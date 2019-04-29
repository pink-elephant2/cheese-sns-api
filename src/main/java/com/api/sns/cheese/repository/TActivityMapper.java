package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TActivity;
import com.api.sns.cheese.domain.TActivityExample;
import com.api.sns.cheese.domain.TActivityKey;
import com.api.sns.common.business.repository.BaseMapper;

public interface TActivityMapper extends BaseMapper<TActivityKey, TActivity, TActivityExample> {
}