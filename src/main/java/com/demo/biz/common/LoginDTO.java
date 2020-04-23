package com.demo.biz.common;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : LoginDTO.java
 * @Description : 사용자 로그인을 위한 DTO 클래스
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
public class LoginDTO implements Serializable {

    /**
     * 사용자 ID
     */
	private String id;
    /**
     * 사용자 PW
     */
	private String password;
	
}
