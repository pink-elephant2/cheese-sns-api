package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TContact;
import com.api.sns.cheese.domain.TContactExample;
import com.api.sns.cheese.domain.TContactKey;
import com.api.sns.common.business.repository.BaseMapper;

public interface TContactMapper extends BaseMapper<TContactKey, TContact, TContactExample> {
}