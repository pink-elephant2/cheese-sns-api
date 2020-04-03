package com.api.sns.cheese.repository;

import java.util.List;

import com.api.sns.cheese.domain.TTagPhoto;
import com.api.sns.cheese.domain.TTagPhotoExample;
import com.api.sns.cheese.domain.TTagPhotoKey;
import com.api.sns.common.business.repository.BaseRepository;

public interface TTagPhotoRepository extends BaseRepository<TTagPhotoKey, TTagPhoto, TTagPhotoExample> {

	/**
	 * 写真IDからレコードを取得する
	 *
	 * @param photoId 写真ID
	 */
	public List<TTagPhoto> findAllByPhotoId(Long photoId);
}