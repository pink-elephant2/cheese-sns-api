package com.api.sns.cheese.repository;

import org.springframework.stereotype.Repository;

@Repository
public class TFollowTagRepositoryImpl implements TFollowTagRepository {
    private TFollowTagMapper tFollowTagMapper;

    public TFollowTagRepositoryImpl(TFollowTagMapper tFollowTagMapper) {
        this.tFollowTagMapper = tFollowTagMapper;
    }

    @Override
    public TFollowTagMapper getMapper() {
        return this.tFollowTagMapper;
    }
}