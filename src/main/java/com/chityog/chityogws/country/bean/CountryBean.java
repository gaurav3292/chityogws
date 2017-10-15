package com.chityog.chityogws.country.bean;

import java.io.Serializable;

public class CountryBean implements Serializable {
	private Long countryId;
	private String countryName;

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}
