package com.demo.biz.member.impl;

import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.demo.biz.common.EmailDTO;
import com.demo.biz.member.EmailService;
import com.demo.biz.member.MemberDAO;
import com.demo.biz.member.MemberVO;

@Service
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender mailSender;
	
	private final MemberDAO dao;
	
	@Autowired
	public EmailServiceImpl(JavaMailSender mailSender, MemberDAO dao) {
		this.mailSender = mailSender;
		this.dao = dao;
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
	
	public MemberVO getMember(String mbId) {
		return dao.getMember(mbId);
	}
	
	public List<MemberVO> getAllMembers() {
		return dao.getAllMembers();
	}
}