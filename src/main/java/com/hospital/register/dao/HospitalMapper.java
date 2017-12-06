package com.hospital.register.dao;

import com.hospital.register.bean.Hospital;
import com.hospital.register.bean.HospitalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HospitalMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_hospital
     *
     * @mbg.generated
     */
    long countByExample(HospitalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_hospital
     *
     * @mbg.generated
     */
    int deleteByExample(HospitalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_hospital
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer hospitalId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_hospital
     *
     * @mbg.generated
     */
    int insert(Hospital record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_hospital
     *
     * @mbg.generated
     */
    int insertSelective(Hospital record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_hospital
     *
     * @mbg.generated
     */
    List<Hospital> selectByExample(HospitalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_hospital
     *
     * @mbg.generated
     */
    Hospital selectByPrimaryKey(Integer hospitalId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_hospital
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Hospital record, @Param("example") HospitalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_hospital
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Hospital record, @Param("example") HospitalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_hospital
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Hospital record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_hospital
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Hospital record);
}