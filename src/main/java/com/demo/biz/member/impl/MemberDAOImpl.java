package com.demo.biz.member.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.biz.common.LoginDTO;
import com.demo.biz.common.SearchCriteria;
import com.demo.biz.member.MemberDAO;
import com.demo.biz.member.MemberVO;

/**
 * @ClassName : MemberDAOImpl.java
 * @Description : 사용자 정보의 관리를 위한 데이터 접근 클래스
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
public class MemberDAOImpl implements MemberDAO {

	private final static String NAMESPACE = "com.demo.mapper.MemberMapper";

	private final SqlSession session;
	
	@Autowired
	public MemberDAOImpl(SqlSession session) {
		this.session = session;
	}

    /**
     * 입력받은 값으로 로그인을 시도한다.
     *
     * @param LoginDTO 사용자에게 입력받은 ID와 PW
     * @return MemberVO - 로그인을 시도한 사용자 정보
     */
	@Override
	public MemberVO loginMember(LoginDTO dto) {
		return session.selectOne(NAMESPACE + ".login", dto);
	}
	
    /**
     * 입력받은 이메일로 로그인을 시도한다. (Facebook OAuth)
     *
     * @param mbEmail 사용자에게 입력받은 이메일
     * @return MemberVO - 로그인을 시도한 사용자 정보
     */
	@Override
	public MemberVO loginMember(String mbEmail) {
		return session.selectOne(NAMESPACE + ".loginFacebook", mbEmail);
	}
	
    /**
     * 회원가입을 할 때, 아이디가 중복되는지 확인한다.
     *
     * @param mbId 중복확인 ID
     * @return 아이디 중복 여부 반환
     */
	@Override
	public int checkId(String mbId) {
		return session.selectOne(NAMESPACE + ".checkId", mbId);
	}
	
    /**
     * 회원 가입을 한다.
     *
     * @param MemberVO 회원가입 정보
     * @return
     */
	@Override
	public void insertMember(MemberVO vo) {
		session.insert(NAMESPACE + ".insert", vo);
	}

    /**
     * 회원가입 후, 이메일 인증이 완료되면 인증 상태를 업데이트한다.
     *
     * @param mbId 회원가입 ID
     * @return
     */
	@Override
	public void updateConDate(LoginDTO dto) {
		session.update(NAMESPACE + ".updateConDate", dto);
	}

    /**
     * 아이디에 해당하는 회원 정보를 반환한다.
     *
     * @param mbId 회원 ID
     * @return MemberVO - 회원 정보
     */
	@Override
	public MemberVO getMember(String mbId) {
		return session.selectOne(NAMESPACE + ".getMember", mbId);
	}

    /**
     * 회원가입 후, 이메일 인증이 완료되면 인증 상태를 업데이트한다.
     *
     * @param mbId 회원가입 ID
     * @return
     */
	@Override
	public void updateAuth(String mbId) {
		session.update(NAMESPACE + ".updateAuth", mbId);
	}

    /**
     * 회원 정보를 수정한다.
     *
     * @param MemberVO 입력받은 회원 정보
     * @return
     */
	@Override
	public void updateMember(MemberVO vo) {
		session.update(NAMESPACE + ".update", vo);
	}

    /**
     * 검색된 모든 회원 정보를 가져온다.
     *
     * @param SearchCriteria 검색 정보
     * @return List - 검색된 회원 정보 리스트
     */
	@Override
	public List<MemberVO> getMemberList(SearchCriteria cri) {
		return session.selectList(NAMESPACE + ".getMemberList", cri);
	}

	/**
     * 검색된 회원의 총 수를 반환한다.
     *
     * @param SearchCriteria 검색 정보
     * @return 검색된 회원의 총 수
     */
	@Override
	public int countMemberList(SearchCriteria cri) {
		return session.selectOne(NAMESPACE + ".countMemberList", cri);
	}

    /**
     * 회원 탈퇴를 한다.
     *
     * @param mbId 탈퇴할 회원 ID
     * @return
     */
	@Override
	public void deleteMember(String mbId) {
		session.delete(NAMESPACE + ".deleteMember", mbId);
	}

    /**
     * 모든 회원 정보를 가져온다.
     *
     * @param
     * @return List - 모든 회원 정보 리스트
     */
	@Override
	public List<MemberVO> getAllMembers() {
		return session.selectList(NAMESPACE + ".getAllMembers");
	}
}
