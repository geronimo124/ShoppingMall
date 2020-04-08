package com.demo.biz.member.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.biz.member.MessageDAO;
import com.demo.biz.member.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	private final MessageDAO dao;
	
	@Autowired
	public MessageServiceImpl(MessageDAO dao) {
		this.dao = dao;
	}
}
