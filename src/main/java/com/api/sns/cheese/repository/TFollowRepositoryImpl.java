package com.api.sns.cheese.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class TFollowRepositoryImpl implements TFollowRepository {
    private TFollowMapper tFollowMapper;

    public TFollowRepositoryImpl(TFollowMapper tFollowMapper) {
        this.tFollowMapper = tFollowMapper;
    }

    @Override
    public TFollowMapper getMapper() {
        return this.tFollowMapper;
    }
}