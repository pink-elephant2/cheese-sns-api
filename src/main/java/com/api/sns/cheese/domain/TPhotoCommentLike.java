package com.api.sns.cheese.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_photo_comment_like
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class TPhotoCommentLike implements Serializable {
    /**
     * Database Column Remarks:
     *   いいねID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_photo_comment_like.like_id
     *
     * @mbg.generated
     */
    private Long likeId;

    /**
     * Database Column Remarks:
     *   アカウントID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_photo_comment_like.account_id
     *
     * @mbg.generated
     */
    private Integer accountId;

    /**
     * Database Column Remarks:
     *   写真ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_photo_comment_like.photo_id
     *
     * @mbg.generated
     */
    private Long photoId;

    /**
     * Database Column Remarks:
     *   コメントID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_photo_comment_like.comment_id
     *
     * @mbg.generated
     */
    private Long commentId;

    /**
     * Database Column Remarks:
     *   削除フラグ
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_photo_comment_like.deleted
     *
     * @mbg.generated
     */
    private String deleted;

    /**
     * Database Column Remarks:
     *   作成日時
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_photo_comment_like.created_at
     *
     * @mbg.generated
     */
    private Date createdAt;

    /**
     * Database Column Remarks:
     *   作成者
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_photo_comment_like.created_by
     *
     * @mbg.generated
     */
    private Integer createdBy;

    /**
     * Database Column Remarks:
     *   更新日時
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_photo_comment_like.updated_at
     *
     * @mbg.generated
     */
    private Date updatedAt;

    /**
     * Database Column Remarks:
     *   更新者
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_photo_comment_like.updated_by
     *
     * @mbg.generated
     */
    private Integer updatedBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_photo_comment_like
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_photo_comment_like.like_id
     *
     * @return the value of t_photo_comment_like.like_id
     *
     * @mbg.generated
     */
    public Long getLikeId() {
        return likeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_photo_comment_like.like_id
     *
     * @param likeId the value for t_photo_comment_like.like_id
     *
     * @mbg.generated
     */
    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_photo_comment_like.account_id
     *
     * @return the value of t_photo_comment_like.account_id
     *
     * @mbg.generated
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_photo_comment_like.account_id
     *
     * @param accountId the value for t_photo_comment_like.account_id
     *
     * @mbg.generated
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_photo_comment_like.photo_id
     *
     * @return the value of t_photo_comment_like.photo_id
     *
     * @mbg.generated
     */
    public Long getPhotoId() {
        return photoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_photo_comment_like.photo_id
     *
     * @param photoId the value for t_photo_comment_like.photo_id
     *
     * @mbg.generated
     */
    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_photo_comment_like.comment_id
     *
     * @return the value of t_photo_comment_like.comment_id
     *
     * @mbg.generated
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_photo_comment_like.comment_id
     *
     * @param commentId the value for t_photo_comment_like.comment_id
     *
     * @mbg.generated
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_photo_comment_like.deleted
     *
     * @return the value of t_photo_comment_like.deleted
     *
     * @mbg.generated
     */
    public String getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_photo_comment_like.deleted
     *
     * @param deleted the value for t_photo_comment_like.deleted
     *
     * @mbg.generated
     */
    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_photo_comment_like.created_at
     *
     * @return the value of t_photo_comment_like.created_at
     *
     * @mbg.generated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_photo_comment_like.created_at
     *
     * @param createdAt the value for t_photo_comment_like.created_at
     *
     * @mbg.generated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_photo_comment_like.created_by
     *
     * @return the value of t_photo_comment_like.created_by
     *
     * @mbg.generated
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_photo_comment_like.created_by
     *
     * @param createdBy the value for t_photo_comment_like.created_by
     *
     * @mbg.generated
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_photo_comment_like.updated_at
     *
     * @return the value of t_photo_comment_like.updated_at
     *
     * @mbg.generated
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_photo_comment_like.updated_at
     *
     * @param updatedAt the value for t_photo_comment_like.updated_at
     *
     * @mbg.generated
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_photo_comment_like.updated_by
     *
     * @return the value of t_photo_comment_like.updated_by
     *
     * @mbg.generated
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_photo_comment_like.updated_by
     *
     * @param updatedBy the value for t_photo_comment_like.updated_by
     *
     * @mbg.generated
     */
    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }
}