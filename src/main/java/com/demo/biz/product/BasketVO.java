package com.demo.biz.product;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : BasketVO.java
 * @Description : 장바구니 정보 VO 클래스
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
@Getter
@Setter
@ToString
@SuppressWarnings("serial")
public class BasketVO implements Serializable {

	/**
	 * 회원 ID
	 */
	private String mbId;
	/**
	 * 상품 고유번호
	 */
	private Integer pdNo;
	/**
	 * 장바구니 수량
	 */
	private Integer bskQty;
	/**
	 * 상품 이미지
	 */
	private String pdImg;
	/**
	 * 상품 이름
	 */
	private String pdNm;
	/**
	 * 상품 가격
	 */
	private Integer pdTag;
	/**
	 * 상품 할인율
	 */
	private Integer pdSale;
	
}
