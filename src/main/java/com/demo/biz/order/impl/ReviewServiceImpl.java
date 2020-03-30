package com.demo.biz.order.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.biz.common.Criteria;
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

	@Override
	public List<ReviewVO> listReview(Integer pdNo, Criteria cri) {
		// TODO Auto-generated method stub
		return dao.listReview(pdNo, cri);
	}

	@Override
	public int countReviewList(Integer pdNo) {
		// TODO Auto-generated method stub
		return dao.countReviewList(pdNo);
	}

	@Override
	public void deleteReview(Integer revNo) {
		// TODO Auto-generated method stub
		dao.deleteReview(revNo);
	}

	@Override
	public void modifyReview(ReviewVO vo) {
		// TODO Auto-generated method stub
		dao.modifyReview(vo);
	}

	@Override
	public ReviewVO getReview(Integer ordNo, Integer pdNo) {
		// TODO Auto-generated method stub
		return dao.getReview(ordNo, pdNo);
	}
}
