package com.demo.biz.order;

import java.util.List;

import com.demo.biz.product.BasketVO;

public interface OrderService {

	public List<BasketVO> getBaskets(List<Integer> productList);
}
