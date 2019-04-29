package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TPhotoCommentLike;
import com.api.sns.cheese.domain.TPhotoCommentLikeExample;
import com.api.sns.cheese.domain.TPhotoCommentLikeKey;
import com.api.sns.common.business.repository.BaseRepository;

public interface TPhotoCommentLikeRepository extends BaseRepository<TPhotoCommentLikeKey, TPhotoCommentLike, TPhotoCommentLikeExample> {
}