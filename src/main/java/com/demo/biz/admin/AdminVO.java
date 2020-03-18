package com.demo.biz.admin;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class AdminVO implements Serializable {

	private String admId;
	private String admPw;
	private String admNm;
	private Date admCondt;
	
	public String getAdmId() {
		return admId;
	}
	public void setAdmId(String admId) {
		this.admId = admId;
	}
	public String getAdmPw() {
		return admPw;
	}
	public void setAdmPw(String admPw) {
		this.admPw = admPw;
	}
	public String getAdmNm() {
		return admNm;
	}
	public void setAdmNm(String admNm) {
		this.admNm = admNm;
	}
	public Date getAdmCondt() {
		return admCondt;
	}
	public void setAdmCondt(Date admCondt) {
		this.admCondt = admCondt;
	}
	
	@Override
	public String toString() {
		return "AdminVO [admId=" + admId + ", admPw=" + admPw + ", admNm=" + admNm + ", admCondt=" + admCondt + "]";
	}
}
