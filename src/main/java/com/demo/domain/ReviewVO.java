package com.demo.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ReviewVO implements Serializable {

	private Integer rno;
	private Integer pno;
	private Integer ono;
	private String id;
	private String title;
	private String content;
	private Integer grade;
	private Date regDate;
	
	public Integer getRno() {
		return rno;
	}
	public void setRno(Integer rno) {
		this.rno = rno;
	}
	public Integer getPno() {
		return pno;
	}
	public void setPno(Integer pno) {
		this.pno = pno;
	}
	public Integer getOno() {
		return ono;
	}
	public void setOno(Integer ono) {
		this.ono = ono;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "ReviewVO [rno=" + rno + ", pno=" + pno + ", ono=" + ono + ", id=" + id + ", title=" + title
				+ ", content=" + content + ", grade=" + grade + ", regDate=" + regDate + "]";
	}
}
