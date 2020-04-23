package com.demo.biz.order;

import java.util.List;
import java.util.Map;

import com.demo.biz.member.MemberVO;
import com.demo.biz.product.BasketVO;

/**
 * @ClassName : OrderService.java
 * @Description : 주문 정보의 관리를 위한 서비스 인터페이스
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
public interface OrderService {

    /**
     * 주문할 상품을 장바구니 목록에서 가져온다.
     *
     * @param Map<String, Object> - 아이디와 주문할 상품 목록
     * @return List<BasketVO> - 주문할 장바구니 상품 목록
     */
	public List<BasketVO> getBaskets(Map<String, Object> map);
	
    /**
     * 즉시구매 또는 장바구니 목록의 구매를 통해 상품들을 주문한다.
     *
     * @param OrderVO - 주문 정보
     * @param List<Integer> - 주문 상품 고유번호 목록
     * @param mile - 총 적립금
     * @param bskQty - 즉시구매 했을 시의 상품 개수
     * @return
     */
	public void insertOrder(OrderVO vo, List<Integer> productList, Integer mile, Integer bskQty);
	
    /**
     * 주문한 모든 주문 목록을 가져온다.
     *
     * @param mbId - 회원 ID
     * @return List<OrderVO> - 주문 목록
     */
	public List<OrderVO> getOrderList(String mbId);
	
    /**
     * 회원 정보를 가져온다.
     *
     * @param mbId - 회원 ID
     * @return MemberVO - 회원 정보
     */
	public MemberVO getMember(String mbId);
	
    /**
     * 상품 정보를 가져온다.
     *
     * @param pdNo - 상품 고유번호
     * @return BasketVO - 상품 정보
     */
	public BasketVO getProduct(Integer pdNo);
	
    /**
     * 주문한 모든 주문상세 목록을 가져온다.
     *
     * @param ordNo - 주문 고유번호
     * @return List<BasketVO> - 주문한 주문상세 목록
     */
	public List<BasketVO> getOrderDetail(Integer ordNo);
	
    /**
     * 주문한 주문 정보를 가져온다.
     *
     * @param ordNo - 주문 고유번호
     * @return OrderVO - 주문 정보
     */
	public OrderVO getOrder(Integer ordNo);
	
    /**
     * 검색된 모든 주문 목록을 가져온다.
     *
     * @param OrderSearchCriteria - 검색 정보
     * @return List<OrderVO> - 검색된 주문 목록
     */
	public List<OrderVO> getAllOrderList(OrderSearchCriteria cri);
	
    /**
     * 검색된 주문의 총 개수를 가져온다.
     *
     * @param OrderSearchCriteria - 검색 정보
     * @return 검색된 주문의 총 개수
     */
	public int countAllOrderList(OrderSearchCriteria cri);
	
    /**
     * 주문한 상품들의 재고가 충분한지 판단한다.
     *
     * @param List<BasketVO> - 주문한 상품 목록
     * @return 재고 충분 및 불충분 판단
     */
	public boolean checkStock(List<BasketVO> basketList);
	
    /**
     * 선택된 주문의 정보를 수정한다.
     *
     * @param OrderVO - 주문 정보
     * @return
     */
	public void modifyCheckedOrders(List<OrderVO> orderList);
	
    /**
     * 선택된 주문을 주문테이블에서 삭제한다.
     *
     * @param List<Integer> - 주문 고유번호 목록
     * @return
     */
	public void deleteOrders(List<Integer> orderList);
	
}
