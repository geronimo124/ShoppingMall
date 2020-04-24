package com.demo.view.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.demo.biz.member.MemberVO;

/**
 * @ClassName : EchoHandler.java
 * @Description : 회원간의 실시간 채팅을 구현하는 핸들러 클래스
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
public class EchoHandler extends TextWebSocketHandler {

	private final List<WebSocketSession> sessionList = new ArrayList<>();

    /**
     * 새로운 회원이 채팅방에 입장하면 입장 알림을 보낸다.
     *
     * @param
     * @return
     * @throws Exception
     */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		MemberVO vo = (MemberVO) session.getAttributes().get("member");
		
		sessionList.add(session);
		
		for(WebSocketSession sess : sessionList)
			sess.sendMessage(new TextMessage(vo.getMbNick() + "|" + "님이 입장하였습니다."));
		
	}

    /**
     * 회원이 채팅 메시지를 전송하면 모든 회원에게 메시지를 보낸다.
     *
     * @param
     * @return
     * @throws Exception
     */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		MemberVO vo = (MemberVO) session.getAttributes().get("member");
		
		for(WebSocketSession sess : sessionList)
			sess.sendMessage(new TextMessage(vo.getMbNick() + "|" + message.getPayload()));
		
	}

    /**
     * 입장한 회원이 채팅방에서 나가면 퇴장 알림을 보낸다.
     *
     * @param
     * @return
     * @throws Exception
     */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		MemberVO vo = (MemberVO) session.getAttributes().get("member");
		
		sessionList.remove(session);
		
		for(WebSocketSession sess : sessionList)
			sess.sendMessage(new TextMessage(vo.getMbNick() + "|" + "님이 퇴장하였습니다."));
		
	}
}
