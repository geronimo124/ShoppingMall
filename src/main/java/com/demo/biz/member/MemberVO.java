package com.demo.biz.member;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@ToString
public class MemberVO implements Serializable {

	private String mbId;
	private String mbPw;
	private String mbNm;
	private String mbNick;
	private String mbPhone;
	private String mbEmail;
	private String mbZip;
	private String mbAddr;
	private String mbDeaddr;
	private Integer mbMile;
	private Date mbRegdt;
	private Date mbCondt;
	private String mbAuthkey;
	private String mbAuth;
	
}
