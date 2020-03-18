package com.demo.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SupplierVO implements Serializable {

	private String id;
	private String password;
	private String name;
	private String phone;
	private String email;
	private String zip;
	private String addr;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	@Override
	public String toString() {
		return "SupplierVO [id=" + id + ", password=" + password + ", name=" + name + ", phone=" + phone + ", email="
				+ email + ", zip=" + zip + ", addr=" + addr + "]";
	}
}
