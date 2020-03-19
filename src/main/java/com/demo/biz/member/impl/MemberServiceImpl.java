package com.demo.biz.member.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.biz.common.LoginDTO;
import com.demo.biz.member.MemberDAO;
import com.demo.biz.member.MemberService;
import com.demo.biz.member.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	private final MemberDAO dao;
	
	private final BCryptPasswordEncoder crptPassEnc; 
	
	@Autowired
	public MemberServiceImpl(MemberDAO dao, BCryptPasswordEncoder crptPassEnc) {
		this.dao = dao;
		this.crptPassEnc = crptPassEnc;
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
	public void insertMember(MemberVO vo) {
		// TODO Auto-generated method stub
		dao.insertMember(vo);
	}


}
