package com.demo.biz.order;

import java.util.Map;

import com.demo.biz.common.Criteria;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderSearchCriteria extends Criteria {
	
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
