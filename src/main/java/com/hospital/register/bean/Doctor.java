package com.hospital.register.bean;

import java.io.Serializable;
import java.util.Date;

public class Doctor implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_doctor.doctor_id
     *
     * @mbg.generated
     */
    private Integer doctorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_doctor.doctor_name
     *
     * @mbg.generated
     */
    private String doctorName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_doctor.doctor_code
     *
     * @mbg.generated
     */
    private String doctorCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_doctor.department_id
     *
     * @mbg.generated
     */
    private Integer departmentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_doctor.phone
     *
     * @mbg.generated
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_doctor.position_name
     *
     * @mbg.generated
     */
    private String positionName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_doctor.job_name
     *
     * @mbg.generated
     */
    private String jobName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_doctor.hospital_id
     *
     * @mbg.generated
     */
    private Integer hospitalId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_doctor.doctor_specialty
     *
     * @mbg.generated
     */
    private String doctorSpecialty;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_doctor.doctor_summary
     *
     * @mbg.generated
     */
    private String doctorSummary;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_doctor.doctor_honor
     *
     * @mbg.generated
     */
    private String doctorHonor;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_doctor.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_doctor.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_doctor
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_doctor
     *
     * @mbg.generated
     */
    public Doctor(Integer doctorId, String doctorName, String doctorCode, Integer departmentId, String phone, String positionName, String jobName, Integer hospitalId, String doctorSpecialty, String doctorSummary, String doctorHonor, Date createTime, Date updateTime) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorCode = doctorCode;
        this.departmentId = departmentId;
        this.phone = phone;
        this.positionName = positionName;
        this.jobName = jobName;
        this.hospitalId = hospitalId;
        this.doctorSpecialty = doctorSpecialty;
        this.doctorSummary = doctorSummary;
        this.doctorHonor = doctorHonor;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_doctor
     *
     * @mbg.generated
     */
    public Doctor() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_doctor.doctor_id
     *
     * @return the value of t_doctor.doctor_id
     *
     * @mbg.generated
     */
    public Integer getDoctorId() {
        return doctorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_doctor.doctor_id
     *
     * @param doctorId the value for t_doctor.doctor_id
     *
     * @mbg.generated
     */
    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_doctor.doctor_name
     *
     * @return the value of t_doctor.doctor_name
     *
     * @mbg.generated
     */
    public String getDoctorName() {
        return doctorName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_doctor.doctor_name
     *
     * @param doctorName the value for t_doctor.doctor_name
     *
     * @mbg.generated
     */
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName == null ? null : doctorName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_doctor.doctor_code
     *
     * @return the value of t_doctor.doctor_code
     *
     * @mbg.generated
     */
    public String getDoctorCode() {
        return doctorCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_doctor.doctor_code
     *
     * @param doctorCode the value for t_doctor.doctor_code
     *
     * @mbg.generated
     */
    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode == null ? null : doctorCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_doctor.department_id
     *
     * @return the value of t_doctor.department_id
     *
     * @mbg.generated
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_doctor.department_id
     *
     * @param departmentId the value for t_doctor.department_id
     *
     * @mbg.generated
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_doctor.phone
     *
     * @return the value of t_doctor.phone
     *
     * @mbg.generated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_doctor.phone
     *
     * @param phone the value for t_doctor.phone
     *
     * @mbg.generated
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_doctor.position_name
     *
     * @return the value of t_doctor.position_name
     *
     * @mbg.generated
     */
    public String getPositionName() {
        return positionName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_doctor.position_name
     *
     * @param positionName the value for t_doctor.position_name
     *
     * @mbg.generated
     */
    public void setPositionName(String positionName) {
        this.positionName = positionName == null ? null : positionName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_doctor.job_name
     *
     * @return the value of t_doctor.job_name
     *
     * @mbg.generated
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_doctor.job_name
     *
     * @param jobName the value for t_doctor.job_name
     *
     * @mbg.generated
     */
    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_doctor.hospital_id
     *
     * @return the value of t_doctor.hospital_id
     *
     * @mbg.generated
     */
    public Integer getHospitalId() {
        return hospitalId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_doctor.hospital_id
     *
     * @param hospitalId the value for t_doctor.hospital_id
     *
     * @mbg.generated
     */
    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_doctor.doctor_specialty
     *
     * @return the value of t_doctor.doctor_specialty
     *
     * @mbg.generated
     */
    public String getDoctorSpecialty() {
        return doctorSpecialty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_doctor.doctor_specialty
     *
     * @param doctorSpecialty the value for t_doctor.doctor_specialty
     *
     * @mbg.generated
     */
    public void setDoctorSpecialty(String doctorSpecialty) {
        this.doctorSpecialty = doctorSpecialty == null ? null : doctorSpecialty.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_doctor.doctor_summary
     *
     * @return the value of t_doctor.doctor_summary
     *
     * @mbg.generated
     */
    public String getDoctorSummary() {
        return doctorSummary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_doctor.doctor_summary
     *
     * @param doctorSummary the value for t_doctor.doctor_summary
     *
     * @mbg.generated
     */
    public void setDoctorSummary(String doctorSummary) {
        this.doctorSummary = doctorSummary == null ? null : doctorSummary.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_doctor.doctor_honor
     *
     * @return the value of t_doctor.doctor_honor
     *
     * @mbg.generated
     */
    public String getDoctorHonor() {
        return doctorHonor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_doctor.doctor_honor
     *
     * @param doctorHonor the value for t_doctor.doctor_honor
     *
     * @mbg.generated
     */
    public void setDoctorHonor(String doctorHonor) {
        this.doctorHonor = doctorHonor == null ? null : doctorHonor.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_doctor.create_time
     *
     * @return the value of t_doctor.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_doctor.create_time
     *
     * @param createTime the value for t_doctor.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_doctor.update_time
     *
     * @return the value of t_doctor.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_doctor.update_time
     *
     * @param updateTime the value for t_doctor.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_doctor
     *
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
        Doctor other = (Doctor) that;
        return (this.getDoctorId() == null ? other.getDoctorId() == null : this.getDoctorId().equals(other.getDoctorId()))
            && (this.getDoctorName() == null ? other.getDoctorName() == null : this.getDoctorName().equals(other.getDoctorName()))
            && (this.getDoctorCode() == null ? other.getDoctorCode() == null : this.getDoctorCode().equals(other.getDoctorCode()))
            && (this.getDepartmentId() == null ? other.getDepartmentId() == null : this.getDepartmentId().equals(other.getDepartmentId()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getPositionName() == null ? other.getPositionName() == null : this.getPositionName().equals(other.getPositionName()))
            && (this.getJobName() == null ? other.getJobName() == null : this.getJobName().equals(other.getJobName()))
            && (this.getHospitalId() == null ? other.getHospitalId() == null : this.getHospitalId().equals(other.getHospitalId()))
            && (this.getDoctorSpecialty() == null ? other.getDoctorSpecialty() == null : this.getDoctorSpecialty().equals(other.getDoctorSpecialty()))
            && (this.getDoctorSummary() == null ? other.getDoctorSummary() == null : this.getDoctorSummary().equals(other.getDoctorSummary()))
            && (this.getDoctorHonor() == null ? other.getDoctorHonor() == null : this.getDoctorHonor().equals(other.getDoctorHonor()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_doctor
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDoctorId() == null) ? 0 : getDoctorId().hashCode());
        result = prime * result + ((getDoctorName() == null) ? 0 : getDoctorName().hashCode());
        result = prime * result + ((getDoctorCode() == null) ? 0 : getDoctorCode().hashCode());
        result = prime * result + ((getDepartmentId() == null) ? 0 : getDepartmentId().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getPositionName() == null) ? 0 : getPositionName().hashCode());
        result = prime * result + ((getJobName() == null) ? 0 : getJobName().hashCode());
        result = prime * result + ((getHospitalId() == null) ? 0 : getHospitalId().hashCode());
        result = prime * result + ((getDoctorSpecialty() == null) ? 0 : getDoctorSpecialty().hashCode());
        result = prime * result + ((getDoctorSummary() == null) ? 0 : getDoctorSummary().hashCode());
        result = prime * result + ((getDoctorHonor() == null) ? 0 : getDoctorHonor().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}