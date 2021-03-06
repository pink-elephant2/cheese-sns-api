package com.api.sns.cheese.api.v1;

import java.net.URLEncoder;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.sns.cheese.config.AppConfig;
import com.api.sns.cheese.domain.TAccount;
import com.api.sns.cheese.exception.NotFoundException;
import com.api.sns.cheese.form.PasswordReminderForm;
import com.api.sns.cheese.form.PasswordResetForm;
import com.api.sns.cheese.service.AccountService;
import com.api.sns.cheese.service.CacheService;
import com.api.sns.cheese.service.MailService;
import com.api.sns.cheese.util.AesUtils;


/**
 * パスワードリマインダーAPI
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/password")
public class PasswordController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private CacheService cacheService;

	@Autowired
	private MailService mailService;

	@Autowired
	private AppConfig appConfig;

	/** キャッシュキー */
	private final String CACHE_KEY_PREFIX = "FORGET_ID_KEY:";

	/**
	 * リマインダーメールを送信する
	 *
	 * @throws Exception
	 */
	@PostMapping("/reminder")
	public boolean reminder(@RequestBody @Validated PasswordReminderForm form) throws Exception {
		// ユーザー存在チェック
		TAccount account = accountService.findByMail(form.getMail());

		// ワンタイムトークン生成
		String secretKey = RandomStringUtils.randomAlphanumeric(16);
		String token;
		try {
			String key = account.getLoginId() + "," + secretKey;
			String signature = AesUtils.encrypt(appConfig.getAesKey(), key);
			token = URLEncoder.encode(signature, "utf-8");
		} catch (Exception e) {
			throw e;
		}

		// キャッシュ(24時間)
		cacheService.saveValue(CACHE_KEY_PREFIX + account.getLoginId(), secretKey, 86400);

		// リマインダーメール送信
		return mailService.sendPasswordReminder(form.getMail(), token);
	}

	/**
	 * ワンタイムトークンをチェックする
	 *
	 * @param token ワンタイムトークン
	 */
	@GetMapping("/reminder/token/{token}")
	public boolean check(@PathVariable("token") String token) {
		// トークンチェック
		return StringUtils.isNotEmpty(checkToken(token));
	}

	/**
	 * パスワードを再設定する
	 */
	@PostMapping("/reset")
	public boolean reset(@RequestBody @Validated PasswordResetForm form) {
		// トークンチェック
		String loginId = checkToken(form.getToken());
		if (StringUtils.isEmpty(loginId)) {
			// 有効期限切れなど
			throw new NotFoundException("");
		}
		// パスワード更新
		boolean ret = accountService.savePassword(loginId, form);
		if (ret) {
			// キャッシュクリア
			cacheService.delete(CACHE_KEY_PREFIX + loginId);
		}
		return ret;
	}

	/**
	 * トークンチェック
	 *
	 * @param token トークン
	 * @return トークンから取得したログインID
	 */
	private String checkToken(String token) {
		String key;
		try {
			key = AesUtils.decrypt(appConfig.getAesKey(), token);
		} catch (Exception e) {
			// 不正なアクセス
			throw new NotFoundException("");
		}
		String[] tempArray = key.split(",");
		String loginId = tempArray[0];
		String secretKey = tempArray[1];
		String secretKeyCache = cacheService.getValue(CACHE_KEY_PREFIX + loginId);
		if (secretKeyCache == null) {
			// 有効期限切れ
			throw new NotFoundException("");
		}
		if (!secretKey.equals(secretKeyCache)) {
			// 不正なアクセス
			throw new NotFoundException("");
		}
		return loginId;
	}
}
