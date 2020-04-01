package com.demo.biz.stat.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.biz.stat.StatDAO;
import com.demo.biz.stat.StatService;

@Service
public class StatServiceImpl implements StatService {

	private final StatDAO dao;
	
	@Autowired
	public StatServiceImpl(StatDAO dao) {
		this.dao = dao;
	}

	@Override
	public int getCountNewOrders(String admId) {
		// TODO Auto-generated method stub
		return dao.getCountNewOrders(admId);
	}

	@Override
	public int getCountNewMembers(String admId) {
		// TODO Auto-generated method stub
		return dao.getCountNewMembers(admId);
	}
	
}
