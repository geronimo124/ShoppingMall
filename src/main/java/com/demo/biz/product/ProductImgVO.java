package com.demo.biz.product;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProductImgVO implements Serializable {

	private Integer pdNo;
	private String pdImg;
	
	public Integer getPdNo() {
		return pdNo;
	}
	public void setPdNo(Integer pdNo) {
		this.pdNo = pdNo;
	}
	public String getPdImg() {
		return pdImg;
	}
	public void setPdImg(String pdImg) {
		this.pdImg = pdImg;
	}
	
	@Override
	public String toString() {
		return "ProductImgVO [pdNo=" + pdNo + ", pdImg=" + pdImg + "]";
	}
}
