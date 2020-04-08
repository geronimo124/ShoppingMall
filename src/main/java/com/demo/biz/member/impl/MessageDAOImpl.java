package com.demo.biz.member.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.biz.member.MessageDAO;

@Repository
public class MessageDAOImpl implements MessageDAO {

	private final static String NAMESPACE = "com.demo.mapper.MessageMapper";

	private final SqlSession session;
	
	@Autowired
	public MessageDAOImpl(SqlSession session) {
		this.session = session;
	}
}
