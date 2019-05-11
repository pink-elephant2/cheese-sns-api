package com.api.sns.cheese.domain;

import com.api.sns.cheese.enums.ActivityTypeEnum;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_activity
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TActivity extends TActivityKey implements Serializable {
    /**
     * Database Column Remarks:
     *   アカウントID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_activity.account_id
     *
     * @mbg.generated
     */
    private Integer accountId;

    /**
     * Database Column Remarks:
     *   アクティビティ種別
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_activity.activity_type
     *
     * @mbg.generated
     */
    private ActivityTypeEnum activityType;

    /**
     * Database Column Remarks:
     *   写真ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_activity.photo_id
     *
     * @mbg.generated
     */
    private Long photoId;

    /**
     * Database Column Remarks:
     *   フォローアカウントID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_activity.follow_account_id
     *
     * @mbg.generated
     */
    private Integer followAccountId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_activity
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_activity.account_id
     *
     * @return the value of t_activity.account_id
     *
     * @mbg.generated
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_activity.account_id
     *
     * @param accountId the value for t_activity.account_id
     *
     * @mbg.generated
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_activity.activity_type
     *
     * @return the value of t_activity.activity_type
     *
     * @mbg.generated
     */
    public ActivityTypeEnum getActivityType() {
        return activityType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_activity.activity_type
     *
     * @param activityType the value for t_activity.activity_type
     *
     * @mbg.generated
     */
    public void setActivityType(ActivityTypeEnum activityType) {
        this.activityType = activityType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_activity.photo_id
     *
     * @return the value of t_activity.photo_id
     *
     * @mbg.generated
     */
    public Long getPhotoId() {
        return photoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_activity.photo_id
     *
     * @param photoId the value for t_activity.photo_id
     *
     * @mbg.generated
     */
    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_activity.follow_account_id
     *
     * @return the value of t_activity.follow_account_id
     *
     * @mbg.generated
     */
    public Integer getFollowAccountId() {
        return followAccountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_activity.follow_account_id
     *
     * @param followAccountId the value for t_activity.follow_account_id
     *
     * @mbg.generated
     */
    public void setFollowAccountId(Integer followAccountId) {
        this.followAccountId = followAccountId;
    }
}