package com.demo.biz.member.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.biz.member.MemberDAO;
import com.demo.biz.member.MemberService;
import com.demo.biz.member.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	private final MemberDAO dao;
	
	@Autowired
	public MemberServiceImpl(MemberDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public void insertMember(MemberVO vo) {
		// TODO Auto-generated method stub
		dao.insertMember(vo);
	}

}
