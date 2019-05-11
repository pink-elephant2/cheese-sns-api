package com.api.sns.cheese.domain;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_follow_tag
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TFollowTag extends TFollowTagKey implements Serializable {
    /**
     * Database Column Remarks:
     *   アカウントID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_follow_tag.account_id
     *
     * @mbg.generated
     */
    private Integer accountId;

    /**
     * Database Column Remarks:
     *   タグID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_follow_tag.tag_id
     *
     * @mbg.generated
     */
    private Long tagId;

    /**
     * Database Column Remarks:
     *   ブロックフラグ
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_follow_tag.block_flag
     *
     * @mbg.generated
     */
    private Boolean blockFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_follow_tag
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_follow_tag.account_id
     *
     * @return the value of t_follow_tag.account_id
     *
     * @mbg.generated
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_follow_tag.account_id
     *
     * @param accountId the value for t_follow_tag.account_id
     *
     * @mbg.generated
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_follow_tag.tag_id
     *
     * @return the value of t_follow_tag.tag_id
     *
     * @mbg.generated
     */
    public Long getTagId() {
        return tagId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_follow_tag.tag_id
     *
     * @param tagId the value for t_follow_tag.tag_id
     *
     * @mbg.generated
     */
    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_follow_tag.block_flag
     *
     * @return the value of t_follow_tag.block_flag
     *
     * @mbg.generated
     */
    public Boolean getBlockFlag() {
        return blockFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_follow_tag.block_flag
     *
     * @param blockFlag the value for t_follow_tag.block_flag
     *
     * @mbg.generated
     */
    public void setBlockFlag(Boolean blockFlag) {
        this.blockFlag = blockFlag;
    }
}