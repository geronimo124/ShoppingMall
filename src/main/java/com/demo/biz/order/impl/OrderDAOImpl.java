package com.demo.biz.order.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.biz.order.OrderDAO;
import com.demo.biz.product.BasketVO;

@Repository
public class OrderDAOImpl implements OrderDAO {

	private final static String NAMESPACE = "com.demo.mapper.OrderMapper";

	private final SqlSession session;
	
	@Autowired
	public OrderDAOImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<BasketVO> getBaskets(List<Integer> productList) {
		// TODO Auto-generated method stub
		return session.selectList(NAMESPACE + ".getBaskets", productList);
	}
	
}
