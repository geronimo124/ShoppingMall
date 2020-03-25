package com.demo.biz.order.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.biz.member.MemberVO;
import com.demo.biz.order.OrderDAO;
import com.demo.biz.order.OrderDetailVO;
import com.demo.biz.order.OrderVO;
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
	public List<BasketVO> getBaskets(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return session.selectList(NAMESPACE + ".getBaskets", map);
	}

	@Override
	public void insertOrder(OrderVO vo) {
		// TODO Auto-generated method stub
		session.insert(NAMESPACE + ".insertOrder", vo);
	}

	@Override
	public int getOrderNo(OrderVO vo) {
		// TODO Auto-generated method stub
		return session.selectOne(NAMESPACE + ".getOrderNo", vo);
	}

	@Override
	public void insertOrderDetail(OrderDetailVO vo) {
		// TODO Auto-generated method stub
		session.insert(NAMESPACE + ".insertOrderDetail", vo);
	}

	@Override
	public void deleteBaskets(Map<String, Object> map) {
		// TODO Auto-generated method stub
		session.delete(NAMESPACE + ".deleteBaskets", map);
	}

	@Override
	public void updateMileage(MemberVO vo) {
		// TODO Auto-generated method stub
		session.update(NAMESPACE + ".updateMileage", vo);
	}
	
}
