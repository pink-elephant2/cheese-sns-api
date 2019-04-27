package com.api.sns.cheese.repository;

import org.springframework.stereotype.Repository;

@Repository
public class TPhotoCommentLikeRepositoryImpl implements TPhotoCommentLikeRepository {
    private TPhotoCommentLikeMapper tPhotoCommentLikeMapper;

    public TPhotoCommentLikeRepositoryImpl(TPhotoCommentLikeMapper tPhotoCommentLikeMapper) {
        this.tPhotoCommentLikeMapper = tPhotoCommentLikeMapper;
    }

    @Override
    public TPhotoCommentLikeMapper getMapper() {
        return this.tPhotoCommentLikeMapper;
    }
}