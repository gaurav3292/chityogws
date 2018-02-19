package com.chityog.chityogws.mail;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailMail {

	private MailSender mailSender;
	private String FOOTER = "\n\n\nThanks & Regards\n"+"Chit Yog Sadhana Team";
	private String from  = "chityogsadhanaapp@gmail.com";

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendMail(String to, String subject, String msg,String replyTo) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg+FOOTER);
		message.setReplyTo(replyTo);
		mailSender.send(message);
	}

}
