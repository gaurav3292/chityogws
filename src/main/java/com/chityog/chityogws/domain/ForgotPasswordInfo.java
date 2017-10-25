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
@Table(name = "forgot_password")
@GenericGenerator(name = "ForgotPasswordInfo", strategy = "org.hibernate.id.enhanced.TableGenerator", parameters = {
		@Parameter(name = "segment_value", value = "forgot_password"),
		@Parameter(name = "increment_size", value = "10"),
		@Parameter(name = "optimizer", value = "pooled") })
@SuppressWarnings("serial")
public class ForgotPasswordInfo implements Serializable{
	

	@Id
	@GeneratedValue(generator = "ForgotPasswordInfo")
	@Column(name = "FORGOT_PASSWORD_ID")
	private Long forgotPasswordId;
	
	@Column(name = "FORGOT_PASSWORD_CODE")
	private String forgotPasswordCode;
	
	@OneToOne
	@JoinColumn(name = "USER_ID")
	private UserInfo userInfo;

	public Long getForgotPasswordId() {
		return forgotPasswordId;
	}

	public void setForgotPasswordId(Long forgotPasswordId) {
		this.forgotPasswordId = forgotPasswordId;
	}

	public String getForgotPasswordCode() {
		return forgotPasswordCode;
	}

	public void setForgotPasswordCode(String forgotPasswordCode) {
		this.forgotPasswordCode = forgotPasswordCode;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	

}
