package com.demo.biz.member;

public interface MessageDAO {

	public void sendMsg(MessageVO vo);
	public MemberVO getMember(String mbId);
}
