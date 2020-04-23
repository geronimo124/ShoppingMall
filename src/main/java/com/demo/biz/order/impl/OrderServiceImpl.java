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
import com.demo.biz.order.OrderSearchCriteria;
import com.demo.biz.order.OrderService;
import com.demo.biz.order.OrderVO;
import com.demo.biz.product.BasketVO;

/**
 * @ClassName : OrderServiceImpl.java
 * @Description : 주문 정보의 관리를 위한 서비스 클래스
 * @Modification Information
 *
 *    수정일			수정자		수정내용
 *    -------		-------     -------------------
 *    2020. 4. 23.	전일배		최초생성
 *
 * @author 전일배
 * @since 2020. 4. 23.
 * @version
 * @see
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

	private final OrderDAO dao;
	
	@Autowired
	public OrderServiceImpl(OrderDAO dao) {
		this.dao = dao;
	}

    /**
     * 주문할 상품을 장바구니 목록에서 가져온다.
     *
     * @param Map<String, Object> - 아이디와 주문할 상품 목록
     * @return List<BasketVO> - 주문할 장바구니 상품 목록
     */
	@Override
	public List<BasketVO> getBaskets(Map<String, Object> map) {
		return dao.getBaskets(map);
	}

    /**
     * 즉시구매 또는 장바구니 목록의 구매를 통해 상품들을 주문한다.
     *
     * @param OrderVO - 주문 정보
     * @param List<Integer> - 주문 상품 고유번호 목록
     * @param mile - 총 적립금
     * @param bskQty - 즉시구매 했을 시의 상품 개수
     * @return
     */
	@Transactional
	@Override
	public void insertOrder(OrderVO vo, List<Integer> productList, Integer mile, Integer bskQty) {
		
		// 주문테이블 삽입
		dao.insertOrder(vo);
		
		// 주문 번호 가져오기
		Integer ordNo = dao.getOrderNo(vo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("mbId", vo.getMbId());
		map.put("productList", productList);
		
		List<BasketVO> basketList = dao.getBaskets(map);
		
		// 주문상세 테이블 삽입
		
		// 즉시구매를 눌렀을 경우
		if(basketList.size() == 0) {
			
			BasketVO basketVO = dao.getProduct(productList.get(0));
			
			OrderDetailVO orderDetail = OrderDetailVO.builder()
					.ordNo(ordNo)
					.pdNo(basketVO.getPdNo())
					.orddtQty(bskQty)
					.orddtPrice((basketVO.getPdTag() * bskQty) * (100 - basketVO.getPdSale()) / 100)
					.build();
			
			dao.insertOrderDetail(orderDetail);

		}
		
		for(BasketVO basketVO : basketList) {
			
			OrderDetailVO orderDetail = OrderDetailVO.builder()
					.ordNo(ordNo)
					.pdNo(basketVO.getPdNo())
					.orddtQty(basketVO.getBskQty())
					.orddtPrice((basketVO.getPdTag() * basketVO.getBskQty()) * (100 - basketVO.getPdSale()) / 100)
					.build();
			
			dao.insertOrderDetail(orderDetail);
		}
		
		// 장바구니 삭제
		dao.deleteBaskets(map);

		// 적립금 초기화
		MemberVO member = new MemberVO();
		member.setMbId(vo.getMbId());
		member.setMbMile(mile);
		dao.updateMileage(member);
		
	}

    /**
     * 주문한 모든 주문 목록을 가져온다.
     *
     * @param mbId - 회원 ID
     * @return List<OrderVO> - 주문 목록
     */
	@Override
	public List<OrderVO> getOrderList(String mbId) {
		
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

    /**
     * 회원 정보를 가져온다.
     *
     * @param mbId - 회원 ID
     * @return MemberVO - 회원 정보
     */
	@Override
	public MemberVO getMember(String mbId) {
		return dao.getMember(mbId);
	}

    /**
     * 상품 정보를 가져온다.
     *
     * @param pdNo - 상품 고유번호
     * @return BasketVO - 상품 정보
     */
	@Override
	public BasketVO getProduct(Integer pdNo) {
		return dao.getProduct(pdNo);
	}

    /**
     * 주문한 모든 주문상세 목록을 가져온다.
     *
     * @param ordNo - 주문 고유번호
     * @return List<BasketVO> - 주문한 주문상세 목록
     */
	@Override
	public List<BasketVO> getOrderDetail(Integer ordNo) {
		return dao.getOrderDetail(ordNo);
	}

    /**
     * 주문한 주문 정보를 가져온다.
     *
     * @param ordNo - 주문 고유번호
     * @return OrderVO - 주문 정보
     */
	@Override
	public OrderVO getOrder(Integer ordNo) {
		return dao.getOrder(ordNo);
	}

    /**
     * 검색된 모든 주문 목록을 가져온다.
     *
     * @param OrderSearchCriteria - 검색 정보
     * @return List<OrderVO> - 검색된 주문 목록
     */
	@Override
	public List<OrderVO> getAllOrderList(OrderSearchCriteria cri) {
		
		List<OrderVO> list = dao.getAllOrderList(cri);
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

    /**
     * 주문한 상품들의 재고가 충분한지 판단한다.
     *
     * @param List<BasketVO> - 주문한 상품 목록
     * @return 재고 충분 및 불충분 판단
     */
	@Override
	public boolean checkStock(List<BasketVO> basketList) {
		
		for(BasketVO basket : basketList) {
			
			int stock = dao.getStock(basket.getPdNo());
			
			if(basket.getBskQty() > stock)
				return false;
		}
		return true;
	}

    /**
     * 검색된 주문의 총 개수를 가져온다.
     *
     * @param OrderSearchCriteria - 검색 정보
     * @return 검색된 주문의 총 개수
     */
	@Override
	public int countAllOrderList(OrderSearchCriteria cri) {
		
		List<OrderVO> list = dao.countAllOrderList(cri);
		List<OrderVO> orderList = new ArrayList<>();
		
		if(list.size() == 0)
			return 0;
		else if(list.size() == 1)
			return 1;
		
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
		
		return orderList.size();
	}

    /**
     * 선택된 주문의 정보를 수정한다.
     *
     * @param OrderVO - 주문 정보
     * @return
     */
	@Transactional
	@Override
	public void modifyCheckedOrders(List<OrderVO> orderList) {
		
		for(OrderVO vo : orderList)
			dao.modifyCheckedOrder(vo);
		
	}

    /**
     * 선택된 주문을 주문테이블에서 삭제한다.
     *
     * @param List<Integer> - 주문 고유번호 목록
     * @return
     */
	@Override
	public void deleteOrders(List<Integer> orderList) {
		dao.deleteOrders(orderList);
	}
}
