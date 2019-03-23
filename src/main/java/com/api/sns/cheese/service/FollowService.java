package com.api.sns.cheese.service;

import org.springframework.data.domain.Page;

import com.api.sns.cheese.resources.AccountResource;

/**
 * フォローサービス
 */
public interface FollowService {

	/**
	 * フォローを取得する
	 *
	 * @param loginId
	 *            ログインID
	 * @return アカウント情報
	 */
	public Page<AccountResource> findFollow(String loginId);

	/**
	 * フォローワーを取得する
	 *
	 * @param loginId
	 *            ログインID
	 * @return アカウント情報
	 */
	public Page<AccountResource> findFollowers(String loginId);
}
