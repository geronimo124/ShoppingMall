package com.demo.biz.product;

import java.util.List;

import com.demo.biz.common.Criteria;

public interface QnaService {
	
	public void insertQna(QnaVO vo);
	List<QnaVO> listQna(Integer pdNo, Criteria cri);
	public int countQnaList(Integer pdNo);
}
