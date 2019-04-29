package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TPhotoComment;
import com.api.sns.cheese.domain.TPhotoCommentExample;
import com.api.sns.cheese.domain.TPhotoCommentKey;
import com.api.sns.common.business.repository.BaseRepository;

public interface TPhotoCommentRepository extends BaseRepository<TPhotoCommentKey, TPhotoComment, TPhotoCommentExample> {
}