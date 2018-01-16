package com.hospital.register.vo;

import java.io.Serializable;
import java.util.Date;

public class BonusDetailVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8073771093496615396L;
	
	private Date completeTime;
	
	private Integer SubId;
	
	private Integer settleMode;
	
	private Integer settleAmount;
	
	private Integer bonusPoints;
	
	private Integer totalPoints;

	public BonusDetailVO() {
	}
	
	
	public BonusDetailVO(Date completeTime, Integer subId, Integer settleMode, Integer settleAmount,
			Integer bonusPoints, Integer totalPoints) {
		super();
		this.completeTime = completeTime;
		SubId = subId;
		this.settleMode = settleMode;
		this.settleAmount = settleAmount;
		this.bonusPoints = bonusPoints;
		this.totalPoints = totalPoints;
	}


	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public Integer getSubId() {
		return SubId;
	}

	public void setSubId(Integer subId) {
		SubId = subId;
	}

	public Integer getSettleMode() {
		return settleMode;
	}

	public void setSettleMode(Integer settleMode) {
		this.settleMode = settleMode;
	}

	public Integer getSettleAmount() {
		return settleAmount;
	}

	public void setSettleAmount(Integer settleAmount) {
		this.settleAmount = settleAmount;
	}

	public Integer getBonusPoints() {
		return bonusPoints;
	}

	public void setBonusPoints(Integer bonusPoints) {
		this.bonusPoints = bonusPoints;
	}

	public Integer getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Integer totalPoints) {
		this.totalPoints = totalPoints;
	}
	

}
