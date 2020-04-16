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
public class MessageVO implements Serializable {

	private Integer msgNo;
	private String msgTarget;
	private String msgSender;
	private String msgContent;
	private String msgStatus;
	private Date msgOpdt;
	private Date msgSddt;
	
}
