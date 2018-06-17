package com.chityog.chityogws.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class JavaMail {

	private MailSender mailSender;
	private String FOOTER = "\n\n\nThanks & Regards\n"
			+ "Chit Yog Sadhana Team";
	private String from = "chityogsadhanaapp@gmail.com";
	private JavaMailSender javaMailSender;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}



	public void sendMail(String to, String subject, String msg, String replyTo) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg + FOOTER);
		message.setReplyTo(replyTo);
		mailSender.send(message);
	}

	public void sendResetPasswordMail(String to, String subject, String msg,
			String replyTo) throws MessagingException {

		SimpleMailMessage message = new SimpleMailMessage();

		MimeMessage mime = this.javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mime, true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);

		helper.setText(msg + FOOTER, true);
		message.setReplyTo("chityogsadhanaapp@gmail.com");
		this.javaMailSender.send(mime);

	}
}
