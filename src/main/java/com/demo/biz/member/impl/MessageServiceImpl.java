package com.demo.biz.member.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.biz.member.MemberVO;
import com.demo.biz.member.MessageDAO;
import com.demo.biz.member.MessageService;
import com.demo.biz.member.MessageVO;

@Service
public class MessageServiceImpl implements MessageService {

	private final MessageDAO dao;
	
	@Autowired
	public MessageServiceImpl(MessageDAO dao) {
		this.dao = dao;
	}

	@Override
	public boolean sendMsg(MessageVO vo) {
		// TODO Auto-generated method stub
		MemberVO member = dao.getMember(vo.getMsgTarget());
		
		if(member == null)
			return false;
		
		dao.sendMsg(vo);
		return true;
	}
}
