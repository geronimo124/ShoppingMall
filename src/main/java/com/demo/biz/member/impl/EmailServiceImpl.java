package com.demo.biz.member.impl;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.demo.biz.common.EmailDTO;
import com.demo.biz.member.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender mailSender;
	
	@Autowired
	public EmailServiceImpl(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@Override
	public void sendMail(EmailDTO dto) {
		// TODO Auto-generated method stub
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			
			msg.addRecipient(RecipientType.TO, new InternetAddress(dto.getReceiveMail()));
			
			msg.addFrom(new InternetAddress[] { new InternetAddress(dto.getSenderMail(), dto.getSenderName()) });
			
			msg.setSubject(dto.getSubject(), "utf-8");
			
			msg.setText(dto.getMessage(), "utf-8");
			
			mailSender.send(msg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}