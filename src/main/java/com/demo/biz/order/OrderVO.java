package com.demo.biz.order;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@ToString
public class OrderVO implements Serializable {

	private Integer ordNo;
	private String mbId;
	private String ordNm;
	private String ordZip;
	private String ordAddr;
	private String ordDeaddr;
	private String ordPhone;
	private Integer ordPrice;
	private String ordMsg;
	private String ordStatus;
	private Date ordDt;
	
	private String pdNm;

}
