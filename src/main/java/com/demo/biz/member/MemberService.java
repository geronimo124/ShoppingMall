package com.demo.biz.member;

import java.util.List;

import com.demo.biz.common.LoginDTO;
import com.demo.biz.common.SearchCriteria;

public interface MemberService {

	public MemberVO loginMember(LoginDTO dto);
	public MemberVO loginMember(String mbEmail);
	public MemberVO getMember(String mbId);
	public void updateAuth(String mbId);
	public void updateMember(MemberVO vo);
	public int checkId(String mbId);
	public void insertMember(MemberVO vo);
	
	public List<MemberVO> getMemberList(SearchCriteria cri);
	public int countMemberList(SearchCriteria cri);
	public void deleteMembers(List<String> memberList);
	
}