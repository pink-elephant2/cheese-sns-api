package com.api.sns.cheese.repository;

import org.springframework.stereotype.Repository;

@Repository
public class TPhotoCommentRepositoryImpl implements TPhotoCommentRepository {
    private TPhotoCommentMapper tPhotoCommentMapper;

    public TPhotoCommentRepositoryImpl(TPhotoCommentMapper tPhotoCommentMapper) {
        this.tPhotoCommentMapper = tPhotoCommentMapper;
    }

    @Override
    public TPhotoCommentMapper getMapper() {
        return this.tPhotoCommentMapper;
    }
}