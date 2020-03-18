package com.demo.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class QnaVO implements Serializable {

	private Integer qno;
	private String id;
	private Integer pno;
	private String title;
	private String content;
	private String answer;
	private Date regDate;
	private String status;
	
	public Integer getQno() {
		return qno;
	}
	public void setQno(Integer qno) {
		this.qno = qno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getPno() {
		return pno;
	}
	public void setPno(Integer pno) {
		this.pno = pno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "QnaVO [qno=" + qno + ", id=" + id + ", pno=" + pno + ", title=" + title + ", content=" + content
				+ ", answer=" + answer + ", regDate=" + regDate + ", status=" + status + "]";
	}
}
