package com.api.sns.cheese.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

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
	 * LAST_INSERT_IDを取得する
	 */
	public Long lastInsertId() {
		return null;
	}
}