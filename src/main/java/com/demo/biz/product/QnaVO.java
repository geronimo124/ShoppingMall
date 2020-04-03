package com.demo.biz.product;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class QnaVO implements Serializable {

	private Integer qnaNo;
	private Integer qnaGroup;
	private Integer qnaStep;
	private Integer qnaLevel;
	private String mbId;
	private Integer pdNo;
	private String qnaTitle;
	private String qnaWriter;
	private String qnaContent;
	private Date qnaDt;
	
	public Integer getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(Integer qnaNo) {
		this.qnaNo = qnaNo;
	}
	public Integer getQnaGroup() {
		return qnaGroup;
	}
	public void setQnaGroup(Integer qnaGroup) {
		this.qnaGroup = qnaGroup;
	}
	public Integer getQnaStep() {
		return qnaStep;
	}
	public void setQnaStep(Integer qnaStep) {
		this.qnaStep = qnaStep;
	}
	public Integer getQnaLevel() {
		return qnaLevel;
	}
	public void setQnaLevel(Integer qnaLevel) {
		this.qnaLevel = qnaLevel;
	}
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
	public String getQnaTitle() {
		return qnaTitle;
	}
	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}
	public String getQnaWriter() {
		return qnaWriter;
	}
	public void setQnaWriter(String qnaWriter) {
		this.qnaWriter = qnaWriter;
	}
	public String getQnaContent() {
		return qnaContent;
	}
	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}
	public Date getQnaDt() {
		return qnaDt;
	}
	public void setQnaDt(Date qnaDt) {
		this.qnaDt = qnaDt;
	}
	
	@Override
	public String toString() {
		return "QnaVO [qnaNo=" + qnaNo + ", qnaGroup=" + qnaGroup + ", qnaStep=" + qnaStep + ", qnaLevel=" + qnaLevel
				+ ", mbId=" + mbId + ", pdNo=" + pdNo + ", qnaTitle=" + qnaTitle + ", qnaWriter=" + qnaWriter
				+ ", qnaContent=" + qnaContent + ", qnaDt=" + qnaDt + "]";
	}
}
