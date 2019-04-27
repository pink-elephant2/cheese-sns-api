package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TAccount;
import com.api.sns.cheese.domain.TAccountExample;
import com.api.sns.cheese.domain.TAccountKey;
import com.api.sns.common.business.repository.BaseMapper;

public interface TAccountMapper extends BaseMapper<TAccountKey, TAccount, TAccountExample> {
}