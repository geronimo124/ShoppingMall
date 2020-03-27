package com.demo.biz.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.biz.order.ReviewDAO;
import com.demo.biz.order.ReviewService;
import com.demo.biz.order.ReviewVO;

@Service
public class ReviewServiceImpl implements ReviewService {

	private final ReviewDAO dao;
	
	@Autowired
	public ReviewServiceImpl(ReviewDAO dao) {
		this.dao = dao;
	}

	@Override
	public void insertReview(ReviewVO vo) {
		// TODO Auto-generated method stub
		dao.insertReview(vo);
	}
}
