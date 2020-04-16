package com.demo.biz.notice;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@ToString
public class NoticeVO implements Serializable {

	private Integer ntNo;
	private String admId;
	private String admNm;
	private String ntTitle;
	private String ntContent;
	private Date ntDt;
	
}
