package com.api.sns.cheese.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.sns.cheese.resources.ActivityResource;

/**
 * アクティビティサービス
 */
public interface ActivityService {

	/**
	 * フォロー中のアクティビティを取得する
	 *
	 * @param loginId
	 *            ログインID
	 * @param pageable
	 *            ページ情報
	 * @return アクティビティ情報
	 */
	public Page<ActivityResource> findFollowing(String loginId, Pageable pageable);

	/**
	 * 自分に対するアクティビティを取得する
	 *
	 * @param loginId
	 *            ログインID
	 * @param pageable
	 *            ページ情報
	 * @return アクティビティ情報
	 */
	public Page<ActivityResource> findMe(String loginId, Pageable pageable);
}
