package com.demo.biz.common;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@ToString
public class LoginDTO implements Serializable {

	private String id;
	private String password;
	
}
