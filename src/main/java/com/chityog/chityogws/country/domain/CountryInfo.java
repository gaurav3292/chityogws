package com.chityog.chityogws.country.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "chityog_country")
@GenericGenerator(name = "CountryInfo", strategy = "org.hibernate.id.enhanced.TableGenerator", parameters = {
		@Parameter(name = "segment_value", value = "chityog_country"),
		@Parameter(name = "increment_size", value = "10"),
		@Parameter(name = "optimizer", value = "pooled") })
@SuppressWarnings("serial")
public class CountryInfo implements Serializable{
	
	@Id
	@GeneratedValue(generator = "CountryInfo")
	@Column(name = "COUNTRY_ID")
	private Long countryId;
	
	@Column(name = "COUNTRY_NAME")
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
