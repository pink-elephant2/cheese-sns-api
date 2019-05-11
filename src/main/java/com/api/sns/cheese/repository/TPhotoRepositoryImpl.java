package com.api.sns.cheese.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.api.sns.cheese.consts.CommonConst;
import com.api.sns.cheese.domain.TPhoto;
import com.api.sns.cheese.domain.TPhotoExample;

@Primary
@Repository
public class TPhotoRepositoryImpl implements TPhotoRepository {
	private TPhotoMapper tPhotoMapper;

	public TPhotoRepositoryImpl(TPhotoMapper tPhotoMapper) {
		this.tPhotoMapper = tPhotoMapper;
	}

	@Override
	public TPhotoMapper getMapper() {
		return this.tPhotoMapper;
	}

	/**
	 * 写真IDからレコードを取得する
	 *
	 * @param photoId 写真ID
	 */
	@Override
	public TPhoto findOneById(Long photoId) {
		TPhotoExample photoExample = new TPhotoExample();
		photoExample.createCriteria().andPhotoIdEqualTo(photoId).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		TPhoto photo = findOneBy(photoExample);
		if (photo == null) {
			// TODO 404を返す
			// throw new NotFoundException("写真が存在しません");
		}
		return photo;
	}

	/**
	 * 写真CDからレコードを取得する
	 *
	 * @param photoCd 写真CD
	 */
	@Override
	public TPhoto findOneByCd(String photoCd) {
		TPhotoExample photoExample = new TPhotoExample();
		photoExample.createCriteria().andPhotoCdEqualTo(photoCd).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		TPhoto photo = findOneBy(photoExample);
		if (photo == null) {
			// TODO 404を返す
			// throw new NotFoundException("写真が存在しません");
		}
		return photo;
	}

	/**
	 * LAST_INSERT_IDを取得する
	 */
	@Override
	public Long lastInsertId() {
		return null;
	}
}