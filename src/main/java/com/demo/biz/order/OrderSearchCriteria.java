package com.demo.biz.order;

import java.util.HashMap;
import java.util.Map;

import com.demo.biz.common.Criteria;

public class OrderSearchCriteria extends Criteria {
	
	private Map<String, String> searchMap;
	
	public OrderSearchCriteria() {
		searchMap = new HashMap<>();
	}
	
	public String getValue(String searchType) {
		return searchMap.get(searchType);
	}
	
	public void setValue(String searchType, String keyword) {
		searchMap.put(searchType, keyword);
	}
	
	public Map<String, String> getSearchMap() {
		return searchMap;
	}
	
	public void setSearchMap(Map<String, String> searchMap) {
		this.searchMap = searchMap;
	}
	
	public String getMinDate() {
		return searchMap.get("orderDate").split("-")[0];
	}
	
	public String getMaxDate() {
		return searchMap.get("orderDate").split("-")[1].split(" ")[1];
	}

	@Override
	public String toString() {
		return "OrderSearchCriteria [searchMap=" + searchMap + "]";
	}
}
