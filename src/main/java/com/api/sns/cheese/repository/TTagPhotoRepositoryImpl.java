package com.api.sns.cheese.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.api.sns.cheese.consts.CommonConst;
import com.api.sns.cheese.domain.TTagPhoto;
import com.api.sns.cheese.domain.TTagPhotoExample;

@Primary
@Repository
public class TTagPhotoRepositoryImpl implements TTagPhotoRepository {
	private TTagPhotoMapper tTagPhotoMapper;

	public TTagPhotoRepositoryImpl(TTagPhotoMapper tTagPhotoMapper) {
		this.tTagPhotoMapper = tTagPhotoMapper;
	}

	@Override
	public TTagPhotoMapper getMapper() {
		return this.tTagPhotoMapper;
	}

	/**
	 * 写真IDからレコードを取得する
	 *
	 * @param photoId 写真ID
	 */
	@Override
	public List<TTagPhoto> findAllByPhotoId(Long photoId) {
		TTagPhotoExample tagPhotoExample = new TTagPhotoExample();
		tagPhotoExample.createCriteria().andPhotoIdEqualTo(photoId).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		List<TTagPhoto> tagPhotoList = findAllBy(tagPhotoExample);
		return tagPhotoList;
	}

}