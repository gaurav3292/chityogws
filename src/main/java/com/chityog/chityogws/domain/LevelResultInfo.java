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
@Table(name = "level_result")
@GenericGenerator(name = "LevelResultInfo", strategy = "org.hibernate.id.enhanced.TableGenerator", parameters = {
		@Parameter(name = "segment_value", value = "level_result"),
		@Parameter(name = "increment_size", value = "10"),
		@Parameter(name = "optimizer", value = "pooled") })
@SuppressWarnings("serial")
public class LevelResultInfo implements Serializable{
	
	
	@Id
	@GeneratedValue(generator = "LevelResultInfo")
	@Column(name = "LEVEL_RESULT_ID")
	private Long levelResultId;
	
	@Column(name = "LEVEL_RESULT_PERCENT")
	private Double levelResultPercent;
	
	@Column(name = "LAST_SUBMITTION_DATE")
	private Date lastSubmittionDate;
	
	@OneToOne
	@JoinColumn(name = "USER_LEVEL_ID")
	private UserLevelInfo userLevelInfo;

	public Long getLevelResultId() {
		return levelResultId;
	}

	public void setLevelResultId(Long levelResultId) {
		this.levelResultId = levelResultId;
	}

	public Double getLevelResultPercent() {
		return levelResultPercent;
	}

	public void setLevelResultPercent(Double levelResultPercent) {
		this.levelResultPercent = levelResultPercent;
	}

	public UserLevelInfo getUserLevelInfo() {
		return userLevelInfo;
	}

	public void setUserLevelInfo(UserLevelInfo userLevelInfo) {
		this.userLevelInfo = userLevelInfo;
	}

	public Date getLastSubmittionDate() {
		return lastSubmittionDate;
	}

	public void setLastSubmittionDate(Date lastSubmittionDate) {
		this.lastSubmittionDate = lastSubmittionDate;
	}
	
	
	
	

}
