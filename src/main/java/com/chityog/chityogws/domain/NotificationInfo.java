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
@Table(name = "user_notification")
@GenericGenerator(name = "NotificationInfo", strategy = "org.hibernate.id.enhanced.TableGenerator", parameters = {
		@Parameter(name = "segment_value", value = "user_notification"),
		@Parameter(name = "increment_size", value = "10"),
		@Parameter(name = "optimizer", value = "pooled") })
@SuppressWarnings("serial")
public class NotificationInfo implements Serializable {
	
	@Id
	@GeneratedValue(generator = "NotificationInfo")
	@Column(name = "NOTIFICATION_ID")
	private Long notificationId;
	
	@Column(name = "NOTIFICATION_MSG")
	private String notificationMsg;
	
	@Column(name = "NOTIFICATION_DATE")
	private Date notificationDate;
	
	@OneToOne
	@JoinColumn(name = "USER_ID")
	private UserInfo userInfo;

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public String getNotificationMsg() {
		return notificationMsg;
	}

	public void setNotificationMsg(String notificationMsg) {
		this.notificationMsg = notificationMsg;
	}

	public Date getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	
	
	

}
