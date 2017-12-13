package com.hospital.register.conditionVO;

/**
 * 
 * 管理后台查询预约记录条件
 * @author caiwei
 * @version $Id: RegisterVO.java, v 0.1 2017年12月12日 下午10:38:20 caiwei Exp $
 */
public class RegisterVO {

    private String telphone;//手机号
    
    private String startDate;//就诊开始时间
    
    private String endDate;//就诊结束时间
    
    private String status;//就诊状态

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
