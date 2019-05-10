package com.api.sns.cheese.domain;

import com.api.sns.common.business.domain.AbstractBaseEntity;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_follow
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TFollowKey extends AbstractBaseEntity implements Serializable {
    /**
     * Database Column Remarks:
     *   フォローID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_follow.follow_id
     *
     * @mbg.generated
     */
    private Long followId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_follow
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_follow.follow_id
     *
     * @return the value of t_follow.follow_id
     *
     * @mbg.generated
     */
    public Long getFollowId() {
        return followId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_follow.follow_id
     *
     * @param followId the value for t_follow.follow_id
     *
     * @mbg.generated
     */
    public void setFollowId(Long followId) {
        this.followId = followId;
    }
}