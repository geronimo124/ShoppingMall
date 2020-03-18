package com.demo.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OrderDetailVO implements Serializable {

	private Integer ono;
	private Integer pno;
	private Integer quantity;
	private Integer price;
	
	public Integer getOno() {
		return ono;
	}
	public void setOno(Integer ono) {
		this.ono = ono;
	}
	public Integer getPno() {
		return pno;
	}
	public void setPno(Integer pno) {
		this.pno = pno;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "OrderDetailVO [ono=" + ono + ", pno=" + pno + ", quantity=" + quantity + ", price=" + price + "]";
	}
}
