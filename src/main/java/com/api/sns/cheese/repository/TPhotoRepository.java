package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TPhoto;
import com.api.sns.cheese.domain.TPhotoExample;
import com.api.sns.cheese.domain.TPhotoKey;
import com.api.sns.common.business.repository.BaseRepository;

public interface TPhotoRepository extends BaseRepository<TPhotoKey, TPhoto, TPhotoExample> {

	/**
	 * 写真IDからレコードを取得する
	 *
	 * @param photoId 写真ID
	 */
	public TPhoto findOneById(Long photoId);

	/**
	 * 写真CDからレコードを取得する
	 *
	 * @param photoCd 写真CD
	 */
	public TPhoto findOneByCd(String photoCd);

	/**
	 * LAST_INSERT_IDを取得する
	 */
	public Long lastInsertId();

}