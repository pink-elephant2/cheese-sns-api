package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TPhoto;
import com.api.sns.cheese.domain.TPhotoExample;
import com.api.sns.cheese.domain.TPhotoKey;
import com.api.sns.common.business.repository.BaseMapper;

public interface TPhotoMapper extends BaseMapper<TPhotoKey, TPhoto, TPhotoExample> {

	/**
	 * レコードを登録してIDを返却する
	 */
	public Long insertReturnId(TPhoto photo);
}