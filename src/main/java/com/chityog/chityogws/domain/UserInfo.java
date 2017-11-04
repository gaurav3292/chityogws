package com.chityog.chityogws.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "user")
@GenericGenerator(name = "UserInfo", strategy = "org.hibernate.id.enhanced.TableGenerator", parameters = {
		@Parameter(name = "segment_value", value = "user"),
		@Parameter(name = "increment_size", value = "10"),
		@Parameter(name = "optimizer", value = "pooled") })
@SuppressWarnings("serial")
public class UserInfo implements Serializable {

	@Id
	@GeneratedValue(generator = "UserInfo")
	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "DEVICE_TYPE")
	private String deviceType;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "NUMBER_OF_TRUE")
	private Integer numberOfTrue;

	@Column(name = "EMAIL_VERIFY")
	private String isEmailVerify;

	@Column(name = "EMAIL_TOKEN")
	private String token;

	@Column(name = "PROFILE_PIC")
	private String profilePic;
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getNumberOfTrue() {
		return numberOfTrue;
	}

	public void setNumberOfTrue(Integer numberOfTrue) {
		this.numberOfTrue = numberOfTrue;
	}

	public String getIsEmailVerify() {
		return isEmailVerify;
	}

	public void setIsEmailVerify(String isEmailVerify) {
		this.isEmailVerify = isEmailVerify;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
