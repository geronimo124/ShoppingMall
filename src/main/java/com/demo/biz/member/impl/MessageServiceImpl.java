package com.demo.biz.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.biz.member.MemberVO;
import com.demo.biz.member.MessageDAO;
import com.demo.biz.member.MessageService;
import com.demo.biz.member.MessageVO;

/**
 * @ClassName : MessageServiceImpl.java
 * @Description : 메시지 관리를 위한 서비스 클래스
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
@Service
public class MessageServiceImpl implements MessageService {

	private final MessageDAO dao;
	
	@Autowired
	public MessageServiceImpl(MessageDAO dao) {
		this.dao = dao;
	}

    /**
     * 메시지를 전송한 후 성공과 실패를 반환한다.
     *
     * @param MessageVO 전송할 메시지 정보
     * @return 메시지 송신의 결과
     */
	@Override
	public boolean sendMsg(MessageVO vo) {
		MemberVO member = dao.getMember(vo.getMsgTarget());
		
		if(member == null)
			return false;
		
		dao.sendMsg(vo);
		
		return true;
	}

    /**
     * 해당 회원이 받았지만 읽지않은 모든 메시지를 가져온다.
     *
     * @param mbId 회원 ID
     * @return List - 미확인 메시지 리스트
     */
	@Override
	public List<MessageVO> getMsgs(String mbId) {
		return dao.getMsgs(mbId);
	}

    /**
     * 선택한 메시지의 상세 정보를 가져온다.
     *
     * @param msgNo 메시지 고유번호
     * @return MessageVO - 메시지 정보
     */
	@Override
	public MessageVO getMsg(Integer msgNo) {
		dao.updateStatus(msgNo);
		return dao.getMsg(msgNo);
	}
}
