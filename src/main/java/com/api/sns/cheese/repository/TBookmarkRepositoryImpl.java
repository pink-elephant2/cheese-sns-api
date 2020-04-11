package com.api.sns.cheese.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class TBookmarkRepositoryImpl implements TBookmarkRepository {
    private TBookmarkMapper tBookmarkMapper;

    public TBookmarkRepositoryImpl(TBookmarkMapper tBookmarkMapper) {
        this.tBookmarkMapper = tBookmarkMapper;
    }

    @Override
    public TBookmarkMapper getMapper() {
        return this.tBookmarkMapper;
    }
}