package com.api.sns.cheese.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.sns.cheese.aop.SessionInfoContextHolder;
import com.api.sns.cheese.domain.TPhoto;
import com.api.sns.cheese.domain.TPhotoKey;
import com.api.sns.cheese.domain.VActivity;
import com.api.sns.cheese.domain.VActivityExample;
import com.api.sns.cheese.repository.TAccountRepository;
import com.api.sns.cheese.repository.TPhotoRepository;
import com.api.sns.cheese.repository.VActivityRepository;
import com.api.sns.cheese.resources.AccountResource;
import com.api.sns.cheese.resources.ActivityResource;
import com.api.sns.cheese.resources.PhotoResource;
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
	private TAccountRepository tAccountRepository;

	@Autowired
	private TPhotoRepository tPhotoRepository;

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
		return vActivityRepository.findPageBy(example, pageable).map(vFollow -> mapResource(vFollow));
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
		return vActivityRepository.findPageBy(example, pageable).map(vFollow -> mapResource(vFollow));
	}

	/**
	 * アクティビティリソースに変換
	 *
	 * @param vFollow
	 * @return ActivityResource
	 */
	private ActivityResource mapResource(VActivity vFollow) {
		ActivityResource resource = mapper.map(vFollow, ActivityResource.class);

		switch (resource.getActivityType()) {

		// コメントされた
		case COMMENT:
			break;

		// フォローされた
		case FOLLOW:
			// アカウントを取得
			AccountResource followAccount = mapper.map(
					tAccountRepository.findOneById(vFollow.getFollowAccountId()),
					AccountResource.class);
			resource.setAccount(followAccount);
			break;

		// いいねされた
		case LIKE:
			break;

		// 投稿された
		case NEW_POST:
			// 写真を取得
			TPhotoKey photoKey = new TPhotoKey();
			photoKey.setPhotoId(vFollow.getPhotoId());
			TPhoto photo = tPhotoRepository.findOneBy(photoKey);
			PhotoResource photoResource = mapper.map(photo, PhotoResource.class);

			// アカウントを取得
			AccountResource postAccount = mapper.map(tAccountRepository.findOneById(photo.getAccountId()),
					AccountResource.class);
			photoResource.setAccount(postAccount);
			resource.setAccount(postAccount);

			resource.setPhoto(photoResource);
			break;

		default:
			break;
		}
		return resource;
	}
}
