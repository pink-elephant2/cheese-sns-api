package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TContact;
import com.api.sns.cheese.domain.TContactExample;
import com.api.sns.cheese.domain.TContactKey;
import com.api.sns.common.business.repository.BaseRepository;

public interface TContactRepository extends BaseRepository<TContactKey, TContact, TContactExample> {
}