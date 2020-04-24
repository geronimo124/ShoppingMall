package com.demo.biz.admin;

import com.demo.biz.common.LoginDTO;

/**
 * @ClassName : AdminService.java
 * @Description : 관리자 정보의 관리를 위한 서비스 인터페이스
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
public interface AdminService {

    /**
     * 입력받은 값을 이용, 비밀번호를 암호화하여 로그인을 시도한다.
     *
     * @param LoginDTO 사용자에게 입력받은 ID와 PW
     * @return AdminVO - 로그인을 시도한 관리자 정보
     */
	public AdminVO loginAdmin(LoginDTO dto);
	
}
