package com.demo.biz.member;

import com.demo.biz.common.LoginDTO;

public interface MemberService {

	public MemberVO loginMember(LoginDTO dto);
	public void insertMember(MemberVO vo);
	
}