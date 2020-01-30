package com.api.sns.cheese.api.v1;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.api.sns.cheese.config.AppConfig;
import com.api.sns.cheese.config.AutoAuthenticationManager;
import com.api.sns.cheese.domain.TAccount;
import com.api.sns.cheese.form.AccountCreateForm;
import com.api.sns.cheese.service.AccountService;
import com.api.sns.cheese.service.FacebookService;
import com.restfb.types.User;

/**
 * ログインAPI
 */
//@Slf4j
@Controller
@RequestMapping("/api/v1/login")
public class LoginController {

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private AccountService accountService;

	@Autowired
	private AutoAuthenticationManager autoAuthenticationManager;

	@Autowired
	private FacebookService facebookService;

	/**
	 * Facebookログイン
	 */
	@GetMapping("/facebook")
	public String loginToFacebook() {
		return "redirect:" + facebookService.createFacebookAuthorizationURL();
	}

	/**
	 * Facebookコールバック
	 */
	@GetMapping("/facebook/cbk")
	public String createFacebookAccessToken(@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "error", required = false) String error, HttpServletRequest request) {
		if (error != null) {
			return "redirect:" + appConfig.getUrl();
		}
		// Facebook認証
		User facebookUser = facebookService.getFacebookUser(code);

		// DBに登録されているユーザーを取得
		TAccount account = accountService.findByFacebookId(facebookUser.getId());
		// TODO BANされている場合
		if (account == null) {
			// ユーザー登録
			AccountCreateForm form = new AccountCreateForm();
			form.setLoginId(facebookUser.getId()); // TODO ログインID検討
			form.setName(facebookUser.getName());
			form.setMail(facebookUser.getEmail());
			form.setPassword("FACEBOOK," + facebookUser.getId() + ",USER"); // TODO 仮パスワード長すぎ
			form.setImgUrl(facebookUser.getPicture() == null ? null : facebookUser.getPicture().getUrl());
			form.setFacebook(facebookUser.getId());
			accountService.create(form);
		}
		// ログイン
		autoLogin(request, facebookUser);
		return "redirect:" + appConfig.getUrl();
	}

	/**
	 * 独自ログインする
	 */
	private void autoLogin(HttpServletRequest request, User facebookUser) {
		// ログイン
		String pass = "FACEBOOK," + facebookUser.getId() + ",USER";
		UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
				facebookUser.getId(), pass, AuthorityUtils.createAuthorityList("ROLE_USER"));
		authReq.setDetails(new WebAuthenticationDetails(request));
		Authentication auth = autoAuthenticationManager.authenticate(authReq);

		SecurityContextHolder.getContext().setAuthentication(auth);
	}

}
