package com.demo.biz.member;

import java.util.List;

import com.demo.biz.common.LoginDTO;
import com.demo.biz.common.SearchCriteria;

public interface MemberDAO {

	public MemberVO loginMember(LoginDTO dto);
	public int checkId(String mbId);
	public void updateAuth(String mbId);
	public void updateMember(MemberVO vo);
	public MemberVO getMember(String mbId);
	public void insertMember(MemberVO vo);
	public void updateConDate(LoginDTO dto);
	
	public List<MemberVO> getMemberList(SearchCriteria cri);
	public int countMemberList(SearchCriteria cri);
	public void deleteMember(String mbId);
}
