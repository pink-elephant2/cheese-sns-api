package com.api.sns.cheese.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class VActivityRepositoryImpl implements VActivityRepository {
    private VActivityMapper vActivityMapper;

    public VActivityRepositoryImpl(VActivityMapper vActivityMapper) {
        this.vActivityMapper = vActivityMapper;
    }

    @Override
    public VActivityMapper getMapper() {
        return this.vActivityMapper;
    }
}