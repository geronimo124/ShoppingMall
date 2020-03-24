package com.demo.biz.order.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.biz.order.OrderDAO;
import com.demo.biz.order.OrderService;
import com.demo.biz.product.BasketVO;

@Service
public class OrderServiceImpl implements OrderService{

	private final OrderDAO dao;
	
	@Autowired
	public OrderServiceImpl(OrderDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<BasketVO> getBaskets(List<Integer> productList) {
		// TODO Auto-generated method stub
		return dao.getBaskets(productList);
	}
}
