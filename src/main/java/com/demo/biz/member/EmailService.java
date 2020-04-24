package com.demo.biz.member;

import java.util.List;

import com.demo.biz.common.EmailDTO;

/**
 * @ClassName : EmailService.java
 * @Description : 관리자의 이메일 전송을 위한 서비스 인터페이스
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
public interface EmailService {

    /**
     * 이메일을 전송한다.
     *
     * @param EmailDTO 입력한 이메일 정보
     * @return
     */
	public void sendMail(EmailDTO dto);
	
    /**
     * 입력받은 ID의 사용자 정보를 가져온다.
     *
     * @param mbId 사용자 ID
     * @return MemberVO - 사용자 정보
     */
	public MemberVO getMember(String mbId);
	
    /**
     * 모든 사용자 정보를 가져온다.
     *
     * @param
     * @return List - 모든 사용자 정보 리스트
     */
	public List<MemberVO> getAllMembers();
	
}