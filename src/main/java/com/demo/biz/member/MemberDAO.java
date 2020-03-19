package com.demo.biz.member;

import com.demo.biz.common.LoginDTO;

public interface MemberDAO {

	public MemberVO loginMember(LoginDTO dto);
	public void insertMember(MemberVO vo);
	public void updateConDate(LoginDTO dto);
	
}
