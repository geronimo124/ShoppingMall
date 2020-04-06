package com.demo.biz.member.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.biz.common.LoginDTO;
import com.demo.biz.common.SearchCriteria;
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
	public int checkId(String mbId) {
		// TODO Auto-generated method stub
		return session.selectOne(NAMESPACE + ".checkId", mbId);
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

	@Override
	public MemberVO getMember(String mbId) {
		// TODO Auto-generated method stub
		return session.selectOne(NAMESPACE + ".getMember", mbId);
	}

	@Override
	public void updateAuth(String mbId) {
		// TODO Auto-generated method stub
		session.update(NAMESPACE + ".updateAuth", mbId);
	}

	@Override
	public void updateMember(MemberVO vo) {
		// TODO Auto-generated method stub
		session.update(NAMESPACE + ".update", vo);
	}

	@Override
	public List<MemberVO> getMemberList(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return session.selectList(NAMESPACE + ".getMemberList", cri);
	}

	@Override
	public int countMemberList(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return session.selectOne(NAMESPACE + ".countMemberList", cri);
	}

	@Override
	public void deleteMember(String mbId) {
		// TODO Auto-generated method stub
		session.delete(NAMESPACE + ".deleteMember", mbId);
	}

	@Override
	public List<MemberVO> getAllMembers() {
		// TODO Auto-generated method stub
		return session.selectList(NAMESPACE + ".getAllMembers");
	}
}
