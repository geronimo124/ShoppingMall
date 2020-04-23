package com.demo.biz.member;

import java.util.List;

import com.demo.biz.common.LoginDTO;
import com.demo.biz.common.SearchCriteria;

/**
 * @ClassName : MemberDAO.java
 * @Description : 사용자 정보의 관리를 위한 데이터 접근 인터페이스
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
public interface MemberDAO {

    /**
     * 입력받은 값으로 로그인을 시도한다.
     *
     * @param LoginDTO - 사용자에게 입력받은 ID와 PW
     * @return MemberVO - 로그인을 시도한 사용자 정보
     */
	public MemberVO loginMember(LoginDTO dto);
	
    /**
     * 입력받은 이메일로 로그인을 시도한다. (Facebook OAuth)
     *
     * @param mbEmail - 사용자에게 입력받은 이메일
     * @return MemberVO - 로그인을 시도한 사용자 정보
     */
	public MemberVO loginMember(String mbEmail);
	
    /**
     * 회원가입을 할 때, 아이디가 중복되는지 확인한다.
     *
     * @param mbId - 중복확인 ID
     * @return 아이디 중복 여부 반환
     */
	public int checkId(String mbId);
	
    /**
     * 회원가입 후, 이메일 인증이 완료되면 인증 상태를 업데이트한다.
     *
     * @param mbId - 회원가입 ID
     * @return
     */
	public void updateAuth(String mbId);
	
    /**
     * 회원 정보를 수정한다.
     *
     * @param MemberVO - 입력받은 회원 정보
     * @return
     */
	public void updateMember(MemberVO vo);
	
    /**
     * 아이디에 해당하는 회원 정보를 반환한다.
     *
     * @param mbId - 회원 ID
     * @return MemberVO - 회원 정보
     */
	public MemberVO getMember(String mbId);
	
    /**
     * 회원 가입을 한다.
     *
     * @param MemberVO - 회원가입 정보
     * @return
     */
	public void insertMember(MemberVO vo);
	
    /**
     * 회원가입 후, 이메일 인증이 완료되면 인증 상태를 업데이트한다.
     *
     * @param mbId - 회원가입 ID
     * @return
     */
	public void updateConDate(LoginDTO dto);

    /**
     * 검색된 모든 회원 정보를 가져온다.
     *
     * @param SearchCriteria - 검색 정보
     * @return List<MemberVO> - 검색된 회원 정보 리스트
     */
	public List<MemberVO> getMemberList(SearchCriteria cri);
	
    /**
     * 모든 회원 정보를 가져온다.
     *
     * @param
     * @return List<MemberVO> - 모든 회원 정보 리스트
     */
	public List<MemberVO> getAllMembers();
	
    /**
     * 검색된 회원의 총 수를 반환한다.
     *
     * @param SearchCriteria - 검색 정보
     * @return 검색된 회원의 총 수
     */
	public int countMemberList(SearchCriteria cri);
	
    /**
     * 회원 탈퇴를 한다.
     *
     * @param mbId - 탈퇴할 회원 ID
     * @return
     */
	public void deleteMember(String mbId);
	
}
