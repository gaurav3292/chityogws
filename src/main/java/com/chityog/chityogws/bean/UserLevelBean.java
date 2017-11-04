package com.chityog.chityogws.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserLevelBean implements Serializable {

	private Long userLevelId;
	private String userLevel;
	private String totalNumberOfDays;
	private String completedNumberOfDays;
	private String skippedNumberOfDays;

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

}
