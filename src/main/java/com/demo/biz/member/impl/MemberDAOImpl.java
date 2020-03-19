package com.demo.biz.member.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.biz.common.LoginDTO;
import com.demo.biz.member.MemberDAO;
import com.demo.biz.member.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	private final static String NAMESPACE = "com.demo.mapper.MemberMapper";

	private final SqlSession session;
	
	@Autowired
	public MemberDAOImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public MemberVO loginMember(LoginDTO dto) {
		// TODO Auto-generated method stub
		return session.selectOne(NAMESPACE + ".login", dto);
	}
	
	@Override
	public void insertMember(MemberVO vo) {
		// TODO Auto-generated method stub
		session.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public void updateConDate(LoginDTO dto) {
		// TODO Auto-generated method stub
		session.update(NAMESPACE + ".updateConDate", dto);
	}

	
}
