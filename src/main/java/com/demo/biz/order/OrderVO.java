package com.demo.biz.order;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : OrderVO.java
 * @Description : 주문 정보 VO 클래스
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
@ToString
public class OrderVO implements Serializable {

	/**
	 * 주문 고유번호
	 */
	private Integer ordNo;
	/**
	 * 주문자 ID
	 */
	private String mbId;
	/**
	 * 수령인 이름
	 */
	private String ordNm;
	/**
	 * 수령인 우편번호
	 */
	private String ordZip;
	/**
	 * 수령인 주소
	 */
	private String ordAddr;
	/**
	 * 수령인 상세주소
	 */
	private String ordDeaddr;
	/**
	 * 수령인 전화번호
	 */
	private String ordPhone;
	/**
	 * 주문 총액
	 */
	private Integer ordPrice;
	/**
	 * 주문시 메시지
	 */
	private String ordMsg;
	/**
	 * 주문 상태
	 */
	private String ordStatus;
	/**
	 * 주문일자
	 */
	private Date ordDt;
	
	
	
	/**
	 * 상품 이름
	 */
	private String pdNm;

}
