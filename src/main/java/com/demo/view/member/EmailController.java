package com.demo.view.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.biz.common.EmailDTO;
import com.demo.biz.member.EmailService;

@Controller
@RequestMapping("/email")
public class EmailController {

	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);
	
	private final EmailService service;
	
	@Autowired
	public EmailController(EmailService service) {
		this.service = service;
	}
	
	@RequestMapping("write.do")
	public String write() {
		return "email/write";
	}
	
	@RequestMapping("send.do")
	public String send(@ModelAttribute EmailDTO dto, Model model) {
		
		logger.info(dto.toString());
		
		try {
			
			service.sendMail(dto);
			model.addAttribute("message", "이메일이 발송되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "이메일 발송 실패...");
		}
		
		return "email/write";
	}
}