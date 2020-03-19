package com.demo.biz.member;

import com.demo.biz.common.EmailDTO;

public interface EmailService {

	public void sendMail(EmailDTO dto);
}