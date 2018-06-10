package com.chityog.chityogws.bean;

import java.io.Serializable;
import java.util.Map;

@SuppressWarnings("serial")
public class ResetPasswordResponse implements Serializable {
	
	private UserBean userBean;
	private Map<String, String> errorMessages;
	
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	public Map<String, String> getErrorMessages() {
		return errorMessages;
	}
	public void setErrorMessages(Map<String, String> errorMessages) {
		this.errorMessages = errorMessages;
	}
	
	

}
