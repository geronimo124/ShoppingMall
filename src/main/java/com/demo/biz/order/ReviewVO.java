package com.demo.biz.order;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : ReviewVO.java
 * @Description : 상품 리뷰 정보 VO 클래스
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
public class ReviewVO implements Serializable{

	/**
	 * 리뷰 고유번호
	 */
	private Integer revNo;
	/**
	 * 상품 고유번호
	 */
	private Integer orddtPdNo;
	/**
	 * 주문 고유번호
	 */
	private Integer orddtOrdNo;
	/**
	 * 리뷰 작성자 ID
	 */
	private String mbId;
	/**
	 * 리뷰 제목
	 */
	private String revTitle;
	/**
	 * 리뷰 작성자 닉네임
	 */
	private String revWriter;
	/**
	 * 리뷰 내용
	 */
	private String revContent;
	/**
	 * 리뷰 별점
	 */
	private Integer revGrade;
	/**
	 * 리뷰 작성일자
	 */
	private Date revDt;
	
}