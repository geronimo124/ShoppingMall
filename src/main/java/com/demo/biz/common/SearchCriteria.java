package com.demo.biz.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchCriteria extends Criteria{

	private String searchType;
	private String keyword;
	
}