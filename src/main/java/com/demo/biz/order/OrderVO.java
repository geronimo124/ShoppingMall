package com.demo.biz.order;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class OrderVO implements Serializable {

	private Integer ordNo;
	private String mbId;
	private String ordNm;
	private String ordZip;
	private String ordAddr;
	private String ordDeaddr;
	private String ordPhone;
	private Integer ordPrice;
	private String ordMsg;
	private String ordStatus;
	private Date ordDt;
	
	private String pdNm;

	public Integer getOrdNo() {
		return ordNo;
	}

	public void setOrdNo(Integer ordNo) {
		this.ordNo = ordNo;
	}

	public String getMbId() {
		return mbId;
	}

	public void setMbId(String mbId) {
		this.mbId = mbId;
	}

	public String getOrdNm() {
		return ordNm;
	}

	public void setOrdNm(String ordNm) {
		this.ordNm = ordNm;
	}

	public String getOrdZip() {
		return ordZip;
	}

	public void setOrdZip(String ordZip) {
		this.ordZip = ordZip;
	}

	public String getOrdAddr() {
		return ordAddr;
	}

	public void setOrdAddr(String ordAddr) {
		this.ordAddr = ordAddr;
	}

	public String getOrdDeaddr() {
		return ordDeaddr;
	}

	public void setOrdDeaddr(String ordDeaddr) {
		this.ordDeaddr = ordDeaddr;
	}

	public String getOrdPhone() {
		return ordPhone;
	}

	public void setOrdPhone(String ordPhone) {
		this.ordPhone = ordPhone;
	}

	public Integer getOrdPrice() {
		return ordPrice;
	}

	public void setOrdPrice(Integer ordPrice) {
		this.ordPrice = ordPrice;
	}

	public String getOrdMsg() {
		return ordMsg;
	}

	public void setOrdMsg(String ordMsg) {
		this.ordMsg = ordMsg;
	}

	public String getOrdStatus() {
		return ordStatus;
	}

	public void setOrdStatus(String ordStatus) {
		this.ordStatus = ordStatus;
	}

	public Date getOrdDt() {
		return ordDt;
	}

	public void setOrdDt(Date ordDt) {
		this.ordDt = ordDt;
	}

	public String getPdNm() {
		return pdNm;
	}

	public void setPdNm(String pdNm) {
		this.pdNm = pdNm;
	}

	@Override
	public String toString() {
		return "OrderVO [ordNo=" + ordNo + ", mbId=" + mbId + ", ordNm=" + ordNm + ", ordZip=" + ordZip + ", ordAddr="
				+ ordAddr + ", ordDeaddr=" + ordDeaddr + ", ordPhone=" + ordPhone + ", ordPrice=" + ordPrice
				+ ", ordMsg=" + ordMsg + ", ordStatus=" + ordStatus + ", ordDt=" + ordDt + ", pdNm=" + pdNm + "]";
	}
}
