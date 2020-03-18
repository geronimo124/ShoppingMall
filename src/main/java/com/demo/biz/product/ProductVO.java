package com.demo.biz.product;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("serial")
public class ProductVO implements Serializable {

	private Integer pdNo;
	private Integer ctgyPtcd;
	private String ctgyPtnm;
	private Integer ctgyCd;
	private String ctgyNm;
	private String pdNm;
	private Integer pdTag;
	private Integer pdSale;
	private String pdStatus;
	private String pdImg;
	private String pdDetl;
	private Integer pdStock;
	private Date pdEnldt;
	private Date pdUpddt;
	
	private MultipartFile file;

	public Integer getPdNo() {
		return pdNo;
	}

	public void setPdNo(Integer pdNo) {
		this.pdNo = pdNo;
	}

	public Integer getCtgyPtcd() {
		return ctgyPtcd;
	}

	public void setCtgyPtcd(Integer ctgyPtcd) {
		this.ctgyPtcd = ctgyPtcd;
	}

	public String getCtgyPtnm() {
		return ctgyPtnm;
	}

	public void setCtgyPtnm(String ctgyPtnm) {
		this.ctgyPtnm = ctgyPtnm;
	}

	public Integer getCtgyCd() {
		return ctgyCd;
	}

	public void setCtgyCd(Integer ctgyCd) {
		this.ctgyCd = ctgyCd;
	}

	public String getCtgyNm() {
		return ctgyNm;
	}

	public void setCtgyNm(String ctgyNm) {
		this.ctgyNm = ctgyNm;
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

	public String getPdStatus() {
		return pdStatus;
	}

	public void setPdStatus(String pdStatus) {
		this.pdStatus = pdStatus;
	}

	public String getPdImg() {
		return pdImg;
	}

	public void setPdImg(String pdImg) {
		this.pdImg = pdImg;
	}

	public String getPdDetl() {
		return pdDetl;
	}

	public void setPdDetl(String pdDetl) {
		this.pdDetl = pdDetl;
	}

	public Integer getPdStock() {
		return pdStock;
	}

	public void setPdStock(Integer pdStock) {
		this.pdStock = pdStock;
	}

	public Date getPdEnldt() {
		return pdEnldt;
	}

	public void setPdEnldt(Date pdEnldt) {
		this.pdEnldt = pdEnldt;
	}

	public Date getPdUpddt() {
		return pdUpddt;
	}

	public void setPdUpddt(Date pdUpddt) {
		this.pdUpddt = pdUpddt;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "ProductVO [pdNo=" + pdNo + ", ctgyPtcd=" + ctgyPtcd + ", ctgyPtnm=" + ctgyPtnm + ", ctgyCd=" + ctgyCd
				+ ", ctgyNm=" + ctgyNm + ", pdNm=" + pdNm + ", pdTag=" + pdTag + ", pdSale=" + pdSale + ", pdStatus="
				+ pdStatus + ", pdImg=" + pdImg + ", pdDetl=" + pdDetl + ", pdStock=" + pdStock + ", pdEnldt=" + pdEnldt
				+ ", pdUpddt=" + pdUpddt + ", file=" + file + "]";
	}
}
