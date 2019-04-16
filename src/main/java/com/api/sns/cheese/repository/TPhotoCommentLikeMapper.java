package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TPhotoCommentLike;
import com.api.sns.cheese.domain.TPhotoCommentLikeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPhotoCommentLikeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_photo_comment_like
     *
     * @mbg.generated
     */
    long countByExample(TPhotoCommentLikeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_photo_comment_like
     *
     * @mbg.generated
     */
    int deleteByExample(TPhotoCommentLikeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_photo_comment_like
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long likeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_photo_comment_like
     *
     * @mbg.generated
     */
    int insert(TPhotoCommentLike record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_photo_comment_like
     *
     * @mbg.generated
     */
    int insertSelective(TPhotoCommentLike record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_photo_comment_like
     *
     * @mbg.generated
     */
    List<TPhotoCommentLike> selectByExample(TPhotoCommentLikeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_photo_comment_like
     *
     * @mbg.generated
     */
    TPhotoCommentLike selectByPrimaryKey(Long likeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_photo_comment_like
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TPhotoCommentLike record, @Param("example") TPhotoCommentLikeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_photo_comment_like
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TPhotoCommentLike record, @Param("example") TPhotoCommentLikeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_photo_comment_like
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TPhotoCommentLike record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_photo_comment_like
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TPhotoCommentLike record);
}