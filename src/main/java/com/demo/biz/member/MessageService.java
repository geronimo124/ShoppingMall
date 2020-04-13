package com.demo.biz.member;

import java.util.List;

public interface MessageService {

	public boolean sendMsg(MessageVO vo);
	public List<MessageVO> getMsgs(String mbId);
	public MessageVO getMsg(Integer msgNo);
}
