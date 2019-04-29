package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TFollow;
import com.api.sns.cheese.domain.TFollowExample;
import com.api.sns.cheese.domain.TFollowKey;
import com.api.sns.common.business.repository.BaseRepository;

public interface TFollowRepository extends BaseRepository<TFollowKey, TFollow, TFollowExample> {
}