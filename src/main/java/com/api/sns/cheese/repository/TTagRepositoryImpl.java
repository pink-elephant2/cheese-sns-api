package com.api.sns.cheese.repository;

import org.springframework.stereotype.Repository;

@Repository
public class TTagRepositoryImpl implements TTagRepository {
    private TTagMapper tTagMapper;

    public TTagRepositoryImpl(TTagMapper tTagMapper) {
        this.tTagMapper = tTagMapper;
    }

    @Override
    public TTagMapper getMapper() {
        return this.tTagMapper;
    }
}