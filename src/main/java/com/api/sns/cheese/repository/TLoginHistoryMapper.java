package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TLoginHistory;
import com.api.sns.cheese.domain.TLoginHistoryExample;
import com.api.sns.cheese.domain.TLoginHistoryKey;
import com.api.sns.common.business.repository.BaseMapper;

public interface TLoginHistoryMapper extends BaseMapper<TLoginHistoryKey, TLoginHistory, TLoginHistoryExample> {
}