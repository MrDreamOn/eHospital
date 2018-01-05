package com.hospital.register.conditionVO;

public class ScheduleVO {

    private int    scheduleId;

    private String dateStr;

    private String weekStr;

    private int    clinicNo;
    
    private String clincTime;

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getWeekStr() {
        return weekStr;
    }

    public void setWeekStr(String weekStr) {
        this.weekStr = weekStr;
    }

    public int getClinicNo() {
        return clinicNo;
    }

    public void setClinicNo(int clinicNo) {
        this.clinicNo = clinicNo;
    }

    public String getClincTime() {
        return clincTime;
    }

    public void setClincTime(String clincTime) {
        this.clincTime = clincTime;
    }
    
    

}