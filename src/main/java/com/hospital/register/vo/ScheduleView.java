package com.hospital.register.vo;

import java.io.Serializable;
import java.util.Date;

public class ScheduleView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6051798361660492903L;
	
	private Date clinicDate;
	
	private String doctorName;
	
	private String clinicTimeMorning;
	
	private String clinicTimeAfternoon;
	
	private String morningLeft;
	
	private String afternoonLeft;
	
	private boolean clinicStatus;

	public Date getClinicDate() {
		return clinicDate;
	}

	public void setClinicDate(Date clinicDate) {
		this.clinicDate = clinicDate;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getClinicTimeMorning() {
		return clinicTimeMorning;
	}

	public void setClinicTimeMorning(String clinicTimeMorning) {
		this.clinicTimeMorning = clinicTimeMorning;
	}

	public String getClinicTimeAfternoon() {
		return clinicTimeAfternoon;
	}

	public void setClinicTimeAfternoon(String clinicTimeAfternoon) {
		this.clinicTimeAfternoon = clinicTimeAfternoon;
	}

	public String getMorningLeft() {
		return morningLeft;
	}

	public void setMorningLeft(String morningLeft) {
		this.morningLeft = morningLeft;
	}

	public String getAfternoonLeft() {
		return afternoonLeft;
	}

	public void setAfternoonLeft(String afternoonLeft) {
		this.afternoonLeft = afternoonLeft;
	}

	public boolean getClinicStatus() {
		return clinicStatus;
	}

	public void setClinicStatus(boolean clinicStatus) {
		this.clinicStatus = clinicStatus;
	}
	
	public ScheduleView() {
	}
}
