package com.api.sns.cheese.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
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