package com.demo.biz.product;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : CategoryVO.java
 * @Description : 카테고리 정보 VO 클래스
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
public class CategoryVO implements Serializable {
	
	/**
	 * 카테고리 고유코드
	 */
	private Integer ctgyCd;
	/**
	 * 부모카테고리 고유코드
	 */
	private Integer ctgyParent;
	/**
	 * 카테고리 이름
	 */
	private String ctgyNm;
	
}
