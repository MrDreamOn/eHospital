package com.hospital.register.conditionVO;

import java.util.Date;

import com.hospital.register.bean.Subscription;
import com.hospital.register.bean.User;
import com.hospital.register.util.DateUtil;

/**
 * 
 * 管理后台查询预约记录条件
 * @author caiwei
 * @version $Id: RegisterVO.java, v 0.1 2017年12月12日 下午10:38:20 caiwei Exp $
 */
public class RegisterVO extends BaseVO {

	private String 		 doctorName;  //预约坐诊医生姓名

    private String       telphone;    //手机号

    private String       startDate;   //就诊开始时间

    private String       endDate;     //就诊结束时间

    private String       status;      //就诊状态

    private String       isRegister;  //1-表示未注册  2-表示已注册 3-预约转挂号

    private User         user;//患者信息
    
    private int          age; //年龄
    
    private String       nowDate;// 当前日期

    private Subscription subscription;//预约信息
    
    private String       createTime;//下单时间
    
    private String       clinicTime;//就诊时间

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

    public String getIsRegister() {
        return isRegister;
    }

    public void setIsRegister(String isRegister) {
        this.isRegister = isRegister;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNowDate() {
        this.nowDate = DateUtil.formatDate(new Date());
        return nowDate;
    }

    public void setNowDate(String nowDate) {
        this.nowDate = nowDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getClinicTime() {
        return clinicTime;
    }

    public void setClinicTime(String clinicTime) {
        this.clinicTime = clinicTime;
    }

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
}
