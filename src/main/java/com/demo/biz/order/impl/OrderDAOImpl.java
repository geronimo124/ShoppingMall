package com.demo.biz.order.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.biz.member.MemberVO;
import com.demo.biz.order.OrderDAO;
import com.demo.biz.order.OrderDetailVO;
import com.demo.biz.order.OrderSearchCriteria;
import com.demo.biz.order.OrderVO;
import com.demo.biz.product.BasketVO;

/**
 * @ClassName : OrderDAOImpl.java
 * @Description : 주문 정보의 관리를 위한 데이터 접근 클래스
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
@Repository
public class OrderDAOImpl implements OrderDAO {

	private final static String NAMESPACE = "com.demo.mapper.OrderMapper";

	private final SqlSession session;
	
	@Autowired
	public OrderDAOImpl(SqlSession session) {
		this.session = session;
	}

    /**
     * 주문할 상품을 장바구니 목록에서 가져온다.
     *
     * @param Map<String, Object> - 아이디와 주문할 상품 목록
     * @return List<BasketVO> - 주문할 장바구니 상품 목록
     */
	@Override
	public List<BasketVO> getBaskets(Map<String, Object> map) {
		return session.selectList(NAMESPACE + ".getBaskets", map);
	}

    /**
     * 주문테이블에 주문 정보를 입력한다.
     *
     * @param OrderVO - 주문 정보
     * @return
     */
	@Override
	public void insertOrder(OrderVO vo) {
		session.insert(NAMESPACE + ".insertOrder", vo);
	}

    /**
     * 방금 주문한 주문 번호를 가져온다.
     *
     * @param OrderVO - 주문 정보
     * @return 주문 번호
     */
	@Override
	public int getOrderNo(OrderVO vo) {
		return session.selectOne(NAMESPACE + ".getOrderNo", vo);
	}

    /**
     * 주문상세테이블에 주문상세 정보를 입력한다.
     *
     * @param OrderDetailVO - 주문상세 정보
     * @return
     */
	@Override
	public void insertOrderDetail(OrderDetailVO vo) {
		session.insert(NAMESPACE + ".insertOrderDetail", vo);
	}

    /**
     * 주문한 상품을 장바구니 테이블에서 삭제한다.
     *
     * @param Map<String, Object> - 아이디와 주문한 상품 목록
     * @return
     */
	@Override
	public void deleteBaskets(Map<String, Object> map) {
		session.delete(NAMESPACE + ".deleteBaskets", map);
	}

    /**
     * 주문이 완료되면 회원의 적립금을 업데이트한다.
     *
     * @param MemberVO - 회원 정보
     * @return
     */
	@Override
	public void updateMileage(MemberVO vo) {
		session.update(NAMESPACE + ".updateMileage", vo);
	}

    /**
     * 주문한 모든 주문 목록을 가져온다.
     *
     * @param mbId - 회원 ID
     * @return List<OrderVO> - 주문 목록
     */
	@Override
	public List<OrderVO> getOrderList(String mbId) {
		return session.selectList(NAMESPACE + ".getOrderList", mbId);
	}

    /**
     * 회원 정보를 가져온다.
     *
     * @param mbId - 회원 ID
     * @return MemberVO - 회원 정보
     */
	@Override
	public MemberVO getMember(String mbId) {
		return session.selectOne(NAMESPACE + ".getMember", mbId);
	}

    /**
     * 상품 정보를 가져온다.
     *
     * @param pdNo - 상품 고유번호
     * @return BasketVO - 상품 정보
     */
	@Override
	public BasketVO getProduct(Integer pdNo) {
		return session.selectOne(NAMESPACE + ".getProduct", pdNo);
	}

    /**
     * 주문한 모든 주문상세 목록을 가져온다.
     *
     * @param ordNo - 주문 고유번호
     * @return List<BasketVO> - 주문한 주문상세 목록
     */
	@Override
	public List<BasketVO> getOrderDetail(Integer ordNo) {
		return session.selectList(NAMESPACE + ".getOrderDetail", ordNo);
	}

    /**
     * 주문한 주문 정보를 가져온다.
     *
     * @param ordNo - 주문 고유번호
     * @return OrderVO - 주문 정보
     */
	@Override
	public OrderVO getOrder(Integer ordNo) {
		return session.selectOne(NAMESPACE + ".getOrder", ordNo);
	}

    /**
     * 검색된 모든 주문 목록을 가져온다.
     *
     * @param OrderSearchCriteria - 검색 정보
     * @return List<OrderVO> - 검색된 주문 목록
     */
	@Override
	public List<OrderVO> getAllOrderList(OrderSearchCriteria cri) {
		return session.selectList(NAMESPACE + ".getAllOrderList", cri);
	}

    /**
     * 해당 상품의 재고 개수를 반환한다.
     *
     * @param pdNo - 상품 고유번호
     * @return 상품 재고
     */
	@Override
	public int getStock(Integer pdNo) {
		return session.selectOne(NAMESPACE + ".getStock", pdNo);
	}

    /**
     * 검색된 주문의 총 개수를 목록으로 가져온다.
     *
     * @param OrderSearchCriteria - 검색 정보
     * @return List<OrderVO> - 검색된 주문 목록
     */
	@Override
	public List<OrderVO> countAllOrderList(OrderSearchCriteria cri) {
		return session.selectList(NAMESPACE + ".countAllOrderList", cri);
	}

    /**
     * 선택된 주문의 정보를 수정한다.
     *
     * @param OrderVO - 주문 정보
     * @return
     */
	@Override
	public void modifyCheckedOrder(OrderVO order) {
		session.update(NAMESPACE + ".modifyCheckedOrder", order);
	}

    /**
     * 선택된 주문을 주문테이블에서 삭제한다.
     *
     * @param List<Integer> - 주문 고유번호 목록
     * @return
     */
	@Override
	public void deleteOrders(List<Integer> orderList) {
		session.delete(NAMESPACE + ".deleteOrders", orderList);
	}
	
}
