package com.demo.biz.order;

import java.util.List;
import java.util.Map;

import com.demo.biz.product.BasketVO;

public interface OrderService {

	public List<BasketVO> getBaskets(Map<String, Object> map);
	public void insertOrder(OrderVO vo, List<Integer> productList, Integer mile);
}
