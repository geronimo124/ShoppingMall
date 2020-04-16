package com.demo.biz.member;

import java.util.List;

import com.demo.biz.common.EmailDTO;

public interface EmailService {

	public void sendMail(EmailDTO dto);
	public MemberVO getMember(String mbId);
	public List<MemberVO> getAllMembers();
	
}