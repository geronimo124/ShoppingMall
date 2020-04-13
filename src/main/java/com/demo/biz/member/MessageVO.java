package com.demo.biz.member;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class MessageVO implements Serializable {

	private Integer msgNo;
	private String msgTarget;
	private String msgSender;
	private String msgContent;
	private String msgStatus;
	private Date msgOpdt;
	private Date msgSddt;
	
	public Integer getMsgNo() {
		return msgNo;
	}
	public void setMsgNo(Integer msgNo) {
		this.msgNo = msgNo;
	}
	public String getMsgTarget() {
		return msgTarget;
	}
	public void setMsgTarget(String msgTarget) {
		this.msgTarget = msgTarget;
	}
	public String getMsgSender() {
		return msgSender;
	}
	public void setMsgSender(String msgSender) {
		this.msgSender = msgSender;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getMsgStatus() {
		return msgStatus;
	}
	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}
	public Date getMsgOpdt() {
		return msgOpdt;
	}
	public void setMsgOpdt(Date msgOpdt) {
		this.msgOpdt = msgOpdt;
	}
	public Date getMsgSddt() {
		return msgSddt;
	}
	public void setMsgSddt(Date msgSddt) {
		this.msgSddt = msgSddt;
	}
	
	@Override
	public String toString() {
		return "MessageVO [msgNo=" + msgNo + ", msgTarget=" + msgTarget + ", msgSender=" + msgSender + ", msgContent="
				+ msgContent + ", msgStatus=" + msgStatus + ", msgOpdt=" + msgOpdt + ", msgSddt=" + msgSddt + "]";
	}
}
