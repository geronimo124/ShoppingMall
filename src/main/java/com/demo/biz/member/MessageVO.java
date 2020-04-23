package com.demo.biz.member;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : MessageVO.java
 * @Description : 메시지 정보 VO 클래스
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
public class MessageVO implements Serializable {

	/**
	 * 메시지 고유번호
	 */
	private Integer msgNo;
	/**
	 * 메시지 수신자
	 */
	private String msgTarget;
	/**
	 * 메시지 송신자
	 */
	private String msgSender;
	/**
	 * 메시지 내용
	 */
	private String msgContent;
	/**
	 * 메시지 읽은 상태
	 */
	private String msgStatus;
	/**
	 * 메시지 확인일자
	 */
	private Date msgOpdt;
	/**
	 * 메시지 송신일자
	 */
	private Date msgSddt;
	
}
