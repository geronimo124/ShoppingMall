package com.demo.biz.admin.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.biz.admin.AdminDAO;
import com.demo.biz.admin.AdminVO;
import com.demo.biz.common.LoginDTO;

/**
 * @ClassName : AdminDAOImpl.java
 * @Description : 관리자 정보의 관리를 위한 데이터 접근 클래스
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
public class AdminDAOImpl implements AdminDAO {

	private final static String NAMESPACE = "com.demo.mapper.AdminMapper";

	private final SqlSession session;
	
	@Autowired
	public AdminDAOImpl(SqlSession session) {
		this.session = session;
	}

    /**
     * 입력받은 값으로 로그인을 시도한다.
     *
     * @param LoginDTO - 사용자에게 입력받은 ID와 PW
     * @return AdminVO - 로그인을 시도한 관리자 정보
     */
	@Override
	public AdminVO loginAdmin(LoginDTO dto) {
		return session.selectOne(NAMESPACE + ".login", dto);
	}

    /**
     * 접속 일자를 업데이트한다.
     *
     * @param LoginDTO - 사용자에게 입력받은 ID와 PW
     * @return
     */
	@Override
	public void updateConDate(LoginDTO dto) {
		session.update(NAMESPACE + ".updateConDate", dto);
	}

}
