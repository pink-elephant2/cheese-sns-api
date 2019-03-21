package com.api.sns.cheese.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.sns.cheese.resources.AccountResource;
import com.api.sns.cheese.service.AccountService;

/**
 * アカウントAPI
 */
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	/**
	 * アカウント取得
	 *
	 * @param loginId
	 *            ログインID
	 */
	@GetMapping("/{loginId}")
	@ResponseStatus(HttpStatus.OK)
	public AccountResource find(@PathVariable("loginId") String loginId) {
		// アカウントを取得する
		return accountService.find(loginId);
	}
}
