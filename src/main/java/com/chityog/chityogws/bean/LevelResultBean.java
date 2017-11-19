package com.chityog.chityogws.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LevelResultBean implements Serializable {

	private Long levelResultId;

	private Double levelResultPercent;

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
	
	

}
