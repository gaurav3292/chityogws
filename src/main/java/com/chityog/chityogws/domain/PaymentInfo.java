package com.chityog.chityogws.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "user_level_payment")
@GenericGenerator(name = "PaymentInfo", strategy = "org.hibernate.id.enhanced.TableGenerator", parameters = {
		@Parameter(name = "segment_value", value = "user_level_payment"),
		@Parameter(name = "increment_size", value = "10"),
		@Parameter(name = "optimizer", value = "pooled") })
@SuppressWarnings("serial")
public class PaymentInfo implements Serializable{
	
	@Id
	@GeneratedValue(generator = "PaymentInfo")
	@Column(name = "USER_LEVEL_PAYMENT_ID")
	private Long userLevelPaymentId;
	
	@Column(name = "USER_LEVEL")
	private String userLevel;
	
	@Column(name = "PAYPAL_ID")
	private String paypalId;
	
	@Column(name = "CREATE_TIME")
	private String createTime;
	
	@Column(name = "STATE")
	private String state;
	
	@OneToOne
	@JoinColumn(name = "USER_ID")
	private UserInfo userInfo;

	public Long getUserLevelPaymentId() {
		return userLevelPaymentId;
	}

	public void setUserLevelPaymentId(Long userLevelPaymentId) {
		this.userLevelPaymentId = userLevelPaymentId;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getPaypalId() {
		return paypalId;
	}

	public void setPaypalId(String paypalId) {
		this.paypalId = paypalId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	
	

}
