package com.demo.biz.order;

import java.util.Map;

import com.demo.biz.common.Criteria;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : OrderSearchCriteria.java
 * @Description : 주문 목록의 검색 페이징을 위한 검색내용과 페이지 정보 클래스
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
public class OrderSearchCriteria extends Criteria {

	/**
	 * 주문 검색 타입과 검색어가 들어있는 Map
	 */
	private Map<String, String> searchMap;
	
	public OrderSearchCriteria(Map<String, String> searchMap) {
		this.searchMap = searchMap;
	}
	
	public String getValue(String searchType) {
		return searchMap.get(searchType);
	}
	
	public void setValue(String searchType, String keyword) {
		searchMap.put(searchType, keyword);
	}
	
	public String getMinDate() {
		return searchMap.get("orderDate").split("-")[0];
	}
	
	public String getMaxDate() {
		return searchMap.get("orderDate").split("-")[1].split(" ")[1];
	}
}
