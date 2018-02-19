package com.chityog.chityogws.mail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chityog.chityogws.domain.UserInfo;
import com.chityog.chityogws.domain.UserLevelInfo;
import com.chityog.chityogws.utils.Config;

public class CustomMail {

	ApplicationContext context = new ClassPathXmlApplicationContext(
			"spring_mail.xml");

	public void sendMailToUserNewUserRegistration(UserInfo user) {

		String msg = String.format(Config.USER_REGISTRAION, user.getName());
		String details = String.format(Config.DETAILS, user.getName(),
				user.getEmail(), user.getAddress(), user.getPhone());

		MailMail mm = (MailMail) context.getBean("mailMail");
		mm.sendMail(user.getEmail(), "User Registration", msg + details, null);
	}

	public void sendMailToAdminNewUserREgistration(UserInfo user) {

		String subject = Config.ADMIN_REG_SUBJECT;
		String msg = Config.ADMIN_REGISTRATION;
		String details = String.format(Config.DETAILS, user.getName(),
				user.getEmail(), user.getAddress(), user.getPhone());

		MailMail mm = (MailMail) context.getBean("mailMail");
		mm.sendMail("chityogsadhanaapp@gmail.com", subject, msg + details,
				user.getEmail());

	}

	public void sendPaymentReceivedMailToAdmin(UserInfo user,
			UserLevelInfo userLevel) {
		String subject = String.format(Config.PAYMENT_SUBJECT_ADMIN,
				user.getEmail(), userLevel.getUserLevel());
		String msg = String.format(Config.PAYMENT_MSG_ADMIN,
				userLevel.getUserLevel());
		String details = String.format(Config.DETAILS, user.getName(),
				user.getEmail(), user.getAddress(), user.getPhone());

		MailMail mm = (MailMail) context.getBean("mailMail");
		mm.sendMail("chityogsadhanaapp@gmail.com", subject, msg + details,
				user.getEmail());
	}

	public void sendPaymentReceivedMailToUser(UserInfo user,
			UserLevelInfo userLevel) {

		String price = null;
		if (userLevel.getUserLevel().equalsIgnoreCase("41")) {
			price = "NSD 5";
		} else {
			price = "NSD 25";
		}

		String subject = Config.PAYMENT_SUBJECT_USER;
		String msg = String.format(Config.PAYMENT_MSG_USER, user.getName(),
				price, userLevel.getUserLevel());

		MailMail mm = (MailMail) context.getBean("mailMail");
		mm.sendMail("chityogsadhanaapp@gmail.com", subject, msg, null);

	}

}
