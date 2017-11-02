package com.chityog.chityogws.domain;

import java.io.Serializable;

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
public class UserLevelInfo implements Serializable{
	

	@Id
	@GeneratedValue(generator = "UserLevelInfo")
	@Column(name = "USER_LEVEL_ID")
	private Long userLevelId;
	
	@Column(name = "USER_LEVEL")
	private String userLevel;
	
	@Column(name = "USER_DAYS_TOTAL")
	private String totalNumberOfDays;
	
	@Column(name = "USER_DAYS_COMPLETED")
	private String completedNumberOfDays;
	
	@Column(name = "USER_DAYS_SKIPPED")
	private String skippedNumberOfDays;
	
	@OneToOne
	@JoinColumn(name = "USER_ID")
	private UserInfo userInfo;

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

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	

}
