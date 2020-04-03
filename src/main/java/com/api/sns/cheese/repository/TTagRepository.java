package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TTag;
import com.api.sns.cheese.domain.TTagExample;
import com.api.sns.cheese.domain.TTagKey;
import com.api.sns.common.business.repository.BaseRepository;

public interface TTagRepository extends BaseRepository<TTagKey, TTag, TTagExample> {

	/**
	 * レコードを登録してIDを返却する
	 */
	public Long createReturnId(TTag tTag);
}