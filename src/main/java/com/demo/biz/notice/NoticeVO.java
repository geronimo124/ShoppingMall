package com.demo.biz.notice;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : NoticeVO.java
 * @Description : 공지사항 정보 VO 클래스
 * @Modification Information
 *
 *    수정일			수정자		수정내용
 *    -------		-------     -------------------
 *    2020. 4. 23.	전일배		최초생성
 *
 * @author 전일배
 * @since 2020. 4. 23.
 * @version
 * @see
 *
 */
@SuppressWarnings("serial")
@Getter
@Setter
@ToString
public class NoticeVO implements Serializable {

	/**
	 * 공지사항 고유번호
	 */
	private Integer ntNo;
	/**
	 * 공지사항 작성 관리자 ID
	 */
	private String admId;
	/**
	 * 공지사항 작성 관리자 이름
	 */
	private String admNm;
	/**
	 * 공지사항 제목
	 */
	private String ntTitle;
	/**
	 * 공지사항 내용
	 */
	private String ntContent;
	/**
	 * 공지사항 작성일자
	 */
	private Date ntDt;
	
}
