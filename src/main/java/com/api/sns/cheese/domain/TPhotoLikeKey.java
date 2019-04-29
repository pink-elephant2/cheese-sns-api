package com.api.sns.cheese.domain;

import com.api.sns.common.business.domain.AbstractBaseEntity;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_photo_like
 *
 * @mbg.generated do_not_delete_during_merge
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TPhotoLikeKey extends AbstractBaseEntity implements Serializable {
    /**
     * Database Column Remarks:
     *   いいねID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_photo_like.like_id
     *
     * @mbg.generated
     */
    private Long likeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_photo_like
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_photo_like.like_id
     *
     * @return the value of t_photo_like.like_id
     *
     * @mbg.generated
     */
    public Long getLikeId() {
        return likeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_photo_like.like_id
     *
     * @param likeId the value for t_photo_like.like_id
     *
     * @mbg.generated
     */
    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }
}