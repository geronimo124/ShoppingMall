package com.demo.biz.member;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : MemberVO.java
 * @Description : 회원 정보 VO 클래스
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
public class MemberVO implements Serializable {

	/**
	 * 회원 ID
	 */
	private String mbId;
	/**
	 * 회원 PW
	 */
	private String mbPw;
	/**
	 * 회원 이름
	 */
	private String mbNm;
	/**
	 * 회원 닉네임
	 */
	private String mbNick;
	/**
	 * 회원 전화번호
	 */
	private String mbPhone;
	/**
	 * 회원 이메일
	 */
	private String mbEmail;
	/**
	 * 회원 우편번호
	 */
	private String mbZip;
	/**
	 * 회원 주소
	 */
	private String mbAddr;
	/**
	 * 회원 상세주소
	 */
	private String mbDeaddr;
	/**
	 * 회원 적립금
	 */
	private Integer mbMile;
	/**
	 * 회원가입 일자
	 */
	private Date mbRegdt;
	/**
	 * 회원 접속 일자
	 */
	private Date mbCondt;
	/**
	 * 회원 인증키
	 */
	private String mbAuthkey;
	/**
	 * 회원 인증상태
	 */
	private String mbAuth;
	
}
