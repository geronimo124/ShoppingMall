package com.demo.biz.notice;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class NoticeVO implements Serializable {

	private Integer ntNo;
	private String admId;
	private String admNm;
	private String ntTitle;
	private String ntContent;
	private Date ntDt;
	
	public Integer getNtNo() {
		return ntNo;
	}
	public void setNtNo(Integer ntNo) {
		this.ntNo = ntNo;
	}
	public String getAdmId() {
		return admId;
	}
	public void setAdmId(String admId) {
		this.admId = admId;
	}
	public String getAdmNm() {
		return admNm;
	}
	public void setAdmNm(String admNm) {
		this.admNm = admNm;
	}
	public String getNtTitle() {
		return ntTitle;
	}
	public void setNtTitle(String ntTitle) {
		this.ntTitle = ntTitle;
	}
	public String getNtContent() {
		return ntContent;
	}
	public void setNtContent(String ntContent) {
		this.ntContent = ntContent;
	}
	public Date getNtDt() {
		return ntDt;
	}
	public void setNtDt(Date ntDt) {
		this.ntDt = ntDt;
	}
	
	@Override
	public String toString() {
		return "NoticeVO [ntNo=" + ntNo + ", admId=" + admId + ", admNm=" + admNm + ", ntTitle=" + ntTitle
				+ ", ntContent=" + ntContent + ", ntDt=" + ntDt + "]";
	}
}
