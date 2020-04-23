package com.demo.biz.product;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : QnaVO.java
 * @Description : QNA 정보 VO 클래스
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
public class QnaVO implements Serializable {

	/**
	 * QNA 고유번호
	 */
	private Integer qnaNo;
	/**
	 * QNA group
	 */
	private Integer qnaGroup;
	/**
	 * QNA step
	 */
	private Integer qnaStep;
	/**
	 * QNA level
	 */
	private Integer qnaLevel;
	/**
	 * 회원 ID
	 */
	private String mbId;
	/**
	 * 상품 고유번호
	 */
	private Integer pdNo;
	/**
	 * QNA 제목
	 */
	private String qnaTitle;
	/**
	 * QNA 작성자
	 */
	private String qnaWriter;
	/**
	 * QNA 내용
	 */
	private String qnaContent;
	/**
	 * QNA 등록일자
	 */
	private Date qnaDt;
	
	
	/**
	 * 상품 이름
	 */
	private String pdNm;
	
}
