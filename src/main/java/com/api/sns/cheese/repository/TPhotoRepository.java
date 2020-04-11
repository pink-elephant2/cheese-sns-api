package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TPhoto;
import com.api.sns.cheese.domain.TPhotoExample;
import com.api.sns.cheese.domain.TPhotoKey;
import com.api.sns.cheese.exception.NotFoundException;
import com.api.sns.common.business.repository.BaseRepository;

public interface TPhotoRepository extends BaseRepository<TPhotoKey, TPhoto, TPhotoExample> {

	/**
	 * 写真IDからレコードを取得する
	 *
	 * @param photoId 写真ID
	 * @throws NotFoundException
	 */
	public TPhoto findOneById(Long photoId);

	/**
	 * 写真CDからレコードを取得する
	 *
	 * @param photoCd 写真CD
	 * @throws NotFoundException
	 */
	public TPhoto findOneByCd(String photoCd);

	/**
	 * LAST_INSERT_IDを取得する
	 */
	public Long lastInsertId();

	/**
	 * レコードを登録してIDを返却する
	 */
	public Long createReturnId(TPhoto photo);

}