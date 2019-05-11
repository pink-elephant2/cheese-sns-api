package com.api.sns.cheese.domain;

import com.api.sns.common.business.domain.AbstractBaseEntity;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_photo_comment
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TPhotoCommentKey extends AbstractBaseEntity implements Serializable {
    /**
     * Database Column Remarks:
     *   コメントID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_photo_comment.comment_id
     *
     * @mbg.generated
     */
    private Long commentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_photo_comment
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_photo_comment.comment_id
     *
     * @return the value of t_photo_comment.comment_id
     *
     * @mbg.generated
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_photo_comment.comment_id
     *
     * @param commentId the value for t_photo_comment.comment_id
     *
     * @mbg.generated
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
}