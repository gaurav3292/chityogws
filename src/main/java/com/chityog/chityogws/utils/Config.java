package com.chityog.chityogws.utils;

public class Config {
	
	public static final String SUCCESS = "SUCCESS";
	public static final String ERROR = "ERROR";
	
	public static final String EMAIL_REGEX = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
	
	public static final String YES = "YES";
	public static final String NO = "NO";
	
	public static final String TEST_URL = "http://localhost:8080/chityogws";
	public static final String LIVE_URL = "http://54.244.1.194:8080/chityogws";
	
	public static final String IMAGE_TEST_URL = "http://localhost:8080/chityogws/resources";
	public static final String IMAGE_LIVE_URL = "http://54.244.1.194:8080/chityogws/resources";
	
	public static final String EMAIL_VERIFY_STR = "Please click on below link to verify your email\n\n";

	
	public static final String USER_REGISTRAION = "Dear %s\n\n Thank you for registering yourself on Chit Yog Sadhana App. You have registered with the following details in the application.\n\n";
	
	public static final String ADMIN_REGISTRATION = "New user with following details has signed up \n\n";
	
	public static final String ADMIN_REG_SUBJECT = "New user signed up on Chit Yog Sadhana App";
	
	public static final String PAYMENT_SUBJECT_ADMIN = "Payment received by %s for level %s";
	
	public static final String PAYMENT_SUBJECT_USER = "Payment successful";
	
	public static final String PAYMENT_MSG_ADMIN = "User has made the payment for unlocking Level %s\n\n";
	
	public static final String PAYMENT_MSG_USER = "Dear %s\n\nYour payment of %s for unlocking Level %s has been received and same has been unlocked in your application.\n\n";
	
	public static final String DETAILS = "NAME: %s\n"
			+"Email Id: %s\n"
			+"Address: %s\n"
			+"Contact Number: %s\n\n\n";
	
}
