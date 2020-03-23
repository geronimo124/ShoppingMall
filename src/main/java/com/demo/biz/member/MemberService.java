package com.demo.biz.member;

import java.util.List;

import com.demo.biz.common.LoginDTO;

public interface MemberService {

	public MemberVO loginMember(LoginDTO dto);
	public MemberVO getMember(String mbId);
	public void updateAuth(String mbId);
	public void updateMember(MemberVO vo);
	public int checkId(String mbId);
	public void insertMember(MemberVO vo);
	public List<BasketVO> getBaskets(String mbId);
	
}