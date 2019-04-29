package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TTagPhoto;
import com.api.sns.cheese.domain.TTagPhotoExample;
import com.api.sns.cheese.domain.TTagPhotoKey;
import com.api.sns.common.business.repository.BaseRepository;

public interface TTagPhotoRepository extends BaseRepository<TTagPhotoKey, TTagPhoto, TTagPhotoExample> {
}