package com.demo.biz.order;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ReviewVO implements Serializable{

	private Integer revNo;
	private Integer orddtPdNo;
	private Integer orddtOrdNo;
	private String mbId;
	private String revTitle;
	private String revWriter;
	private String revContent;
	private Integer revGrade;
	private Date revDt;
	
	public Integer getRevNo() {
		return revNo;
	}
	public void setRevNo(Integer revNo) {
		this.revNo = revNo;
	}
	public Integer getOrddtPdNo() {
		return orddtPdNo;
	}
	public void setOrddtPdNo(Integer orddtPdNo) {
		this.orddtPdNo = orddtPdNo;
	}
	public Integer getOrddtOrdNo() {
		return orddtOrdNo;
	}
	public void setOrddtOrdNo(Integer orddtOrdNo) {
		this.orddtOrdNo = orddtOrdNo;
	}
	public String getMbId() {
		return mbId;
	}
	public void setMbId(String mbId) {
		this.mbId = mbId;
	}
	public String getRevTitle() {
		return revTitle;
	}
	public void setRevTitle(String revTitle) {
		this.revTitle = revTitle;
	}
	public String getRevWriter() {
		return revWriter;
	}
	public void setRevWriter(String revWriter) {
		this.revWriter = revWriter;
	}
	public String getRevContent() {
		return revContent;
	}
	public void setRevContent(String revContent) {
		this.revContent = revContent;
	}
	public Integer getRevGrade() {
		return revGrade;
	}
	public void setRevGrade(Integer revGrade) {
		this.revGrade = revGrade;
	}
	public Date getRevDt() {
		return revDt;
	}
	public void setRevDt(Date revDt) {
		this.revDt = revDt;
	}
	
	@Override
	public String toString() {
		return "ReviewVO [revNo=" + revNo + ", orddtPdNo=" + orddtPdNo + ", orddtOrdNo=" + orddtOrdNo + ", mbId=" + mbId
				+ ", revTitle=" + revTitle + ", revWriter=" + revWriter + ", revContent=" + revContent + ", revGrade="
				+ revGrade + ", revDt=" + revDt + "]";
	}
}