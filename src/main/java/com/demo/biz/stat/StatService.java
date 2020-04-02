package com.demo.biz.stat;

import java.util.List;
import java.util.Map;

import com.demo.biz.product.ProductVO;

public interface StatService {

	public int getCountNewOrders(String admId);
	public int getCountNewMembers(String admId);
	public List<Map<String, Object>> getSalesGraph();
	public List<ProductVO> getBestSellers();
	
}
