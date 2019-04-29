package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TBookmark;
import com.api.sns.cheese.domain.TBookmarkExample;
import com.api.sns.cheese.domain.TBookmarkKey;
import com.api.sns.common.business.repository.BaseRepository;

public interface TBookmarkRepository extends BaseRepository<TBookmarkKey, TBookmark, TBookmarkExample> {
}