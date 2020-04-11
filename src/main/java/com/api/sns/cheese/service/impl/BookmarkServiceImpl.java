package com.api.sns.cheese.service.impl;

import org.apache.commons.lang3.BooleanUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.sns.cheese.aop.SessionInfoContextHolder;
import com.api.sns.cheese.consts.CommonConst;
import com.api.sns.cheese.domain.TBookmark;
import com.api.sns.cheese.domain.TBookmarkExample;
import com.api.sns.cheese.domain.TPhoto;
import com.api.sns.cheese.exception.NotFoundException;
import com.api.sns.cheese.repository.TAccountRepository;
import com.api.sns.cheese.repository.TBookmarkRepository;
import com.api.sns.cheese.repository.TPhotoRepository;
import com.api.sns.cheese.resources.AccountResource;
import com.api.sns.cheese.resources.PhotoResource;
import com.api.sns.cheese.service.BookmarkService;

/**
 * ブックマークサービス
 */
@Service
@Transactional
public class BookmarkServiceImpl implements BookmarkService {

	@Autowired
	private TBookmarkRepository tBookmarkRepository;

	@Autowired
	private TPhotoRepository tPhotoRepository;

	@Autowired
	private TAccountRepository tAccountRepository;

	@Autowired
	private Mapper mapper;

	/**
	 * ブックマーク一覧を取得する
	 *
	 * @param pageable
	 *            ページ情報
	 * @param ブックマーク一覧
	 */
	@Override
	public Page<PhotoResource> findList(Pageable pageable) {
		// ログインユーザーのブックマークを取得する
		Integer accountId = SessionInfoContextHolder.getSessionInfo().getAccountId();

		TBookmarkExample example = new TBookmarkExample();
		example.createCriteria().andAccountIdEqualTo(accountId).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		return tBookmarkRepository.findPageBy(example, pageable).map(tBookmark -> {
			try {
				// TODO 性能改善

				// 写真を取得
				TPhoto tPhoto = tPhotoRepository.findOneById(tBookmark.getPhotoId());
				PhotoResource resource = mapper.map(tPhoto, PhotoResource.class);

				// TODO 投稿ユーザー View または キャッシュ
				resource.setAccount(
						mapper.map(tAccountRepository.findOneById(tPhoto.getAccountId()), AccountResource.class));

				return resource;
			} catch (NotFoundException e) {
				// null
			}
			return null;
		});
	}

	/**
	 * ブックマークを登録する
	 *
	 * @param photoCd
	 *            写真コード
	 */
	@Override
	public boolean create(String photoCd) {
		Integer accountId = SessionInfoContextHolder.getSessionInfo().getAccountId();

		// 写真を取得する
		TPhoto tPhoto = tPhotoRepository.findOneByCd(photoCd);

		// ブックマークを取得する
		TBookmarkExample example = new TBookmarkExample();
		example.createCriteria().andPhotoIdEqualTo(tPhoto.getPhotoId())
				.andAccountIdEqualTo(accountId);
		TBookmark tBookmark = tBookmarkRepository.findOneBy(example);

		boolean ret;
		if (tBookmark == null) {
			// 登録
			tBookmark = new TBookmark();
			tBookmark.setAccountId(accountId);
			tBookmark.setPhotoId(tPhoto.getPhotoId());
			tBookmark.setDeleted(CommonConst.DeletedFlag.OFF);
			ret = tBookmarkRepository.create(tBookmark);
		} else {
			// 更新
			tBookmark.setDeleted(CommonConst.DeletedFlag.OFF);
			ret = tBookmarkRepository.update(tBookmark);
		}
		return ret;
	}

	/**
	 * ブックマークを削除する
	 *
	 * @param id
	 *            ブックマークID
	 */
	@Override
	public boolean remove(Long id) {
		// 論理削除
		TBookmark entity = new TBookmark();
		entity.setDeleted(CommonConst.DeletedFlag.ON);

		// ブックマークを更新
		TBookmarkExample example = new TBookmarkExample();
		example.createCriteria().andBookmarkIdEqualTo(id)
				.andAccountIdEqualTo(SessionInfoContextHolder.getSessionInfo().getAccountId())
				.andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		return BooleanUtils.toBoolean(tBookmarkRepository.updatePartiallyBy(entity, example));
	}
}
