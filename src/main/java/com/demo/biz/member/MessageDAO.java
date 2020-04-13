package com.demo.biz.member;

import java.util.List;

public interface MessageDAO {

	public void sendMsg(MessageVO vo);
	public MemberVO getMember(String mbId);
	public List<MessageVO> getMsgs(String mbId);
	public MessageVO getMsg(Integer msgNo);
	public void updateStatus(Integer msgNo);
}
