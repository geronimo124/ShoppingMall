package com.demo.biz.member.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.biz.member.MemberVO;
import com.demo.biz.member.MessageDAO;
import com.demo.biz.member.MessageVO;

/**
 * @ClassName : MessageDAOImpl.java
 * @Description : 메시지 관리를 위한 데이터 접근 클래스
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
@Repository
public class MessageDAOImpl implements MessageDAO {

	private final static String NAMESPACE = "com.demo.mapper.MessageMapper";

	private final SqlSession session;
	
	@Autowired
	public MessageDAOImpl(SqlSession session) {
		this.session = session;
	}

    /**
     * 메시지를 전송한다.
     *
     * @param MessageVO 전송할 메시지 정보
     * @return
     */
	@Override
	public void sendMsg(MessageVO vo) {
		session.insert(NAMESPACE + ".sendMsg", vo);
	}

    /**
     * 해당 ID의 회원 정보를 가져온다.
     *
     * @param mbId 회원 ID
     * @return MemberVO - 회원 정보
     */
	@Override
	public MemberVO getMember(String mbId) {
		return session.selectOne(NAMESPACE + ".getMember", mbId);
	}

    /**
     * 해당 회원이 받았지만 읽지않은 모든 메시지를 가져온다.
     *
     * @param mbId 회원 ID
     * @return List - 미확인 메시지 리스트
     */
	@Override
	public List<MessageVO> getMsgs(String mbId) {
		return session.selectList(NAMESPACE + ".getMsgs", mbId);
	}

    /**
     * 선택한 메시지의 상세 정보를 가져온다.
     *
     * @param msgNo 메시지 고유번호
     * @return MessageVO - 메시지 정보
     */
	@Override
	public MessageVO getMsg(Integer msgNo) {
		return session.selectOne(NAMESPACE + ".getMsg", msgNo);
	}

    /**
     * 메시지의 상태를 '읽음'으로 업데이트한다.
     *
     * @param msgNo 메시지 고유번호
     * @return
     */
	@Override
	public void updateStatus(Integer msgNo) {
		session.update(NAMESPACE + ".updateStatus", msgNo);
	}
}
