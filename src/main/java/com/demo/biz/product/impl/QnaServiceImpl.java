package com.demo.biz.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.biz.common.Criteria;
import com.demo.biz.common.SearchCriteria;
import com.demo.biz.product.QnaDAO;
import com.demo.biz.product.QnaService;
import com.demo.biz.product.QnaVO;

@Service
public class QnaServiceImpl implements QnaService {

	private final QnaDAO dao;
	
	@Autowired
	public QnaServiceImpl(QnaDAO dao) {
		this.dao = dao;
	}

	@Override
	public void insertQna(QnaVO vo) {
		// TODO Auto-generated method stub
		dao.insertQna(vo);
	}

	@Override
	public List<QnaVO> listQna(Integer pdNo, Criteria cri) {
		// TODO Auto-generated method stub
		return dao.listQna(pdNo, cri);
	}

	@Override
	public int countQnaList(Integer pdNo) {
		// TODO Auto-generated method stub
		return dao.countQnaList(pdNo);
	}

	@Override
	public List<QnaVO> getQnaList(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return dao.getQnaList(cri);
	}

	@Override
	public int countQnaList(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return dao.countQnaList(cri);
	}
}
