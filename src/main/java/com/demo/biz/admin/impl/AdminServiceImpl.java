package com.demo.biz.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.biz.admin.AdminDAO;
import com.demo.biz.admin.AdminService;
import com.demo.biz.admin.AdminVO;
import com.demo.biz.common.LoginDTO;

/**
 * @ClassName : AdminServiceImpl.java
 * @Description :관리자 정보의 관리를 위한 서비스 클래스
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
public class AdminServiceImpl implements AdminService {

	private final AdminDAO dao;
	
	private final BCryptPasswordEncoder crptPassEnc;
	
	@Autowired
	public AdminServiceImpl(AdminDAO dao, BCryptPasswordEncoder crptPassEnc) {
		this.dao = dao;
		this.crptPassEnc = crptPassEnc;
	}

    /**
     * 입력받은 값을 이용, 비밀번호를 암호화하여 로그인을 시도한다.
     *
     * @param LoginDTO - 사용자에게 입력받은 ID와 PW
     * @return AdminVO - 로그인을 시도한 관리자 정보
     */
	@Override
	public AdminVO loginAdmin(LoginDTO dto) {
		
		AdminVO vo = dao.loginAdmin(dto);
		
		if(vo != null) {
			
			if(crptPassEnc.matches(dto.getPassword(), vo.getAdmPw()))
				dao.updateConDate(dto);
			else
				vo = null;
			
		}

		return vo;
	}
	
}
