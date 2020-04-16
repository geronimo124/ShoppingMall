package com.demo.biz.admin;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@ToString
public class AdminVO implements Serializable {

	private String admId;
	private String admPw;
	private String admNm;
	private Date admCondt;
	
}
