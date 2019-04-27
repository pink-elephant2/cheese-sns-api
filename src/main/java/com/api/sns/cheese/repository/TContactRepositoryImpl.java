package com.api.sns.cheese.repository;

import org.springframework.stereotype.Repository;

@Repository
public class TContactRepositoryImpl implements TContactRepository {
    private TContactMapper tContactMapper;

    public TContactRepositoryImpl(TContactMapper tContactMapper) {
        this.tContactMapper = tContactMapper;
    }

    @Override
    public TContactMapper getMapper() {
        return this.tContactMapper;
    }
}