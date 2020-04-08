package com.demo.view.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.demo.biz.member.MemberVO;

public class EchoHandler extends TextWebSocketHandler {

	private final List<WebSocketSession> sessionList = new ArrayList<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		MemberVO vo = (MemberVO) session.getAttributes().get("member");
		
		sessionList.add(session);
		
		for(WebSocketSession sess : sessionList)
			sess.sendMessage(new TextMessage(vo.getMbNick() + "|" + "님이 입장하였습니다."));
		
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO Auto-generated method stub
		MemberVO vo = (MemberVO) session.getAttributes().get("member");
		
		for(WebSocketSession sess : sessionList)
			sess.sendMessage(new TextMessage(vo.getMbNick() + "|" + message.getPayload()));
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		MemberVO vo = (MemberVO) session.getAttributes().get("member");
		
		sessionList.remove(session);
		
		for(WebSocketSession sess : sessionList)
			sess.sendMessage(new TextMessage(vo.getMbNick() + "|" + "님이 퇴장하였습니다."));
		
	}
}
