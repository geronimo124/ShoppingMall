package com.demo.biz.stat.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.biz.stat.StatDAO;

@Repository
public class StatDAOImpl implements StatDAO {

	private final static String NAMESPACE = "com.demo.mapper.StatMapper";

	private final SqlSession session;
	
	@Autowired
	public StatDAOImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public int getCountNewOrders(String admId) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = new HashMap<>();
		map.put("admId", admId);
		
		session.selectOne(NAMESPACE + ".getCountNewOrders", map);
		
		return (int) map.get("count");
	}

	@Override
	public int getCountNewMembers(String admId) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = new HashMap<>();
		map.put("admId", admId);
		
		session.selectOne(NAMESPACE + ".getCountNewMembers", map);
		
		return (int) map.get("count");
	}
}
