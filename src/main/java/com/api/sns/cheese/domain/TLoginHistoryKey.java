package com.api.sns.cheese.domain;

import com.api.sns.common.business.domain.AbstractBaseEntity;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_login_history
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TLoginHistoryKey extends AbstractBaseEntity implements Serializable {
    /**
     * Database Column Remarks:
     *   ログイン履歴ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_login_history.history_id
     *
     * @mbg.generated
     */
    private Long historyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_login_history
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_login_history.history_id
     *
     * @return the value of t_login_history.history_id
     *
     * @mbg.generated
     */
    public Long getHistoryId() {
        return historyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_login_history.history_id
     *
     * @param historyId the value for t_login_history.history_id
     *
     * @mbg.generated
     */
    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }
}