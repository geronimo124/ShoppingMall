package com.demo.biz.admin;

import com.demo.biz.common.LoginDTO;

public interface AdminDAO {

	public AdminVO loginAdmin(LoginDTO dto);
	public void updateConDate(LoginDTO dto);
}
