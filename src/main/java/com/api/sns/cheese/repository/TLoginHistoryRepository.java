package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TLoginHistory;
import com.api.sns.cheese.domain.TLoginHistoryExample;
import com.api.sns.cheese.domain.TLoginHistoryKey;
import com.api.sns.common.business.repository.BaseRepository;

public interface TLoginHistoryRepository extends BaseRepository<TLoginHistoryKey, TLoginHistory, TLoginHistoryExample> {
}