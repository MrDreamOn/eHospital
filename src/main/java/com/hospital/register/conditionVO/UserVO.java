package com.hospital.register.conditionVO;

import java.util.Date;

import com.hospital.register.util.DateUtil;

public class UserVO {

	private int userId;

	private String realName;

	private String telphone;

	private String idCard;

	private int sex;

	private Date brithday;
	
	private String brithdayStr;

	private String status;// 1-预约 2-挂号

	private int subscriptionId;

	private String age;

	private String sexStr;

	// 最近成功就诊时间
	private Date lastSuccessDate;
	
	private Date createTime;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
		this.sexStr = sex == 1 ? "男" : "女";
	}

	public Date getBrithday() {
		return brithday;
	}

	public void setBrithday(Date brithday) {
		this.brithday = brithday;
		if(this.brithday != null) {
			int ageInt = DateUtil.getAge(this.brithday);
			this.age = ageInt+"岁";
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSexStr() {
		return sexStr;
	}

	public void setSexStr(String sexStr) {
		this.sexStr = sexStr;
	}

	public Date getLastSuccessDate() {
		return lastSuccessDate;
	}

	public void setLastSuccessDate(Date lastSuccessDate) {
		this.lastSuccessDate = lastSuccessDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

    public String getBrithdayStr() {
        return brithdayStr;
    }

    public void setBrithdayStr(String brithdayStr) {
        this.brithdayStr = brithdayStr;
    }

	
}
