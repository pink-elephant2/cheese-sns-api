package com.api.sns.cheese.repository;

import org.springframework.stereotype.Repository;

@Repository
public class TTagPhotoRepositoryImpl implements TTagPhotoRepository {
    private TTagPhotoMapper tTagPhotoMapper;

    public TTagPhotoRepositoryImpl(TTagPhotoMapper tTagPhotoMapper) {
        this.tTagPhotoMapper = tTagPhotoMapper;
    }

    @Override
    public TTagPhotoMapper getMapper() {
        return this.tTagPhotoMapper;
    }
}