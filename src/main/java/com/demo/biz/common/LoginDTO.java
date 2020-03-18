package com.demo.biz.common;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LoginDTO implements Serializable {

	private String id;
	private String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "LoginDTO [id=" + id + ", password=" + password + "]";
	}
}
