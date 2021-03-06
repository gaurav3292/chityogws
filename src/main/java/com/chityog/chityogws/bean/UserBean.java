package com.chityog.chityogws.bean;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class UserBean implements Serializable {

	private Long userId;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String password;
	private String deviceType;
	private String deviceToken;
	private String gender;
	private String country;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	private String otp;
	private int numberOfTrue;
	private int totalNumberOfQuestions;
	private String profilePic;
	private String isEmailVerify;
	private String token;
	private String levelNumber;
	private Date date;
	private String isResult;
	private String lineRating;
	private UserLevelBean level;
	private PaymentBean payment;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public int getNumberOfTrue() {
		return numberOfTrue;
	}

	public void setNumberOfTrue(int numberOfTrue) {
		this.numberOfTrue = numberOfTrue;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public UserLevelBean getLevel() {
		return level;
	}

	public void setLevel(UserLevelBean level) {
		this.level = level;
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

	public String getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(String levelNumber) {
		this.levelNumber = levelNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTotalNumberOfQuestions() {
		return totalNumberOfQuestions;
	}

	public void setTotalNumberOfQuestions(int totalNumberOfQuestions) {
		this.totalNumberOfQuestions = totalNumberOfQuestions;
	}

	public String getIsResult() {
		return isResult;
	}

	public void setIsResult(String isResult) {
		this.isResult = isResult;
	}

	public String getLineRating() {
		return lineRating;
	}

	public void setLineRating(String lineRating) {
		this.lineRating = lineRating;
	}

	public PaymentBean getPayment() {
		return payment;
	}

	public void setPayment(PaymentBean payment) {
		this.payment = payment;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	
	

	
}
