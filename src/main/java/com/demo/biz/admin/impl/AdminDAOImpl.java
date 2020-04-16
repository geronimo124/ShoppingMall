package com.demo.biz.admin.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.biz.admin.AdminDAO;
import com.demo.biz.admin.AdminVO;
import com.demo.biz.common.LoginDTO;

@Repository
public class AdminDAOImpl implements AdminDAO {

	private final static String NAMESPACE = "com.demo.mapper.AdminMapper";

	private final SqlSession session;
	
	@Autowired
	public AdminDAOImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public AdminVO loginAdmin(LoginDTO dto) {
		// TODO Auto-generated method stub
		return session.selectOne(NAMESPACE + ".login", dto);
	}

	@Override
	public void updateConDate(LoginDTO dto) {
		// TODO Auto-generated method stub
		session.update(NAMESPACE + ".updateConDate", dto);
	}

}
