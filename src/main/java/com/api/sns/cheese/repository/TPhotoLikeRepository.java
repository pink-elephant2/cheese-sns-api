package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TPhotoLike;
import com.api.sns.cheese.domain.TPhotoLikeExample;
import com.api.sns.cheese.domain.TPhotoLikeKey;
import com.api.sns.common.business.repository.BaseRepository;

public interface TPhotoLikeRepository extends BaseRepository<TPhotoLikeKey, TPhotoLike, TPhotoLikeExample> {
}