package com.demo.biz.member.impl;

import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.biz.common.LoginDTO;
import com.demo.biz.common.SearchCriteria;
import com.demo.biz.member.MemberDAO;
import com.demo.biz.member.MemberService;
import com.demo.biz.member.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	private final MemberDAO dao;
	
	private final BCryptPasswordEncoder crptPassEnc; 
	
	private final JavaMailSender mailSender;
	
	@Autowired
	public MemberServiceImpl(MemberDAO dao, BCryptPasswordEncoder crptPassEnc, JavaMailSender mailSender) {
		this.dao = dao;
		this.crptPassEnc = crptPassEnc;
		this.mailSender = mailSender;
	}
	
	@Override
	public MemberVO loginMember(LoginDTO dto) {
		// TODO Auto-generated method stub
		MemberVO vo = dao.loginMember(dto);
		
		if(vo != null) {
			
			if(crptPassEnc.matches(dto.getPassword(), vo.getMbPw()))
				dao.updateConDate(dto);
			else
				vo = null;
		}
		
		return vo;
	}

	@Override
	public MemberVO loginMember(String mbEmail) {
		// TODO Auto-generated method stub
		return dao.loginMember(mbEmail);
	}
	
	@Override
	public int checkId(String mbId) {
		// TODO Auto-generated method stub
		return dao.checkId(mbId);
	}

	@Override
	public void insertMember(MemberVO vo) {
		// TODO Auto-generated method stub
		
		vo.setMbPw(crptPassEnc.encode(vo.getMbPw()));
		vo.setMbAuthkey(crptPassEnc.encode(vo.getMbId()));
		
		try {
			
			MimeMessage msg = mailSender.createMimeMessage();
			
			msg.addRecipient(RecipientType.TO, new InternetAddress(vo.getMbEmail()));
			
			msg.addFrom(new InternetAddress[] { new InternetAddress("temp@temp.com", "어쩌구쇼핑몰") });
			
			msg.setSubject("회원가입 인증키 메일", "utf-8");
			
			msg.setText(vo.getMbAuthkey(), "utf-8");
			
			mailSender.send(msg);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		dao.insertMember(vo);
	}

	@Override
	public MemberVO getMember(String mbId) {
		// TODO Auto-generated method stub
		return dao.getMember(mbId);
	}

	@Override
	public void updateAuth(String mbId) {
		// TODO Auto-generated method stub
		dao.updateAuth(mbId);
	}

	@Override
	public void updateMember(MemberVO vo) {
		// TODO Auto-generated method stub
		vo.setMbPw(crptPassEnc.encode(vo.getMbPw()));
		dao.updateMember(vo);
	}

	@Override
	public List<MemberVO> getMemberList(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return dao.getMemberList(cri);
	}

	@Override
	public int countMemberList(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return dao.countMemberList(cri);
	}

	@Transactional
	@Override
	public void deleteMembers(List<String> memberList) {
		// TODO Auto-generated method stub
		for(String mbId : memberList)
			dao.deleteMember(mbId);
		
	}

}
