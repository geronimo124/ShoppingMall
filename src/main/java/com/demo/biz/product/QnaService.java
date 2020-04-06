package com.demo.biz.product;

import java.util.List;

import com.demo.biz.common.Criteria;
import com.demo.biz.common.SearchCriteria;

public interface QnaService {
	
	// Member
	public void insertQna(QnaVO vo);
	List<QnaVO> listQna(Integer pdNo, Criteria cri);
	public int countQnaList(Integer pdNo);

	// Admin
	public List<QnaVO> getQnaList(SearchCriteria cri);
	public int countQnaList(SearchCriteria cri);

}
