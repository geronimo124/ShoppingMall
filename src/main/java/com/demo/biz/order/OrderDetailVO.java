package com.demo.biz.order;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : OrderDetailVO.java
 * @Description : 주문상세 정보 VO 클래스
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
@SuppressWarnings("serial")
@Getter
@Setter
@Builder
@ToString
public class OrderDetailVO implements Serializable {
	
	/**
	 * 주문 고유번호
	 */
	private Integer ordNo;
	/**
	 * 상품 고유번호
	 */
	private Integer pdNo;
	/**
	 * 주문한 상품 개수
	 */
	private Integer orddtQty;
	/**
	 * 주문한 상품 가격
	 */
	private Integer orddtPrice;
	
}
