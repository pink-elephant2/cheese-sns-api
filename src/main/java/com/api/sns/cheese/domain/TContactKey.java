package com.api.sns.cheese.domain;

import com.api.sns.common.business.domain.AbstractBaseEntity;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_contact
 *
 * @mbg.generated do_not_delete_during_merge
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TContactKey extends AbstractBaseEntity implements Serializable {
    /**
     * Database Column Remarks:
     *   お問合せID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_contact.contact_id
     *
     * @mbg.generated
     */
    private Integer contactId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_contact
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_contact.contact_id
     *
     * @return the value of t_contact.contact_id
     *
     * @mbg.generated
     */
    public Integer getContactId() {
        return contactId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_contact.contact_id
     *
     * @param contactId the value for t_contact.contact_id
     *
     * @mbg.generated
     */
    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }
}