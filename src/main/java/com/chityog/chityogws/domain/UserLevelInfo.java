package com.chityog.chityogws.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "user_level")
@GenericGenerator(name = "UserLevelInfo", strategy = "org.hibernate.id.enhanced.TableGenerator", parameters = {
		@Parameter(name = "segment_value", value = "user_level"),
		@Parameter(name = "increment_size", value = "10"),
		@Parameter(name = "optimizer", value = "pooled") })
@SuppressWarnings("serial")
public class UserLevelInfo implements Serializable {

	@Id
	@GeneratedValue(generator = "UserLevelInfo")
	@Column(name = "USER_LEVEL_ID")
	private Long userLevelId;

	@Column(name = "USER_LEVEL")
	private String userLevel;

	@Column(name = "USER_DAYS_TOTAL")
	private Integer totalNumberOfDays;

	@Column(name = "USER_DAYS_COMPLETED")
	private Integer completedNumberOfDays;

	@Column(name = "USER_DAYS_SKIPPED")
	private Integer skippedNumberOfDays;

	@Column(name = "USER_LEVEL_START_DATE")
	private Date startDate;

	@Column(name = "USER_LEVEL_RESULT")
	private String isResult;

	@Column(name = "NUMBER_OF_TRUE")
	private Integer numberOfTrue;

	@Column(name = "NUMBER_OF_QUESTIONS")
	private Integer totalNumberOfQuestions;
	
	@Column(name = "USER_DAYS_ATTENDED")
	private Integer attendedNumberOfDays;
	
	@Column(name = "USER_SUB_LEVEL")
	private String userSubLevel;
	
	@Column(name = "IS_EXTRA_RESULT")
	private Integer isExtraResult;

	@OneToOne
	@JoinColumn(name = "USER_ID")
	private UserInfo userInfo;

	public Integer getIsExtraResult() {
		return isExtraResult;
	}

	public void setIsExtraResult(Integer isExtraResult) {
		this.isExtraResult = isExtraResult;
	}

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

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
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
	
	
	
	
	
	

}