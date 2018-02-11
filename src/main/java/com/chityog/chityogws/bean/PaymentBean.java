package com.chityog.chityogws.bean;

import java.io.Serializable;


@SuppressWarnings("serial")
public class PaymentBean implements Serializable {

	private String create_time;
	private String id;
	private String state;
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	

}
