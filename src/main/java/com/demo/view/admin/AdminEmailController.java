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

/**
 * @ClassName : AdminEmailController.java
 * @Description : 관리자 이메일관리 정보에 대한 컨트롤러 클래스
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
@Controller
@RequestMapping("/admin/email")
public class AdminEmailController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminEmailController.class);
	
	private final EmailService service;
	
	@Autowired
	public AdminEmailController(EmailService service) {
		this.service = service;
	}
	
    /**
     * 이메일 전송 페이지.
     *
     * @param
     * @return JSP
     */
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public void sendMail() {
		
		logger.info("Email send page");
		
	}
	
    /**
     * 관리자 계정으로 이메일을 전송한다.
     *
     * @param EmailDTO 이메일 정보
     * @return JSP
     */
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public String sendMail(EmailDTO dto, Model model) {
		
		logger.info(dto.toString());
		
		dto.setSenderMail("jeonilbae@naver.com");
		
		// 모든 사용자에게 메일을 전송할 경우
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
			
		} 
		
		// 특정 사용자에게 메일을 전송할 경우
		else {
			
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
