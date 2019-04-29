package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TTag;
import com.api.sns.cheese.domain.TTagExample;
import com.api.sns.cheese.domain.TTagKey;
import com.api.sns.common.business.repository.BaseMapper;

public interface TTagMapper extends BaseMapper<TTagKey, TTag, TTagExample> {
}