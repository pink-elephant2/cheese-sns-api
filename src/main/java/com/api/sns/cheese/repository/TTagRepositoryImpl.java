package com.api.sns.cheese.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.api.sns.cheese.domain.TTag;

@Primary
@Repository
public class TTagRepositoryImpl implements TTagRepository {
	private TTagMapper tTagMapper;

	public TTagRepositoryImpl(TTagMapper tTagMapper) {
		this.tTagMapper = tTagMapper;
	}

	@Override
	public TTagMapper getMapper() {
		return this.tTagMapper;
	}

	/**
	 * レコードを登録してIDを返却する
	 */
	@Override
	public Long createReturnId(TTag tTag) {
		this.beforeInsert(tTag);
		return tTagMapper.insertReturnId(tTag);
	}
}