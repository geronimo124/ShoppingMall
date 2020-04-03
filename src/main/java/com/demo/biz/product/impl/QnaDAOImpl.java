package com.demo.biz.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.biz.common.Criteria;
import com.demo.biz.product.QnaDAO;
import com.demo.biz.product.QnaVO;

@Repository
public class QnaDAOImpl implements QnaDAO {
	
	private final static String NAMESPACE = "com.demo.mapper.QnaMapper";

	private final SqlSession session;
	
	@Autowired
	public QnaDAOImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public void insertQna(QnaVO vo) {
		// TODO Auto-generated method stub
		session.insert(NAMESPACE + ".insertQna", vo);
	}

	@Override
	public List<QnaVO> listQna(Integer pdNo, Criteria cri) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();

	    map.put("pdNo", pdNo);
	    map.put("cri", cri);
		
		return session.selectList(NAMESPACE + ".listQna", map);
	}

	@Override
	public int countQnaList(Integer pdNo) {
		// TODO Auto-generated method stub
		return session.selectOne(NAMESPACE + ".countQnaList", pdNo);
	}
}
