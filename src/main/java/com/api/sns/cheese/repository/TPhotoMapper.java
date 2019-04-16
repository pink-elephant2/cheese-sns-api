package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TPhoto;
import com.api.sns.cheese.domain.TPhotoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPhotoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_photo
     *
     * @mbg.generated
     */
    long countByExample(TPhotoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_photo
     *
     * @mbg.generated
     */
    int deleteByExample(TPhotoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_photo
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long photoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_photo
     *
     * @mbg.generated
     */
    int insert(TPhoto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_photo
     *
     * @mbg.generated
     */
    int insertSelective(TPhoto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_photo
     *
     * @mbg.generated
     */
    List<TPhoto> selectByExample(TPhotoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_photo
     *
     * @mbg.generated
     */
    TPhoto selectByPrimaryKey(Long photoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_photo
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TPhoto record, @Param("example") TPhotoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_photo
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TPhoto record, @Param("example") TPhotoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_photo
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TPhoto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_photo
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TPhoto record);
}