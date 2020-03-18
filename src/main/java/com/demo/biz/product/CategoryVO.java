package com.demo.biz.product;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CategoryVO implements Serializable {
	
	private Integer ctgyCd;
	private Integer ctgyParent;
	private String ctgyNm;
	
	public Integer getCtgyCd() {
		return ctgyCd;
	}
	public void setCtgyCd(Integer ctgyCd) {
		this.ctgyCd = ctgyCd;
	}
	public Integer getCtgyParent() {
		return ctgyParent;
	}
	public void setCtgyParent(Integer ctgyParent) {
		this.ctgyParent = ctgyParent;
	}
	public String getCtgyNm() {
		return ctgyNm;
	}
	public void setCtgyNm(String ctgyNm) {
		this.ctgyNm = ctgyNm;
	}
	
	@Override
	public String toString() {
		return "CategoryVO [ctgyCd=" + ctgyCd + ", ctgyParent=" + ctgyParent + ", ctgyNm=" + ctgyNm + "]";
	}
}
