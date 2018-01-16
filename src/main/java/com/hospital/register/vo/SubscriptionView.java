package com.hospital.register.vo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.druid.util.StringUtils;

public class SubscriptionView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8270704112256961353L;

	private String subscriptionId;

	private Date createTime;

	private long subscriptionFee;

	private String subscriptionSource;

	private Integer subscriptionStatus;

	private String statusStr;

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public long getSubscriptionFee() {
		return subscriptionFee;
	}

	public void setSubscriptionFee(long subscriptionFee) {
		this.subscriptionFee = subscriptionFee;
	}

	public String getSubscriptionSource() {
		return subscriptionSource;
	}

	public void setSubscriptionSource(String subscriptionSource) {
		if(!StringUtils.isEmpty(subscriptionSource)) {
			switch (subscriptionSource) {
			case "1":
				this.subscriptionSource = "微信预约";
				break;
			case "2":
				this.subscriptionSource = "现场挂号";
				break;
			default:
				break;
			}
			
		}
	}

	public Integer getSubscriptionStatus() {
		return subscriptionStatus;
	}

	public void setSubscriptionStatus(Integer subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
		switch (subscriptionStatus) {
		case 0:
			this.statusStr = "预约成功";
			break;
		case 1:
			this.statusStr = "就诊挂号";
			break;
		case 4:
			this.statusStr = "预约取消";
			break;
		case 5:
			this.statusStr = "挂号取消";
			break;
		case 6:
			this.statusStr = "结算完成";
			break;
		default:
			break;
		}
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

}