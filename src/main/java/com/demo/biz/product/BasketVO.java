package com.demo.biz.product;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BasketVO implements Serializable {

	private String mbId;
	private Integer pdNo;
	private Integer bskQty;
	private String pdImg;
	private String pdNm;
	private Integer pdTag;
	private Integer pdSale;
	
	public String getMbId() {
		return mbId;
	}
	public void setMbId(String mbId) {
		this.mbId = mbId;
	}
	public Integer getPdNo() {
		return pdNo;
	}
	public void setPdNo(Integer pdNo) {
		this.pdNo = pdNo;
	}
	public Integer getBskQty() {
		return bskQty;
	}
	public void setBskQty(Integer bskQty) {
		this.bskQty = bskQty;
	}
	public String getPdImg() {
		return pdImg;
	}
	public void setPdImg(String pdImg) {
		this.pdImg = pdImg;
	}
	public String getPdNm() {
		return pdNm;
	}
	public void setPdNm(String pdNm) {
		this.pdNm = pdNm;
	}
	public Integer getPdTag() {
		return pdTag;
	}
	public void setPdTag(Integer pdTag) {
		this.pdTag = pdTag;
	}
	public Integer getPdSale() {
		return pdSale;
	}
	public void setPdSale(Integer pdSale) {
		this.pdSale = pdSale;
	}
	
	@Override
	public String toString() {
		return "BasketVO [mbId=" + mbId + ", pdNo=" + pdNo + ", bskQty=" + bskQty + ", pdImg=" + pdImg + ", pdNm="
				+ pdNm + ", pdTag=" + pdTag + ", pdSale=" + pdSale + "]";
	}
}
