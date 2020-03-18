package com.demo.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BasketVO implements Serializable {

	private String id;
	private Integer pno;
	private Integer quantity;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		return "BasketVO [id=" + id + ", pno=" + pno + ", quantity=" + quantity + "]";
	}
}
