package com.api.sns.cheese.service;

import javax.validation.constraints.NotNull;

import com.restfb.types.User;

/**
 * Facebookサービス
 */
public interface FacebookService {

	/**
	 * Facebook認証用URLを生成する
	 */
	public String createFacebookAuthorizationURL();

	/**
	 * Facebookユーザーを取得する
	 */
	public User getFacebookUser(@NotNull String code);
}
