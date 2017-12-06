package com.hospital.register.dao;

import java.util.List;

import com.hospital.register.bean.User;
import com.hospital.register.bean.UserExample;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user
	 * @mbg.generated
	 */
	long countByExample(UserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user
	 * @mbg.generated
	 */
	int deleteByExample(UserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Integer userId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user
	 * @mbg.generated
	 */
	int insert(User record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user
	 * @mbg.generated
	 */
	int insertSelective(User record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user
	 * @mbg.generated
	 */
	List<User> selectByExample(UserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user
	 * @mbg.generated
	 */
	User selectByPrimaryKey(Integer userId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user
	 * @mbg.generated
	 */
	int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user
	 * @mbg.generated
	 */
	int updateByExample(@Param("record") User record, @Param("example") UserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(User record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user
	 * @mbg.generated
	 */
	int updateByPrimaryKey(User record);

	List<User> findByUserName(String username);
	
	List<User> selectAllUser();
}
