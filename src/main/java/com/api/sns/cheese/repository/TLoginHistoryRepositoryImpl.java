package com.api.sns.cheese.repository;

import org.springframework.stereotype.Repository;

@Repository
public class TLoginHistoryRepositoryImpl implements TLoginHistoryRepository {
    private TLoginHistoryMapper tLoginHistoryMapper;

    public TLoginHistoryRepositoryImpl(TLoginHistoryMapper tLoginHistoryMapper) {
        this.tLoginHistoryMapper = tLoginHistoryMapper;
    }

    @Override
    public TLoginHistoryMapper getMapper() {
        return this.tLoginHistoryMapper;
    }
}