package com.api.sns.cheese.domain;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_photo
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TPhoto extends TPhotoKey implements Serializable {
    /**
     * Database Column Remarks:
     *   写真コード
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_photo.photo_cd
     *
     * @mbg.generated
     */
    private String photoCd;

    /**
     * Database Column Remarks:
     *   説明
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_photo.caption
     *
     * @mbg.generated
     */
    private String caption;

    /**
     * Database Column Remarks:
     *   画像パス
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_photo.img_url
     *
     * @mbg.generated
     */
    private String imgUrl;

    /**
     * Database Column Remarks:
     *   動画パス
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_photo.video_url
     *
     * @mbg.generated
     */
    private String videoUrl;

    /**
     * Database Column Remarks:
     *   アカウントID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_photo.account_id
     *
     * @mbg.generated
     */
    private Integer accountId;

    /**
     * Database Column Remarks:
     *   いいね件数
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_photo.like_count
     *
     * @mbg.generated
     */
    private Integer likeCount;

    /**
     * Database Column Remarks:
     *   コメントID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_photo.comment_id
     *
     * @mbg.generated
     */
    private Long commentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_photo
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_photo.photo_cd
     *
     * @return the value of t_photo.photo_cd
     *
     * @mbg.generated
     */
    public String getPhotoCd() {
        return photoCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_photo.photo_cd
     *
     * @param photoCd the value for t_photo.photo_cd
     *
     * @mbg.generated
     */
    public void setPhotoCd(String photoCd) {
        this.photoCd = photoCd == null ? null : photoCd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_photo.caption
     *
     * @return the value of t_photo.caption
     *
     * @mbg.generated
     */
    public String getCaption() {
        return caption;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_photo.caption
     *
     * @param caption the value for t_photo.caption
     *
     * @mbg.generated
     */
    public void setCaption(String caption) {
        this.caption = caption == null ? null : caption.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_photo.img_url
     *
     * @return the value of t_photo.img_url
     *
     * @mbg.generated
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_photo.img_url
     *
     * @param imgUrl the value for t_photo.img_url
     *
     * @mbg.generated
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_photo.video_url
     *
     * @return the value of t_photo.video_url
     *
     * @mbg.generated
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_photo.video_url
     *
     * @param videoUrl the value for t_photo.video_url
     *
     * @mbg.generated
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_photo.account_id
     *
     * @return the value of t_photo.account_id
     *
     * @mbg.generated
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_photo.account_id
     *
     * @param accountId the value for t_photo.account_id
     *
     * @mbg.generated
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_photo.like_count
     *
     * @return the value of t_photo.like_count
     *
     * @mbg.generated
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_photo.like_count
     *
     * @param likeCount the value for t_photo.like_count
     *
     * @mbg.generated
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_photo.comment_id
     *
     * @return the value of t_photo.comment_id
     *
     * @mbg.generated
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_photo.comment_id
     *
     * @param commentId the value for t_photo.comment_id
     *
     * @mbg.generated
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
}