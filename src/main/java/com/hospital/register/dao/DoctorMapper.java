package com.hospital.register.dao;

import com.hospital.register.bean.Doctor;
import com.hospital.register.bean.DoctorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DoctorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_doctor
     *
     * @mbg.generated
     */
    long countByExample(DoctorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_doctor
     *
     * @mbg.generated
     */
    int deleteByExample(DoctorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_doctor
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer doctorId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_doctor
     *
     * @mbg.generated
     */
    int insert(Doctor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_doctor
     *
     * @mbg.generated
     */
    int insertSelective(Doctor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_doctor
     *
     * @mbg.generated
     */
    List<Doctor> selectByExample(DoctorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_doctor
     *
     * @mbg.generated
     */
    Doctor selectByPrimaryKey(Integer doctorId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_doctor
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Doctor record, @Param("example") DoctorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_doctor
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Doctor record, @Param("example") DoctorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_doctor
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Doctor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_doctor
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Doctor record);
}