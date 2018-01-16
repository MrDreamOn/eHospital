package com.hospital.register.conditionVO;

public class SystemVO {

    private int userCount;//注册人数
    
    private int patientCount;//就诊人数
    
    private int subCount;//预约人数
    
    private int cancelSubCount;//取消数
    
    private int realCount;//实际预约人数

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public int getPatientCount() {
        return patientCount;
    }

    public void setPatientCount(int patientCount) {
        this.patientCount = patientCount;
    }

    public int getSubCount() {
        return subCount;
    }

    public void setSubCount(int subCount) {
        this.subCount = subCount;
    }

    public int getCancelSubCount() {
        return cancelSubCount;
    }

    public void setCancelSubCount(int cancelSubCount) {
        this.cancelSubCount = cancelSubCount;
    }

    public int getRealCount() {
        return realCount;
    }

    public void setRealCount(int realCount) {
        this.realCount = realCount;
    }
    
    
    
    
}
