package com.api.sns.cheese.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.sns.cheese.aop.SessionInfoContextHolder;
import com.api.sns.cheese.domain.VActivityExample;
import com.api.sns.cheese.repository.VActivityRepository;
import com.api.sns.cheese.resources.ActivityResource;
import com.api.sns.cheese.service.ActivityService;

/**
 * アクティビティサービス
 */
@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private VActivityRepository vActivityRepository;

	@Autowired
	private Mapper mapper;

	/**
	 * フォロー中のアクティビティを取得する
	 *
	 * @param loginId
	 *            ログインID
	 * @param pageable
	 *            ページ情報
	 * @return アクティビティ情報
	 */
	@Override
	public Page<ActivityResource> findFollowing(String loginId, Pageable pageable) {
		VActivityExample example = new VActivityExample();
		example.createCriteria().andAccountIdEqualTo(SessionInfoContextHolder.getSessionInfo().getAccountId());
		return vActivityRepository.findPageBy(example, pageable).map(vFollow -> {
			return mapper.map(vFollow, ActivityResource.class);
		});
	}

	/**
	 * 自分に対するアクティビティを取得する
	 *
	 * @param loginId
	 *            ログインID
	 * @param pageable
	 *            ページ情報
	 * @return アクティビティ情報
	 */
	@Override
	public Page<ActivityResource> findMe(String loginId, Pageable pageable) {
		VActivityExample example = new VActivityExample();
		example.createCriteria().andAccountIdEqualTo(SessionInfoContextHolder.getSessionInfo().getAccountId());
		return vActivityRepository.findPageBy(example, pageable).map(vFollow -> {
			return mapper.map(vFollow, ActivityResource.class);
		});
	}
}
