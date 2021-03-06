package com.hospital.register.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Schedule implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_schedule.schedule_id
	 * @mbg.generated
	 */
	private Integer scheduleId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_schedule.hospital_id
	 * @mbg.generated
	 */
	private Integer hospitalId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_schedule.doctor_id
	 * @mbg.generated
	 */
	private Integer doctorId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_schedule.department_id
	 * @mbg.generated
	 */
	private Integer departmentId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_schedule.clinic_date
	 * @mbg.generated
	 */
	private Date clinicDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_schedule.clinic_time
	 * @mbg.generated
	 */
	private String clinicTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_schedule.clinic_week
	 * @mbg.generated
	 */
	private Integer clinicWeek;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_schedule.clinic_type
	 * @mbg.generated
	 */
	private Integer clinicType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_schedule.clinic_status
	 * @mbg.generated
	 */
	private Integer clinicStatus;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_schedule.clinic_no
	 * @mbg.generated
	 */
	private Integer clinicNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_schedule.clinic_fee
	 * @mbg.generated
	 */
	private BigDecimal clinicFee;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_schedule.create_time
	 * @mbg.generated
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_schedule.update_time
	 * @mbg.generated
	 */
	private Date updateTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_schedule
	 * @mbg.generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_schedule
	 * @mbg.generated
	 */
	public Schedule(Integer scheduleId, Integer hospitalId, Integer doctorId, Integer departmentId, Date clinicDate,
			String clinicTime, Integer clinicWeek, Integer clinicType, Integer clinicStatus, Integer clinicNo,
			BigDecimal clinicFee, Date createTime, Date updateTime) {
		this.scheduleId = scheduleId;
		this.hospitalId = hospitalId;
		this.doctorId = doctorId;
		this.departmentId = departmentId;
		this.clinicDate = clinicDate;
		this.clinicTime = clinicTime;
		this.clinicWeek = clinicWeek;
		this.clinicType = clinicType;
		this.clinicStatus = clinicStatus;
		this.clinicNo = clinicNo;
		this.clinicFee = clinicFee;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_schedule
	 * @mbg.generated
	 */
	public Schedule() {
		super();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_schedule.schedule_id
	 * @return  the value of t_schedule.schedule_id
	 * @mbg.generated
	 */
	public Integer getScheduleId() {
		return scheduleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_schedule.schedule_id
	 * @param scheduleId  the value for t_schedule.schedule_id
	 * @mbg.generated
	 */
	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_schedule.hospital_id
	 * @return  the value of t_schedule.hospital_id
	 * @mbg.generated
	 */
	public Integer getHospitalId() {
		return hospitalId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_schedule.hospital_id
	 * @param hospitalId  the value for t_schedule.hospital_id
	 * @mbg.generated
	 */
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_schedule.doctor_id
	 * @return  the value of t_schedule.doctor_id
	 * @mbg.generated
	 */
	public Integer getDoctorId() {
		return doctorId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_schedule.doctor_id
	 * @param doctorId  the value for t_schedule.doctor_id
	 * @mbg.generated
	 */
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_schedule.department_id
	 * @return  the value of t_schedule.department_id
	 * @mbg.generated
	 */
	public Integer getDepartmentId() {
		return departmentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_schedule.department_id
	 * @param departmentId  the value for t_schedule.department_id
	 * @mbg.generated
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_schedule.clinic_date
	 * @return  the value of t_schedule.clinic_date
	 * @mbg.generated
	 */
	public Date getClinicDate() {
		return clinicDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_schedule.clinic_date
	 * @param clinicDate  the value for t_schedule.clinic_date
	 * @mbg.generated
	 */
	public void setClinicDate(Date clinicDate) {
		this.clinicDate = clinicDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_schedule.clinic_time
	 * @return  the value of t_schedule.clinic_time
	 * @mbg.generated
	 */
	public String getClinicTime() {
		return clinicTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_schedule.clinic_time
	 * @param clinicTime  the value for t_schedule.clinic_time
	 * @mbg.generated
	 */
	public void setClinicTime(String clinicTime) {
		this.clinicTime = clinicTime == null ? null : clinicTime.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_schedule.clinic_week
	 * @return  the value of t_schedule.clinic_week
	 * @mbg.generated
	 */
	public Integer getClinicWeek() {
		return clinicWeek;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_schedule.clinic_week
	 * @param clinicWeek  the value for t_schedule.clinic_week
	 * @mbg.generated
	 */
	public void setClinicWeek(Integer clinicWeek) {
		this.clinicWeek = clinicWeek;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_schedule.clinic_type
	 * @return  the value of t_schedule.clinic_type
	 * @mbg.generated
	 */
	public Integer getClinicType() {
		return clinicType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_schedule.clinic_type
	 * @param clinicType  the value for t_schedule.clinic_type
	 * @mbg.generated
	 */
	public void setClinicType(Integer clinicType) {
		this.clinicType = clinicType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_schedule.clinic_status
	 * @return  the value of t_schedule.clinic_status
	 * @mbg.generated
	 */
	public Integer getClinicStatus() {
		return clinicStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_schedule.clinic_status
	 * @param clinicStatus  the value for t_schedule.clinic_status
	 * @mbg.generated
	 */
	public void setClinicStatus(Integer clinicStatus) {
		this.clinicStatus = clinicStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_schedule.clinic_no
	 * @return  the value of t_schedule.clinic_no
	 * @mbg.generated
	 */
	public Integer getClinicNo() {
		return clinicNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_schedule.clinic_no
	 * @param clinicNo  the value for t_schedule.clinic_no
	 * @mbg.generated
	 */
	public void setClinicNo(Integer clinicNo) {
		this.clinicNo = clinicNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_schedule.clinic_fee
	 * @return  the value of t_schedule.clinic_fee
	 * @mbg.generated
	 */
	public BigDecimal getClinicFee() {
		return clinicFee;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_schedule.clinic_fee
	 * @param clinicFee  the value for t_schedule.clinic_fee
	 * @mbg.generated
	 */
	public void setClinicFee(BigDecimal clinicFee) {
		this.clinicFee = clinicFee;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_schedule.create_time
	 * @return  the value of t_schedule.create_time
	 * @mbg.generated
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_schedule.create_time
	 * @param createTime  the value for t_schedule.create_time
	 * @mbg.generated
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_schedule.update_time
	 * @return  the value of t_schedule.update_time
	 * @mbg.generated
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_schedule.update_time
	 * @param updateTime  the value for t_schedule.update_time
	 * @mbg.generated
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_schedule
	 * @mbg.generated
	 */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		Schedule other = (Schedule) that;
		return (this.getScheduleId() == null ? other.getScheduleId() == null
				: this.getScheduleId().equals(other.getScheduleId()))
				&& (this.getHospitalId() == null ? other.getHospitalId() == null
						: this.getHospitalId().equals(other.getHospitalId()))
				&& (this.getDoctorId() == null ? other.getDoctorId() == null
						: this.getDoctorId().equals(other.getDoctorId()))
				&& (this.getDepartmentId() == null ? other.getDepartmentId() == null
						: this.getDepartmentId().equals(other.getDepartmentId()))
				&& (this.getClinicDate() == null ? other.getClinicDate() == null
						: this.getClinicDate().equals(other.getClinicDate()))
				&& (this.getClinicTime() == null ? other.getClinicTime() == null
						: this.getClinicTime().equals(other.getClinicTime()))
				&& (this.getClinicWeek() == null ? other.getClinicWeek() == null
						: this.getClinicWeek().equals(other.getClinicWeek()))
				&& (this.getClinicType() == null ? other.getClinicType() == null
						: this.getClinicType().equals(other.getClinicType()))
				&& (this.getClinicStatus() == null ? other.getClinicStatus() == null
						: this.getClinicStatus().equals(other.getClinicStatus()))
				&& (this.getClinicNo() == null ? other.getClinicNo() == null
						: this.getClinicNo().equals(other.getClinicNo()))
				&& (this.getClinicFee() == null ? other.getClinicFee() == null
						: this.getClinicFee().equals(other.getClinicFee()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null
						: this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getUpdateTime() == null ? other.getUpdateTime() == null
						: this.getUpdateTime().equals(other.getUpdateTime()));
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_schedule
	 * @mbg.generated
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getScheduleId() == null) ? 0 : getScheduleId().hashCode());
		result = prime * result + ((getHospitalId() == null) ? 0 : getHospitalId().hashCode());
		result = prime * result + ((getDoctorId() == null) ? 0 : getDoctorId().hashCode());
		result = prime * result + ((getDepartmentId() == null) ? 0 : getDepartmentId().hashCode());
		result = prime * result + ((getClinicDate() == null) ? 0 : getClinicDate().hashCode());
		result = prime * result + ((getClinicTime() == null) ? 0 : getClinicTime().hashCode());
		result = prime * result + ((getClinicWeek() == null) ? 0 : getClinicWeek().hashCode());
		result = prime * result + ((getClinicType() == null) ? 0 : getClinicType().hashCode());
		result = prime * result + ((getClinicStatus() == null) ? 0 : getClinicStatus().hashCode());
		result = prime * result + ((getClinicNo() == null) ? 0 : getClinicNo().hashCode());
		result = prime * result + ((getClinicFee() == null) ? 0 : getClinicFee().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
		return result;
	}
}