package com.demo.biz.stat.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.biz.product.CategoryVO;
import com.demo.biz.product.ProductVO;
import com.demo.biz.stat.StatDAO;
import com.demo.biz.stat.StatService;

@Service
public class StatServiceImpl implements StatService {

	private final StatDAO dao;
	
	@Autowired
	public StatServiceImpl(StatDAO dao) {
		this.dao = dao;
	}

	@Override
	public int getCountNewOrders(String admId) {
		// TODO Auto-generated method stub
		return dao.getCountNewOrders(admId);
	}

	@Override
	public int getCountNewMembers(String admId) {
		// TODO Auto-generated method stub
		return dao.getCountNewMembers(admId);
	}

	@Override
	public List<Map<String, Object>> getSalesGraph() {
		// TODO Auto-generated method stub
		return dao.getSalesGraph();
	}

	@Transactional
	@Override
	public List<ProductVO> getBestSellers() {
		// TODO Auto-generated method stub
		
		List<CategoryVO> cateList = dao.getCategoryList(1);
		
		List<ProductVO> bestSellerList = new ArrayList<>();
		
		for(CategoryVO category : cateList)
			bestSellerList.add(dao.getBestSeller(category.getCtgyCd()));
		
		return bestSellerList;
	}
	
}
