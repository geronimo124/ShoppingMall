package com.demo.biz.common;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@ToString
public class EmailDTO implements Serializable {

	private String senderName;
	private String senderMail;
	private String receiveMail;
	private String subject;
	private String message;
	
}
