package com.api.sns.cheese.repository;

import org.springframework.stereotype.Repository;

@Repository
public class TPhotoLikeRepositoryImpl implements TPhotoLikeRepository {
    private TPhotoLikeMapper tPhotoLikeMapper;

    public TPhotoLikeRepositoryImpl(TPhotoLikeMapper tPhotoLikeMapper) {
        this.tPhotoLikeMapper = tPhotoLikeMapper;
    }

    @Override
    public TPhotoLikeMapper getMapper() {
        return this.tPhotoLikeMapper;
    }
}