package com.demo.view.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.biz.common.EmailDTO;
import com.demo.biz.member.EmailService;
import com.demo.biz.member.MemberVO;

@Controller
@RequestMapping("/admin/email")
public class AdminEmailController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminEmailController.class);
	
	private final EmailService service;
	
	@Autowired
	public AdminEmailController(EmailService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public void sendMail() {
		
		logger.info("Email send page");
		
	}
	
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public String sendMail(EmailDTO dto, Model model) {
		
		logger.info(dto.toString());
		
		dto.setSenderMail("jeonilbae@naver.com");
		
		if(dto.getReceiveMail().equals("To: All Members")) {
		
			try {
				List<MemberVO> memberList = service.getAllMembers();
				
				for(MemberVO member : memberList) {
					dto.setReceiveMail(member.getMbEmail());
					service.sendMail(dto);
				}

				model.addAttribute("msg", "SUCCESS");
				
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", "FAIL");
			}
			
		} else {
			
			try {
				MemberVO member = service.getMember(dto.getReceiveMail());
				
				dto.setReceiveMail(member.getMbEmail());
				service.sendMail(dto);

				model.addAttribute("msg", "SUCCESS");
				
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", "FAIL");
			}	
		}
		
		return "/admin/email/send";
	}

}
