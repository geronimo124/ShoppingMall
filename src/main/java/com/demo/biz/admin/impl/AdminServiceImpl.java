package com.demo.biz.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.biz.admin.AdminDAO;
import com.demo.biz.admin.AdminService;
import com.demo.biz.admin.AdminVO;
import com.demo.biz.common.LoginDTO;

@Service
public class AdminServiceImpl implements AdminService {

	private final AdminDAO dao;
	
	private final BCryptPasswordEncoder crptPassEnc;
	
	@Autowired
	public AdminServiceImpl(AdminDAO dao, BCryptPasswordEncoder crptPassEnc) {
		this.dao = dao;
		this.crptPassEnc = crptPassEnc;
	}

	@Override
	public AdminVO loginAdmin(LoginDTO dto) {
		// TODO Auto-generated method stub
		
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
