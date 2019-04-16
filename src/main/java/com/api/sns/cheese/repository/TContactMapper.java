package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TContact;
import com.api.sns.cheese.domain.TContactExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TContactMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contact
     *
     * @mbg.generated
     */
    long countByExample(TContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contact
     *
     * @mbg.generated
     */
    int deleteByExample(TContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contact
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer contactId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contact
     *
     * @mbg.generated
     */
    int insert(TContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contact
     *
     * @mbg.generated
     */
    int insertSelective(TContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contact
     *
     * @mbg.generated
     */
    List<TContact> selectByExample(TContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contact
     *
     * @mbg.generated
     */
    TContact selectByPrimaryKey(Integer contactId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contact
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TContact record, @Param("example") TContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contact
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TContact record, @Param("example") TContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contact
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contact
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TContact record);
}