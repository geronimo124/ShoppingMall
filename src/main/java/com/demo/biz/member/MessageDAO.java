package com.demo.biz.member;

import java.util.List;

/**
 * @ClassName : MessageDAO.java
 * @Description : 메시지 관리를 위한 데이터 접근 인터페이스
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
public interface MessageDAO {

    /**
     * 메시지를 전송한다.
     *
     * @param MessageVO 전송할 메시지 정보
     * @return
     */
	public void sendMsg(MessageVO vo);
	
    /**
     * 해당 ID의 회원 정보를 가져온다.
     *
     * @param mbId 회원 ID
     * @return MemberVO - 회원 정보
     */
	public MemberVO getMember(String mbId);
	
    /**
     * 해당 회원이 받았지만 읽지않은 모든 메시지를 가져온다.
     *
     * @param mbId 회원 ID
     * @return List - 미확인 메시지 리스트
     */
	public List<MessageVO> getMsgs(String mbId);
	
    /**
     * 선택한 메시지의 상세 정보를 가져온다.
     *
     * @param msgNo 메시지 고유번호
     * @return MessageVO - 메시지 정보
     */
	public MessageVO getMsg(Integer msgNo);
	
    /**
     * 메시지의 상태를 '읽음'으로 업데이트한다.
     *
     * @param msgNo 메시지 고유번호
     * @return
     */
	public void updateStatus(Integer msgNo);
	
}
