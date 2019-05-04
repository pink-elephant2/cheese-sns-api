package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TPhoto;
import com.api.sns.cheese.domain.TPhotoExample;
import com.api.sns.cheese.domain.TPhotoKey;
import com.api.sns.common.business.repository.BaseRepository;

public interface TPhotoRepository extends BaseRepository<TPhotoKey, TPhoto, TPhotoExample> {

	/**
	 * 写真CDからレコードを取得する
	 */
	public TPhoto findOneByCd(String photoCd);

	/**
	 * LAST_INSERT_IDを取得する
	 */
	public Long lastInsertId();

}