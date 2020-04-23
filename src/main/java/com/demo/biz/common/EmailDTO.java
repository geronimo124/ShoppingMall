package com.demo.biz.common;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : EmailDTO.java
 * @Description : 관리자의 이메일 전송을 위한 DTO 클래스
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
public class EmailDTO implements Serializable {

    /**
     * 송신자 이름
     */
	private String senderName;
    /**
     * 송신자 메일
     */
	private String senderMail;
    /**
     * 수신자 메일
     */
	private String receiveMail;
    /**
     * 메일 제목
     */
	private String subject;
    /**
     * 메일 내용
     */
	private String message;
	
}
