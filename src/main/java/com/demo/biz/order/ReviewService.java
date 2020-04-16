package com.demo.biz.order;

import java.util.List;

import com.demo.biz.common.Criteria;

public interface ReviewService {

	public void insertReview(ReviewVO vo);
	public List<ReviewVO> listReview(Integer pdNo, Criteria cri);
	public int countReviewList(Integer pdNo);
	public void deleteReview(Integer revNo);
	public void modifyReview(ReviewVO vo);
	public ReviewVO getReview(Integer ordNo, Integer pdNo);
	
}
