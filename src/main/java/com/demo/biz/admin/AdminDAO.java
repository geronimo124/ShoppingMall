package com.demo.biz.admin;

import com.demo.biz.common.LoginDTO;

/**
 * @ClassName : AdminDAO.java
 * @Description : 관리자 정보의 관리를 위한 데이터 접근 인터페이스
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
public interface AdminDAO {

    /**
     * 입력받은 값으로 로그인을 시도한다.
     *
     * @param dto 사용자에게 입력받은 ID와 PW
     * @return AdminVO - 로그인을 시도한 관리자 정보
     */
	public AdminVO loginAdmin(LoginDTO dto);
	
    /**
     * 접속 일자를 업데이트한다.
     *
     * @param LoginDTO 사용자에게 입력받은 ID와 PW
     * @return
     */
	public void updateConDate(LoginDTO dto);
	
}
