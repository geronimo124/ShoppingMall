package com.demo.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class OrderVO implements Serializable {
	
	private Integer ono;
	private String id;
	private String name;
	private String zip;
	private String addr;
	private String deAddr;
	private String phone;
	private Integer price;
	private Date regDate;
	
	public Integer getOno() {
		return ono;
	}
	public void setOno(Integer ono) {
		this.ono = ono;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getDeAddr() {
		return deAddr;
	}
	public void setDeAddr(String deAddr) {
		this.deAddr = deAddr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "OrderVO [ono=" + ono + ", id=" + id + ", name=" + name + ", zip=" + zip + ", addr=" + addr + ", deAddr="
				+ deAddr + ", phone=" + phone + ", price=" + price + ", regDate=" + regDate + "]";
	}
}
