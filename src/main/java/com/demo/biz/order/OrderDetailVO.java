package com.demo.biz.order;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OrderDetailVO implements Serializable {
	
	private Integer ordNo;
	private Integer pdNo;
	private Integer orddtQty;
	private Integer orddtPrice;
	
	public Integer getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(Integer ordNo) {
		this.ordNo = ordNo;
	}
	public Integer getPdNo() {
		return pdNo;
	}
	public void setPdNo(Integer pdNo) {
		this.pdNo = pdNo;
	}
	public Integer getOrddtQty() {
		return orddtQty;
	}
	public void setOrddtQty(Integer orddtQty) {
		this.orddtQty = orddtQty;
	}
	public Integer getOrddtPrice() {
		return orddtPrice;
	}
	public void setOrddtPrice(Integer orddtPrice) {
		this.orddtPrice = orddtPrice;
	}
	
	@Override
	public String toString() {
		return "OrderDetailVO [ordNo=" + ordNo + ", pdNo=" + pdNo + ", orddtQty=" + orddtQty + ", orddtPrice="
				+ orddtPrice + "]";
	}
}
