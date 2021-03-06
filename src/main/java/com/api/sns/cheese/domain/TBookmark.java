package com.api.sns.cheese.domain;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_bookmark
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TBookmark extends TBookmarkKey implements Serializable {
    /**
     * Database Column Remarks:
     *   アカウントID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_bookmark.account_id
     *
     * @mbg.generated
     */
    private Integer accountId;

    /**
     * Database Column Remarks:
     *   写真ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_bookmark.photo_id
     *
     * @mbg.generated
     */
    private Long photoId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_bookmark
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_bookmark.account_id
     *
     * @return the value of t_bookmark.account_id
     *
     * @mbg.generated
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_bookmark.account_id
     *
     * @param accountId the value for t_bookmark.account_id
     *
     * @mbg.generated
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_bookmark.photo_id
     *
     * @return the value of t_bookmark.photo_id
     *
     * @mbg.generated
     */
    public Long getPhotoId() {
        return photoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_bookmark.photo_id
     *
     * @param photoId the value for t_bookmark.photo_id
     *
     * @mbg.generated
     */
    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }
}