package com.demo.biz.order;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@Builder
@ToString
public class OrderDetailVO implements Serializable {
	
	private Integer ordNo;
	private Integer pdNo;
	private Integer orddtQty;
	private Integer orddtPrice;
	
}
