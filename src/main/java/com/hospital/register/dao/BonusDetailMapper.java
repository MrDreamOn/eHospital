package com.hospital.register.dao;

import com.hospital.register.bean.BonusDetail;
import com.hospital.register.bean.BonusDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BonusDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bonus_detail
     *
     * @mbg.generated
     */
    long countByExample(BonusDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bonus_detail
     *
     * @mbg.generated
     */
    int deleteByExample(BonusDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bonus_detail
     *
     * @mbg.generated
     */
    int insert(BonusDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bonus_detail
     *
     * @mbg.generated
     */
    int insertSelective(BonusDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bonus_detail
     *
     * @mbg.generated
     */
    List<BonusDetail> selectByExample(BonusDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bonus_detail
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") BonusDetail record, @Param("example") BonusDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bonus_detail
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") BonusDetail record, @Param("example") BonusDetailExample example);
}