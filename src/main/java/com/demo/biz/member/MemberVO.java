package com.demo.biz.member;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class MemberVO implements Serializable {

	private String mbId;
	private String mbPw;
	private String mbNm;
	private String mbNick;
	private String mbPhone;
	private String mbEmail;
	private String mbZip;
	private String mbAddr;
	private String mbDeAddr;
	private Integer mbMile;
	private Date mbRegdt;
	private Date mbCondt;
	private String mbAuth;
	
	public String getMbId() {
		return mbId;
	}
	public void setMbId(String mbId) {
		this.mbId = mbId;
	}
	public String getMbPw() {
		return mbPw;
	}
	public void setMbPw(String mbPw) {
		this.mbPw = mbPw;
	}
	public String getMbNm() {
		return mbNm;
	}
	public void setMbNm(String mbNm) {
		this.mbNm = mbNm;
	}
	public String getMbNick() {
		return mbNick;
	}
	public void setMbNick(String mbNick) {
		this.mbNick = mbNick;
	}
	public String getMbPhone() {
		return mbPhone;
	}
	public void setMbPhone(String mbPhone) {
		this.mbPhone = mbPhone;
	}
	public String getMbEmail() {
		return mbEmail;
	}
	public void setMbEmail(String mbEmail) {
		this.mbEmail = mbEmail;
	}
	public String getMbZip() {
		return mbZip;
	}
	public void setMbZip(String mbZip) {
		this.mbZip = mbZip;
	}
	public String getMbAddr() {
		return mbAddr;
	}
	public void setMbAddr(String mbAddr) {
		this.mbAddr = mbAddr;
	}
	public String getMbDeAddr() {
		return mbDeAddr;
	}
	public void setMbDeAddr(String mbDeAddr) {
		this.mbDeAddr = mbDeAddr;
	}
	public Integer getMbMile() {
		return mbMile;
	}
	public void setMbMile(Integer mbMile) {
		this.mbMile = mbMile;
	}
	public Date getMbRegdt() {
		return mbRegdt;
	}
	public void setMbRegdt(Date mbRegdt) {
		this.mbRegdt = mbRegdt;
	}
	public Date getMbCondt() {
		return mbCondt;
	}
	public void setMbCondt(Date mbCondt) {
		this.mbCondt = mbCondt;
	}
	public String getMbAuth() {
		return mbAuth;
	}
	public void setMbAuth(String mbAuth) {
		this.mbAuth = mbAuth;
	}
	
	@Override
	public String toString() {
		return "MemberVO [mbId=" + mbId + ", mbPw=" + mbPw + ", mbNm=" + mbNm + ", mbNick=" + mbNick + ", mbPhone="
				+ mbPhone + ", mbEmail=" + mbEmail + ", mbZip=" + mbZip + ", mbAddr=" + mbAddr + ", mbDeAddr="
				+ mbDeAddr + ", mbMile=" + mbMile + ", mbRegdt=" + mbRegdt + ", mbCondt=" + mbCondt + ", mbAuth="
				+ mbAuth + "]";
	}
}
