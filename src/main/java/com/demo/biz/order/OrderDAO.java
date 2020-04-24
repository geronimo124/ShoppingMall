package com.demo.biz.order;

import java.util.List;
import java.util.Map;

import com.demo.biz.member.MemberVO;
import com.demo.biz.product.BasketVO;

/**
 * @ClassName : OrderDAO.java
 * @Description : 주문 정보의 관리를 위한 데이터 접근 인터페이스
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
public interface OrderDAO {
	
    /**
     * 주문할 상품을 장바구니 목록에서 가져온다.
     *
     * @param Map 아이디와 주문할 상품 목록
     * @return List - 주문할 장바구니 상품 목록
     */
	public List<BasketVO> getBaskets(Map<String, Object> map);
	
    /**
     * 주문테이블에 주문 정보를 입력한다.
     *
     * @param OrderVO 주문 정보
     * @return
     */
	public void insertOrder(OrderVO vo);
	
    /**
     * 방금 주문한 주문 번호를 가져온다.
     *
     * @param OrderVO 주문 정보
     * @return 주문 번호
     */
	public int getOrderNo(OrderVO vo);
	
    /**
     * 주문상세테이블에 주문상세 정보를 입력한다.
     *
     * @param OrderDetailVO 주문상세 정보
     * @return
     */
	public void insertOrderDetail(OrderDetailVO vo);
	
    /**
     * 주문한 상품을 장바구니 테이블에서 삭제한다.
     *
     * @param Map 아이디와 주문한 상품 목록
     * @return
     */
	public void deleteBaskets(Map<String, Object> map);
	
    /**
     * 주문이 완료되면 회원의 적립금을 업데이트한다.
     *
     * @param MemberVO 회원 정보
     * @return
     */
	public void updateMileage(MemberVO vo);
	
    /**
     * 주문한 모든 주문 목록을 가져온다.
     *
     * @param mbId 회원 ID
     * @return List - 주문 목록
     */
	public List<OrderVO> getOrderList(String mbId);
	
    /**
     * 회원 정보를 가져온다.
     *
     * @param mbId 회원 ID
     * @return MemberVO - 회원 정보
     */
	public MemberVO getMember(String mbId);
	
    /**
     * 상품 정보를 가져온다.
     *
     * @param pdNo 상품 고유번호
     * @return BasketVO - 상품 정보
     */
	public BasketVO getProduct(Integer pdNo);
	
    /**
     * 주문한 모든 주문상세 목록을 가져온다.
     *
     * @param ordNo 주문 고유번호
     * @return List - 주문한 주문상세 목록
     */
	public List<BasketVO> getOrderDetail(Integer ordNo);
	
    /**
     * 주문한 주문 정보를 가져온다.
     *
     * @param ordNo 주문 고유번호
     * @return OrderVO - 주문 정보
     */
	public OrderVO getOrder(Integer ordNo);
	
    /**
     * 검색된 모든 주문 목록을 가져온다.
     *
     * @param OrderSearchCriteria 검색 정보
     * @return List - 검색된 주문 목록
     */
	public List<OrderVO> getAllOrderList(OrderSearchCriteria cri);
	
    /**
     * 검색된 주문의 총 개수를 목록으로 가져온다.
     *
     * @param OrderSearchCriteria 검색 정보
     * @return List - 검색된 주문 목록
     */
	public List<OrderVO> countAllOrderList(OrderSearchCriteria cri);
	
    /**
     * 해당 상품의 재고 개수를 반환한다.
     *
     * @param pdNo 상품 고유번호
     * @return 상품 재고
     */
	public int getStock(Integer pdNo);
	
    /**
     * 선택된 주문의 정보를 수정한다.
     *
     * @param OrderVO 주문 정보
     * @return
     */
	public void modifyCheckedOrder(OrderVO order);
	
    /**
     * 선택된 주문을 주문테이블에서 삭제한다.
     *
     * @param List 주문 고유번호 목록
     * @return
     */
	public void deleteOrders(List<Integer> orderList);
	
}
