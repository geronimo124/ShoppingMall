package com.demo.biz.order;

import java.util.List;
import java.util.Map;

import com.demo.biz.member.MemberVO;
import com.demo.biz.product.BasketVO;

public interface OrderDAO {
	
	public List<BasketVO> getBaskets(Map<String, Object> map);
	public void insertOrder(OrderVO vo);
	public int getOrderNo(OrderVO vo);
	public void insertOrderDetail(OrderDetailVO vo);
	public void deleteBaskets(Map<String, Object> map);
	public void updateMileage(MemberVO vo);
	public List<OrderVO> getOrderList(String mbId);
	public MemberVO getMember(String mbId);
	public BasketVO getProduct(Integer pdNo);
	public List<BasketVO> getOrderDetail(Integer ordNo);
	public OrderVO getOrder(Integer ordNo);
	public List<OrderVO> getAllOrderList(OrderSearchCriteria cri);
	public List<OrderVO> countAllOrderList(OrderSearchCriteria cri);
	public int getStock(Integer pdNo);
	public void modifyCheckedOrder(OrderVO order);
	public void deleteOrders(List<Integer> orderList);
	
}
