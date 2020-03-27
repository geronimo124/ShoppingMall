package com.demo.biz.order.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.biz.order.ReviewDAO;
import com.demo.biz.order.ReviewVO;

@Repository
public class ReviewDAOImpl implements ReviewDAO {
	
	private final static String NAMESPACE = "com.demo.mapper.ReviewMapper";

	private final SqlSession session;
	
	@Autowired
	public ReviewDAOImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public void insertReview(ReviewVO vo) {
		// TODO Auto-generated method stub
		session.insert(NAMESPACE + ".insertReview", vo);
	}
}
