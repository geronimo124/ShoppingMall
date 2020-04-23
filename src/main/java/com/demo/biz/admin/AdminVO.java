package com.demo.biz.admin;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : AdminVO.java
 * @Description : 관리자 정보 VO 클래스
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
public class AdminVO implements Serializable {

    /**
     * 관리자 아이디
     */
	private String admId;
    /**
     * 관리자 비밀번호
     */
	private String admPw;
    /**
     * 관리자 이름
     */
	private String admNm;
    /**
     * 관리자 접속일자
     */
	private Date admCondt;
	
}
