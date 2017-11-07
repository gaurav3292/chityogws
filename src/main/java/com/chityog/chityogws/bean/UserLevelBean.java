package com.chityog.chityogws.bean;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class UserLevelBean implements Serializable {

	private Long userLevelId;
	private String userLevel;
	private String totalNumberOfDays;
	private String completedNumberOfDays;
	private String skippedNumberOfDays;
	private Date startDate;
	private String isResult;
	private int numberOfTrue;

	public Long getUserLevelId() {
		return userLevelId;
	}

	public void setUserLevelId(Long userLevelId) {
		this.userLevelId = userLevelId;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getTotalNumberOfDays() {
		return totalNumberOfDays;
	}

	public void setTotalNumberOfDays(String totalNumberOfDays) {
		this.totalNumberOfDays = totalNumberOfDays;
	}

	public String getCompletedNumberOfDays() {
		return completedNumberOfDays;
	}

	public void setCompletedNumberOfDays(String completedNumberOfDays) {
		this.completedNumberOfDays = completedNumberOfDays;
	}

	public String getSkippedNumberOfDays() {
		return skippedNumberOfDays;
	}

	public void setSkippedNumberOfDays(String skippedNumberOfDays) {
		this.skippedNumberOfDays = skippedNumberOfDays;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getIsResult() {
		return isResult;
	}

	public void setIsResult(String isResult) {
		this.isResult = isResult;
	}

	public int getNumberOfTrue() {
		return numberOfTrue;
	}

	public void setNumberOfTrue(int numberOfTrue) {
		this.numberOfTrue = numberOfTrue;
	}
	
	

}
