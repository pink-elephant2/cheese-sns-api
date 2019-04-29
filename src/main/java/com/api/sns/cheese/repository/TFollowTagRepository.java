package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TFollowTag;
import com.api.sns.cheese.domain.TFollowTagExample;
import com.api.sns.cheese.domain.TFollowTagKey;
import com.api.sns.common.business.repository.BaseRepository;

public interface TFollowTagRepository extends BaseRepository<TFollowTagKey, TFollowTag, TFollowTagExample> {
}