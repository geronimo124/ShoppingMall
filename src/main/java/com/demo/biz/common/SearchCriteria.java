package com.demo.biz.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : SearchCriteria.java
 * @Description : 검색 페이징을 위한 검색내용과 페이지 정보 클래스
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
public class SearchCriteria extends Criteria{

	/**
	 * 검색 타입
	 */
	private String searchType;
	/**
	 * 검색 키워드
	 */
	private String keyword;
	
}