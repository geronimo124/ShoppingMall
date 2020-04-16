package com.demo.biz.order;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@ToString
public class ReviewVO implements Serializable{

	private Integer revNo;
	private Integer orddtPdNo;
	private Integer orddtOrdNo;
	private String mbId;
	private String revTitle;
	private String revWriter;
	private String revContent;
	private Integer revGrade;
	private Date revDt;
	
}