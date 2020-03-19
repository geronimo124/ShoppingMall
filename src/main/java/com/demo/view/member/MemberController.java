package com.demo.view.member;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.biz.admin.AdminVO;
import com.demo.biz.common.LoginDTO;
import com.demo.biz.member.MemberService;
import com.demo.biz.member.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private final MemberService service;
	
	@Autowired
	public MemberController(MemberService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpSession session) {
		
		logger.info("login page");
		
		if(session.getAttribute("member") == null)
			return "member/login";
		else
			return "/home";
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, RedirectAttributes rttr, LoginDTO dto) {
		
		logger.info(dto.toString());
		
		MemberVO vo = service.loginMember(dto);
		
		if(vo == null) {
			rttr.addFlashAttribute("msg", "FAIL");
			return "redirect:login";
		}
		
		session.setAttribute("member", vo);
		session.setMaxInactiveInterval(60 * 60 * 24);
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:home";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logoutMember(HttpSession session) {
		
		MemberVO vo = (MemberVO) session.getAttribute("member");
		
		logger.info(vo.getMbNick() + " Logout");
		
		session.invalidate();
		
		return "redirect:login";
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void signup() {
	
		logger.info("register");
	
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String signup(MemberVO vo, HttpSession session) {
		
		logger.info(vo.toString());
		
		System.out.println(vo.toString());
		// 디비에 일단 넣고(auth = 'N')
		// authkey 생성 후 이메일로 보내기
		session.setAttribute("temp", vo);
		
		return "redirect:authkey";
	}
	
	@RequestMapping(value = "/authkey", method = RequestMethod.GET)
	public String authkey(HttpSession session) {
		
		logger.info("authkey page");

		if(session.getAttribute("temp") == null)
			return "/home";
		else {
			System.out.println(session.getAttribute("temp").toString());
			return "member/authkey";
		}
	}
}
