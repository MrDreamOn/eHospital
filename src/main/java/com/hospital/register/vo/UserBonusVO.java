package com.hospital.register.vo;

import java.io.Serializable;
import java.util.Date;

public class UserBonusVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3473633419344274682L;

	private int userId;

	private String realName;

	private String telephone;

	private int bonusPoints;

	private Date completeTime;

	public UserBonusVO() {
	}

	public UserBonusVO(int userId, String realName, String telephone, int bonusPoints, Date completeTime) {
		super();
		this.userId = userId;
		this.realName = realName;
		this.telephone = telephone;
		this.bonusPoints = bonusPoints;
		this.completeTime = completeTime;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getBonusPoints() {
		return bonusPoints;
	}

	public void setBonusPoints(int bonusPoints) {
		this.bonusPoints = bonusPoints;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

}
