package com.demo.biz.order;

import java.util.List;
import java.util.Map;

import com.demo.biz.common.SearchCriteria;
import com.demo.biz.member.MemberVO;
import com.demo.biz.product.BasketVO;

public interface OrderService {

	public List<BasketVO> getBaskets(Map<String, Object> map);
	public void insertOrder(OrderVO vo, List<Integer> productList, Integer mile, Integer bskQty);
	public List<OrderVO> getOrderList(String mbId);
	public MemberVO getMember(String mbId);
	public BasketVO getProduct(Integer pdNo);
	public List<BasketVO> getOrderDetail(Integer ordNo);
	public OrderVO getOrder(Integer ordNo);
	public List<OrderVO> getAllOrderList(SearchCriteria cri);
	public int countAllOrderList(SearchCriteria cri);
	public boolean checkStock(List<BasketVO> basketList);
}
