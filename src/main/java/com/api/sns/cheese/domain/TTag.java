package com.api.sns.cheese.domain;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_tag
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TTag extends TTagKey implements Serializable {
    /**
     * Database Column Remarks:
     *   タグ名
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_tag.tag_name
     *
     * @mbg.generated
     */
    private String tagName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_tag
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_tag.tag_name
     *
     * @return the value of t_tag.tag_name
     *
     * @mbg.generated
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_tag.tag_name
     *
     * @param tagName the value for t_tag.tag_name
     *
     * @mbg.generated
     */
    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }
}