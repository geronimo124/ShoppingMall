package com.demo.biz.product.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.biz.product.BasketDAO;
import com.demo.biz.product.BasketService;
import com.demo.biz.product.BasketVO;

@Service
public class BasketServiceImpl implements BasketService {

	private final BasketDAO dao;
	
	@Autowired
	public BasketServiceImpl(BasketDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<BasketVO> getBaskets(String mbId) {
		// TODO Auto-generated method stub
		return dao.getBaskets(mbId);
	}

	@Override
	public boolean insertBasket(BasketVO vo) {
		// TODO Auto-generated method stub
		
		BasketVO getvo = dao.getBasket(vo);
		
		if(getvo != null)
			return false;
		else {
			dao.insertBasket(vo);
			return true;
		}
	}

	@Override
	public void deleteBasket(BasketVO vo) {
		// TODO Auto-generated method stub
		dao.deleteBasket(vo);
	}

	@Override
	public void updateBasket(BasketVO vo) {
		// TODO Auto-generated method stub
		dao.updateBasket(vo);
	}

	@Override
	public void deleteBaskets(Map<String, Object> map) {
		// TODO Auto-generated method stub
		dao.deleteBaskets(map);
	}

	@Transactional
	@Override
	public void updateBaskets(List<BasketVO> basketList) {
		// TODO Auto-generated method stub
		for(BasketVO vo : basketList)
			dao.updateBasket(vo);
	}


}
