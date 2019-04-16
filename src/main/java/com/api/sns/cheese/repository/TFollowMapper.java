package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TFollow;
import com.api.sns.cheese.domain.TFollowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TFollowMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_follow
     *
     * @mbg.generated
     */
    long countByExample(TFollowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_follow
     *
     * @mbg.generated
     */
    int deleteByExample(TFollowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_follow
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long followId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_follow
     *
     * @mbg.generated
     */
    int insert(TFollow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_follow
     *
     * @mbg.generated
     */
    int insertSelective(TFollow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_follow
     *
     * @mbg.generated
     */
    List<TFollow> selectByExample(TFollowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_follow
     *
     * @mbg.generated
     */
    TFollow selectByPrimaryKey(Long followId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_follow
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TFollow record, @Param("example") TFollowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_follow
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TFollow record, @Param("example") TFollowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_follow
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TFollow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_follow
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TFollow record);
}