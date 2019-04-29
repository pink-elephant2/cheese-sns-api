package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TActivity;
import com.api.sns.cheese.domain.TActivityExample;
import com.api.sns.cheese.domain.TActivityKey;
import com.api.sns.common.business.repository.BaseRepository;

public interface TActivityRepository extends BaseRepository<TActivityKey, TActivity, TActivityExample> {
}