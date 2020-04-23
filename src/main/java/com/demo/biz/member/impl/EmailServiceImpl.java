package com.demo.biz.member.impl;

import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.demo.biz.common.EmailDTO;
import com.demo.biz.member.EmailService;
import com.demo.biz.member.MemberDAO;
import com.demo.biz.member.MemberVO;

/**
 * @ClassName : EmailServiceImpl.java
 * @Description : 관리자의 이메일 전송을 위한 서비스 클래스
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
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender mailSender;
	
	private final MemberDAO dao;
	
	@Autowired
	public EmailServiceImpl(JavaMailSender mailSender, MemberDAO dao) {
		this.mailSender = mailSender;
		this.dao = dao;
	}
	
    /**
     * 이메일을 전송한다.
     *
     * @param EmailDTO - 입력한 이메일 정보
     * @return
     */
	@Override
	public void sendMail(EmailDTO dto) {
		// TODO Auto-generated method stub
		try {
			
			MimeMessage msg = mailSender.createMimeMessage();
			
			msg.addRecipient(RecipientType.TO, new InternetAddress(dto.getReceiveMail()));
			
			msg.addFrom(new InternetAddress[] { new InternetAddress(dto.getSenderMail(), dto.getSenderName()) });
			
			msg.setSubject(dto.getSubject(), "utf-8");
			
			msg.setText(dto.getMessage(), "utf-8");
			
			mailSender.send(msg);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}
	
    /**
     * 입력받은 ID의 사용자 정보를 가져온다.
     *
     * @param mbId - 사용자 ID
     * @return MemberVO - 사용자 정보
     */
	public MemberVO getMember(String mbId) {
		return dao.getMember(mbId);
	}
	
    /**
     * 모든 사용자 정보를 가져온다.
     *
     * @param
     * @return List<MemberVO> - 모든 사용자 정보 리스트
     */
	public List<MemberVO> getAllMembers() {
		return dao.getAllMembers();
	}
}