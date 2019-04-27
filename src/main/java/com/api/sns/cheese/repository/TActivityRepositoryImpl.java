package com.api.sns.cheese.repository;

import org.springframework.stereotype.Repository;

@Repository
public class TActivityRepositoryImpl implements TActivityRepository {
    private TActivityMapper tActivityMapper;

    public TActivityRepositoryImpl(TActivityMapper tActivityMapper) {
        this.tActivityMapper = tActivityMapper;
    }

    @Override
    public TActivityMapper getMapper() {
        return this.tActivityMapper;
    }
}