package com.api.sns.cheese.repository;

import org.springframework.stereotype.Repository;

@Repository
public class TPhotoRepositoryImpl implements TPhotoRepository {
    private TPhotoMapper tPhotoMapper;

    public TPhotoRepositoryImpl(TPhotoMapper tPhotoMapper) {
        this.tPhotoMapper = tPhotoMapper;
    }

    @Override
    public TPhotoMapper getMapper() {
        return this.tPhotoMapper;
    }
}