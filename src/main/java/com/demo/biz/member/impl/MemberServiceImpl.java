package com.demo.biz.member.impl;

import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.biz.common.LoginDTO;
import com.demo.biz.common.SearchCriteria;
import com.demo.biz.member.MemberDAO;
import com.demo.biz.member.MemberService;
import com.demo.biz.member.MemberVO;

/**
 * @ClassName : MemberServiceImpl.java
 * @Description : 사용자 정보의 관리를 위한 서비스 클래스
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
public class MemberServiceImpl implements MemberService {

	private final MemberDAO dao;
	
	private final BCryptPasswordEncoder crptPassEnc; 
	
	private final JavaMailSender mailSender;
	
	@Autowired
	public MemberServiceImpl(MemberDAO dao, BCryptPasswordEncoder crptPassEnc, JavaMailSender mailSender) {
		this.dao = dao;
		this.crptPassEnc = crptPassEnc;
		this.mailSender = mailSender;
	}
	
    /**
     * 입력받은 값으로 로그인을 시도한다.
     *
     * @param LoginDTO 사용자에게 입력받은 ID와 PW
     * @return MemberVO - 로그인을 시도한 사용자 정보
     */
	@Override
	public MemberVO loginMember(LoginDTO dto) {
		MemberVO vo = dao.loginMember(dto);
		
		if(vo != null) {
			
			if(crptPassEnc.matches(dto.getPassword(), vo.getMbPw()))
				dao.updateConDate(dto);
			else
				vo = null;
		}
		
		return vo;
	}

    /**
     * 입력받은 이메일로 로그인을 시도한다. (Facebook OAuth)
     *
     * @param mbEmail 사용자에게 입력받은 이메일
     * @return MemberVO - 로그인을 시도한 사용자 정보
     */
	@Override
	public MemberVO loginMember(String mbEmail) {
		return dao.loginMember(mbEmail);
	}
	
    /**
     * 회원가입을 할 때, 아이디가 중복되는지 확인한다.
     *
     * @param mbId 중복확인 ID
     * @return 아이디 중복 여부 반환
     */
	@Override
	public int checkId(String mbId) {
		return dao.checkId(mbId);
	}

    /**
     * 회원 가입을 한다.
     *
     * @param MemberVO 회원가입 정보
     * @return
     */
	@Override
	public void insertMember(MemberVO vo) {
		
		vo.setMbPw(crptPassEnc.encode(vo.getMbPw()));
		vo.setMbAuthkey(crptPassEnc.encode(vo.getMbId()));
		
		// 회원가입 후 인증키 메일을 전송
		try {
			
			MimeMessage msg = mailSender.createMimeMessage();
			
			msg.addRecipient(RecipientType.TO, new InternetAddress(vo.getMbEmail()));
			
			msg.addFrom(new InternetAddress[] { new InternetAddress("temp@temp.com", "어쩌구쇼핑몰") });
			
			msg.setSubject("회원가입 인증키 메일", "utf-8");
			
			msg.setText(vo.getMbAuthkey(), "utf-8");
			
			mailSender.send(msg);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		dao.insertMember(vo);
	}

    /**
     * 아이디에 해당하는 회원 정보를 반환한다.
     *
     * @param mbId 회원 ID
     * @return MemberVO - 회원 정보
     */
	@Override
	public MemberVO getMember(String mbId) {
		return dao.getMember(mbId);
	}

    /**
     * 회원가입 후, 이메일 인증이 완료되면 인증 상태를 업데이트한다.
     *
     * @param mbId 회원가입 ID
     * @return
     */
	@Override
	public void updateAuth(String mbId) {
		dao.updateAuth(mbId);
	}

    /**
     * 회원 정보를 수정한다.
     *
     * @param MemberVO 입력받은 회원 정보
     * @return
     */
	@Override
	public void updateMember(MemberVO vo) {
		vo.setMbPw(crptPassEnc.encode(vo.getMbPw()));
		dao.updateMember(vo);
	}

    /**
     * 검색된 모든 회원 정보를 가져온다.
     *
     * @param SearchCriteria 검색 정보
     * @return List - 검색된 회원 정보 리스트
     */
	@Override
	public List<MemberVO> getMemberList(SearchCriteria cri) {
		return dao.getMemberList(cri);
	}

    /**
     * 검색된 회원의 총 수를 반환한다.
     *
     * @param SearchCriteria 검색 정보
     * @return 검색된 회원의 총 수
     */
	@Override
	public int countMemberList(SearchCriteria cri) {
		return dao.countMemberList(cri);
	}

    /**
     * 선택된 회원들을 탈퇴시킨다.
     *
     * @param List 탈퇴시킬 회원 ID 리스트
     * @return
     */
	@Transactional
	@Override
	public void deleteMembers(List<String> memberList) {
		
		for(String mbId : memberList)
			dao.deleteMember(mbId);
		
	}

}
