package com.hospital.register.dao;

import com.hospital.register.bean.Log;
import com.hospital.register.bean.LogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_log
     *
     * @mbg.generated
     */
    long countByExample(LogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_log
     *
     * @mbg.generated
     */
    int deleteByExample(LogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_log
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer logId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_log
     *
     * @mbg.generated
     */
    int insert(Log record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_log
     *
     * @mbg.generated
     */
    int insertSelective(Log record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_log
     *
     * @mbg.generated
     */
    List<Log> selectByExample(LogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_log
     *
     * @mbg.generated
     */
    Log selectByPrimaryKey(Integer logId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_log
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Log record, @Param("example") LogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_log
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Log record, @Param("example") LogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_log
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Log record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_log
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Log record);
}