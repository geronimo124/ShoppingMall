package com.demo.biz.order.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.biz.common.Criteria;
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

	@Override
	public List<ReviewVO> listReview(Integer pdNo, Criteria cri) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = new HashMap<>();

	    map.put("pdNo", pdNo);
	    map.put("cri", cri);

	    return session.selectList(NAMESPACE + ".listReview", map);
	}

	@Override
	public int countReviewList(Integer pdNo) {
		// TODO Auto-generated method stub
		return session.selectOne(NAMESPACE + ".countReviewList", pdNo);
	}

	@Override
	public void deleteReview(Integer revNo) {
		// TODO Auto-generated method stub
		session.delete(NAMESPACE + ".deleteReview", revNo);
	}

	@Override
	public void modifyReview(ReviewVO vo) {
		// TODO Auto-generated method stub
		session.update(NAMESPACE + ".modifyReview", vo);
	}

	@Override
	public ReviewVO getReview(Integer ordNo, Integer pdNo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();

	    map.put("ordNo", ordNo);
	    map.put("pdNo", pdNo);
		
		return session.selectOne(NAMESPACE + ".getReview", map);
	}
}
