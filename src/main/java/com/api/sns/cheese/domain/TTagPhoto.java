package com.api.sns.cheese.domain;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_tag_photo
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TTagPhoto extends TTagPhotoKey implements Serializable {
    /**
     * Database Column Remarks:
     *   タグID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_tag_photo.tag_id
     *
     * @mbg.generated
     */
    private Long tagId;

    /**
     * Database Column Remarks:
     *   写真ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_tag_photo.photo_id
     *
     * @mbg.generated
     */
    private Long photoId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_tag_photo
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_tag_photo.tag_id
     *
     * @return the value of t_tag_photo.tag_id
     *
     * @mbg.generated
     */
    public Long getTagId() {
        return tagId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_tag_photo.tag_id
     *
     * @param tagId the value for t_tag_photo.tag_id
     *
     * @mbg.generated
     */
    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_tag_photo.photo_id
     *
     * @return the value of t_tag_photo.photo_id
     *
     * @mbg.generated
     */
    public Long getPhotoId() {
        return photoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_tag_photo.photo_id
     *
     * @param photoId the value for t_tag_photo.photo_id
     *
     * @mbg.generated
     */
    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }
}