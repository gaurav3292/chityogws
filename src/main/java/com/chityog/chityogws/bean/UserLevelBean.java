package com.chityog.chityogws.bean;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class UserLevelBean implements Serializable {

	private Long userLevelId;
	private String userLevel;
	private Integer totalNumberOfDays;
	private Integer completedNumberOfDays;
	private Integer skippedNumberOfDays;
	private Date startDate;
	private String isResult;
	private Integer numberOfTrue;
	private Integer totalNumberOfQuestions;
	private Integer attendedNumberOfDays;
	private Integer isExtraResult;
	private String userSubLevel;
	private Boolean isPaymentRequired;
    private Boolean isPaymentMade;
	
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

	public Integer getTotalNumberOfDays() {
		return totalNumberOfDays;
	}

	public void setTotalNumberOfDays(Integer totalNumberOfDays) {
		this.totalNumberOfDays = totalNumberOfDays;
	}

	public Integer getCompletedNumberOfDays() {
		return completedNumberOfDays;
	}

	public void setCompletedNumberOfDays(Integer completedNumberOfDays) {
		this.completedNumberOfDays = completedNumberOfDays;
	}

	public Integer getSkippedNumberOfDays() {
		return skippedNumberOfDays;
	}

	public void setSkippedNumberOfDays(Integer skippedNumberOfDays) {
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

	public Integer getNumberOfTrue() {
		return numberOfTrue;
	}

	public void setNumberOfTrue(Integer numberOfTrue) {
		this.numberOfTrue = numberOfTrue;
	}

	public Integer getTotalNumberOfQuestions() {
		return totalNumberOfQuestions;
	}

	public void setTotalNumberOfQuestions(Integer totalNumberOfQuestions) {
		this.totalNumberOfQuestions = totalNumberOfQuestions;
	}

	public Integer getAttendedNumberOfDays() {
		return attendedNumberOfDays;
	}

	public void setAttendedNumberOfDays(Integer attendedNumberOfDays) {
		this.attendedNumberOfDays = attendedNumberOfDays;
	}

	public String getUserSubLevel() {
		return userSubLevel;
	}

	public void setUserSubLevel(String userSubLevel) {
		this.userSubLevel = userSubLevel;
	}

	public Integer getIsExtraResult() {
		return isExtraResult;
	}

	public void setIsExtraResult(Integer isExtraResult) {
		this.isExtraResult = isExtraResult;
	}

	public Boolean getIsPaymentRequired() {
		return isPaymentRequired;
	}

	public void setIsPaymentRequired(Boolean isPaymentRequired) {
		this.isPaymentRequired = isPaymentRequired;
	}

	public Boolean getIsPaymentMade() {
		return isPaymentMade;
	}

	public void setIsPaymentMade(Boolean isPaymentMade) {
		this.isPaymentMade = isPaymentMade;
	}

	
	
	
	
	

	
}
