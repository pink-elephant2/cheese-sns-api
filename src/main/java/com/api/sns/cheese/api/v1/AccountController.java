package com.api.sns.cheese.api.v1;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.sns.cheese.form.AccountForm;
import com.api.sns.cheese.form.FollowForm;
import com.api.sns.cheese.resources.AccountResource;
import com.api.sns.cheese.service.AccountService;
import com.api.sns.cheese.service.FollowService;

/**
 * アカウントAPI
 */
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private FollowService followService;

	/**
	 * アカウント取得
	 *
	 * @param loginId
	 *            ログインID
	 */
	@GetMapping("/{loginId}")
	@ResponseStatus(HttpStatus.OK)
	public AccountResource find(@PathVariable("loginId") String loginId) throws NotFoundException {
		// アカウントを取得する
		return accountService.find(loginId);
	}

	/**
	 * プロフィール更新
	 *
	 * @param loginId
	 *            ログインID
	 */
	@PostMapping("/profile")
	@ResponseStatus(HttpStatus.OK)
	public boolean saveProfile(@Validated AccountForm form) throws NotFoundException {
		// プロフィールを更新する
		return accountService.saveProfile(form);
	}

	/**
	 * フォロー取得
	 *
	 * @param loginId
	 *            ログインID
	 */
	@GetMapping("/{loginId}/follow")
	@ResponseStatus(HttpStatus.OK)
	public Page<AccountResource> findFollow(@PathVariable("loginId") String loginId) {
		// フォローを取得する
		return followService.findFollow(loginId);
	}

	/**
	 * フォローワー取得
	 *
	 * @param loginId
	 *            ログインID
	 */
	@GetMapping("/{loginId}/follower")
	@ResponseStatus(HttpStatus.OK)
	public Page<AccountResource> findFollower(@PathVariable("loginId") String loginId) {
		// フォローワーを取得する
		return followService.findFollowers(loginId);
	}

	/**
	 * フォローする
	 *
	 * @param loginId
	 *            ログインID
	 */
	@PostMapping("/follow")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean follow(@RequestBody @Validated FollowForm form) {
		// フォローする
		return followService.follow(form.getLoginId());
	}

	/**
	 * フォローを解除する
	 *
	 * @param loginId
	 *            ログインID
	 */
	@PostMapping("/unfollow")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean unfollow(@RequestBody @Validated FollowForm form) {
		// フォローを解除する
		return followService.unfollow(form.getLoginId());
	}
}
