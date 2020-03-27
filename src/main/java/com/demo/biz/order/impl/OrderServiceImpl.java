package com.demo.biz.order.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.biz.member.MemberVO;
import com.demo.biz.order.OrderDAO;
import com.demo.biz.order.OrderDetailVO;
import com.demo.biz.order.OrderService;
import com.demo.biz.order.OrderVO;
import com.demo.biz.product.BasketVO;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderDAO dao;
	
	@Autowired
	public OrderServiceImpl(OrderDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<BasketVO> getBaskets(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.getBaskets(map);
	}

	@Transactional
	@Override
	public void insertOrder(OrderVO vo, List<Integer> productList, Integer mile, Integer bskQty) {
		// TODO Auto-generated method stub
		
		// 주문테이블 삽입(ordervo)
		dao.insertOrder(vo);
		
		// 방금 등록한 주문번호 가져오기
		Integer ordNo = dao.getOrderNo(vo);
		
		// 주문상세 테이블 삽입(위에서 가져온 주문번호, productList, basketvo안에서 mbId,pdNo 사용하여 알아낸 qty, price 넣기)
		Map<String, Object> map = new HashMap<>();
		map.put("mbId", vo.getMbId());
		map.put("productList", productList);
		
		List<BasketVO> basketList = dao.getBaskets(map);
		
		// 즉시구매를 눌렀을 경우 장바구니에서 가져올 수 없음
		if(basketList.size() == 0) {
			OrderDetailVO orderDetail = new OrderDetailVO();
			BasketVO basketVO = dao.getProduct(productList.get(0));
			
			orderDetail.setOrdNo(ordNo);
			orderDetail.setPdNo(basketVO.getPdNo());
			orderDetail.setOrddtQty(bskQty);
			orderDetail.setOrddtPrice((basketVO.getPdTag() * bskQty) * (100 - basketVO.getPdSale()) / 100);
			
			dao.insertOrderDetail(orderDetail);

		}
		
		for(BasketVO basketVO : basketList) {
			OrderDetailVO orderDetail = new OrderDetailVO();
			
			orderDetail.setOrdNo(ordNo);
			orderDetail.setPdNo(basketVO.getPdNo());
			orderDetail.setOrddtQty(basketVO.getBskQty());
			orderDetail.setOrddtPrice((basketVO.getPdTag() * basketVO.getBskQty()) * (100 - basketVO.getPdSale()) / 100);
			
			dao.insertOrderDetail(orderDetail);
		}
		
		// 장바구니 삭제(productList, mbId 사용)
		dao.deleteBaskets(map);
		
		// 적립금 초기화하기
		MemberVO member = new MemberVO();
		member.setMbId(vo.getMbId());
		member.setMbMile(mile);
		dao.updateMileage(member);
		
	}

	@Override
	public List<OrderVO> getOrderList(String mbId) {
		// TODO Auto-generated method stub
		
		List<OrderVO> list = dao.getOrderList(mbId);
		List<OrderVO> orderList = new ArrayList<>();
		
		if(list.size() <= 1)
			return list;
		
		OrderVO vo = list.get(0);
		
		for (int i = 1; i < list.size(); i++) {
			if(vo.getOrdNo() == list.get(i).getOrdNo()) {
				vo.setPdNm(vo.getPdNm() + "<br />" + list.get(i).getPdNm());
			} else {
				orderList.add(vo);
				vo = list.get(i);
			}
			if(i == list.size() - 1)
				orderList.add(vo);
		}
		
		return orderList;
	}

	@Override
	public MemberVO getMember(String mbId) {
		// TODO Auto-generated method stub
		return dao.getMember(mbId);
	}

	@Override
	public BasketVO getProduct(Integer pdNo) {
		// TODO Auto-generated method stub
		return dao.getProduct(pdNo);
	}

	@Override
	public List<BasketVO> getOrderDetail(Integer ordNo) {
		// TODO Auto-generated method stub
		return dao.getOrderDetail(ordNo);
	}

	@Override
	public OrderVO getOrder(Integer ordNo) {
		// TODO Auto-generated method stub
		return dao.getOrder(ordNo);
	}

	@Override
	public List<OrderVO> getAllOrderList() {
		// TODO Auto-generated method stub
		List<OrderVO> list = dao.getAllOrderList();
		List<OrderVO> orderList = new ArrayList<>();
		
		if(list.size() <= 1)
			return list;
		
		OrderVO vo = list.get(0);
		
		for (int i = 1; i < list.size(); i++) {
			if(vo.getOrdNo() == list.get(i).getOrdNo()) {
				vo.setPdNm(vo.getPdNm() + "<br />" + list.get(i).getPdNm());
			} else {
				orderList.add(vo);
				vo = list.get(i);
			}
			if(i == list.size() - 1)
				orderList.add(vo);
		}
		
		return orderList;
	}
}
