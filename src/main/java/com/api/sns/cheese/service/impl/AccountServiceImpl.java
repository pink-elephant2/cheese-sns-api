package com.api.sns.cheese.service.impl;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.ibatis.javassist.NotFoundException;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.sns.cheese.consts.CommonConst;
import com.api.sns.cheese.domain.TAccount;
import com.api.sns.cheese.domain.TAccountExample;
import com.api.sns.cheese.enums.DocumentTypeEnum;
import com.api.sns.cheese.form.AccountCreateForm;
import com.api.sns.cheese.form.AccountImageForm;
import com.api.sns.cheese.form.AccountUpdateForm;
import com.api.sns.cheese.repository.TAccountRepository;
import com.api.sns.cheese.resources.AccountResource;
import com.api.sns.cheese.service.AccountService;
import com.api.sns.cheese.service.S3Service;

/**
 * アカウントサービス
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private TAccountRepository tAccountRepository;

	@Autowired
	private Mapper mapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private S3Service s3Service;

	/**
	 * アカウントを登録する
	 *
	 * @param form
	 *            アカウント作成フォーム
	 */
	@Override
	public boolean create(AccountCreateForm form) {
		// アカウントを登録する
		TAccount account = mapper.map(form, TAccount.class);
		account.setName(form.getLoginId());

		// パスワード生成
		account.setPassword(passwordEncoder.encode(form.getPassword()));
		account.setPasswordChangeDate(new Date());

		// TODO 共通項目は親クラスで設定する
		account.setDeleted(CommonConst.DeletedFlag.OFF);
		account.setCreatedBy(CommonConst.SystemAccount.ADMIN_ID);
		account.setUpdatedBy(CommonConst.SystemAccount.ADMIN_ID);

		// TODO エラーメッセージ
		return tAccountRepository.create(account);
	}

	/**
	 * アカウントを取得する
	 *
	 * @param loginId
	 *            ログインID
	 * @return アカウント情報
	 */
	@Override
	public AccountResource find(String loginId) throws NotFoundException {
		TAccountExample example = new TAccountExample();
		example.createCriteria().andLoginIdEqualTo(loginId).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		TAccount account = tAccountRepository.findOneBy(example);

		if (account == null) {
			// TODO 404を返す
			throw new NotFoundException("アカウントが存在しません");
		}
		return mapper.map(account, AccountResource.class);
	}

	/**
	 * プロフィールを更新する
	 *
	 * @param form
	 *            プロフィールフォーム
	 */
	@Override
	public boolean saveProfile(AccountUpdateForm form) throws NotFoundException {
		String loginId = "my_melody"; // TODO セッション情報から取得

		// プロフィールを更新する
		TAccount account = mapper.map(form, TAccount.class);

		TAccountExample example = new TAccountExample();
		example.createCriteria().andLoginIdEqualTo(loginId).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		return BooleanUtils.toBoolean(tAccountRepository.updatePartiallyBy(account, example));
	}

	/**
	 * アカウント画像を更新する
	 *
	 * @param form
	 *            画像フォーム
	 */
	public boolean saveImage(AccountImageForm form) {
		String loginId = "my_melody"; // TODO セッション情報から取得

		try {
			// S3に保存、URLを設定する
			String filePath = s3Service.upload(DocumentTypeEnum.ACCOUNT, form.getUpfile());

			// プロフィールを更新する
			TAccount account = mapper.map(form, TAccount.class);
			account.setImgUrl(filePath);

			TAccountExample example = new TAccountExample();
			example.createCriteria().andLoginIdEqualTo(loginId).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
			return BooleanUtils.toBoolean(tAccountRepository.updatePartiallyBy(account, example));

		} catch (IOException e) {
			e.printStackTrace();
			// TODO エラーメッセージ
			return false;
		}
	}
}
