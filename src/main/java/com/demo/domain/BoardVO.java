package com.demo.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class BoardVO implements Serializable {

	private Integer bno;
	private String id;
	private String title;
	private String content;
	private Date regDate;
	
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
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
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", id=" + id + ", title=" + title + ", content=" + content + ", regDate="
				+ regDate + "]";
	}
}
