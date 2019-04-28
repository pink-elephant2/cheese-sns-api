package com.api.sns.cheese.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class TAccountRepositoryImpl implements TAccountRepository {
    private TAccountMapper tAccountMapper;

    public TAccountRepositoryImpl(TAccountMapper tAccountMapper) {
        this.tAccountMapper = tAccountMapper;
    }

    @Override
    public TAccountMapper getMapper() {
        return this.tAccountMapper;
    }
}