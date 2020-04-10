package com.demo.biz.member.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.biz.member.MemberVO;
import com.demo.biz.member.MessageDAO;
import com.demo.biz.member.MessageVO;

@Repository
public class MessageDAOImpl implements MessageDAO {

	private final static String NAMESPACE = "com.demo.mapper.MessageMapper";

	private final SqlSession session;
	
	@Autowired
	public MessageDAOImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public void sendMsg(MessageVO vo) {
		// TODO Auto-generated method stub
		session.insert(NAMESPACE + ".sendMsg", vo);
	}

	@Override
	public MemberVO getMember(String mbId) {
		// TODO Auto-generated method stub
		return session.selectOne(NAMESPACE + ".getMember", mbId);
	}
}
