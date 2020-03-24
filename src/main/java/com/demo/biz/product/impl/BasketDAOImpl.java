package com.demo.biz.product.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.biz.product.BasketDAO;
import com.demo.biz.product.BasketVO;

@Repository
public class BasketDAOImpl implements BasketDAO {

	private final static String NAMESPACE = "com.demo.mapper.BasketMapper";

	private final SqlSession session;
	
	@Autowired
	public BasketDAOImpl(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<BasketVO> getBaskets(String mbId) {
		// TODO Auto-generated method stub
		return session.selectList(NAMESPACE + ".getBaskets", mbId);
	}

	@Override
	public void insertBasket(BasketVO vo) {
		// TODO Auto-generated method stub
		session.insert(NAMESPACE + ".insertBasket", vo);
	}

	@Override
	public BasketVO getBasket(BasketVO vo) {
		// TODO Auto-generated method stub
		return session.selectOne(NAMESPACE + ".getBasket", vo);
	}

	@Override
	public void deleteBasket(BasketVO vo) {
		// TODO Auto-generated method stub
		session.delete(NAMESPACE + ".deleteBasket", vo);
	}

	@Override
	public void updateBasket(BasketVO vo) {
		// TODO Auto-generated method stub
		session.update(NAMESPACE + ".updateBasket", vo);
	}

	@Override
	public void deleteBaskets(Map<String, Object> map) {
		// TODO Auto-generated method stub
		session.delete(NAMESPACE + ".deleteBaskets", map);
	}

}
