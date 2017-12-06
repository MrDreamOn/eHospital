package com.hospital.register.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hospital.register.bean.Permission;
import com.hospital.register.bean.PermissionExample;

public interface PermissionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated
     */
    long countByExample(PermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated
     */
    int deleteByExample(PermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer permissionId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated
     */
    int insert(Permission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated
     */
    int insertSelective(Permission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated
     */
    List<Permission> selectByExample(PermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated
     */
    Permission selectByPrimaryKey(Integer permissionId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Permission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Permission record);
    
    
    /**
     * 通过角色id查找权限
     * @param roleId
     * @return
     */
    List<Permission> selectByRoleId(Integer roleId);
}