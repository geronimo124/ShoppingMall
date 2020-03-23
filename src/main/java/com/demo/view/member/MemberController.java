package com.demo.view.member;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public void login(LoginDTO dto, Model model) {
		
		logger.info(dto.toString());
		
		MemberVO vo = service.loginMember(dto);

		if(vo == null) {
			return;
		}

		model.addAttribute("member", vo);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logoutMember(HttpSession session) {
		
		MemberVO vo = (MemberVO) session.getAttribute("member");
		
		logger.info(vo.getMbNick() + " Logout");
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void signup() {
	
		logger.info("register");
	
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String signup(MemberVO vo, HttpSession session) {
		
		logger.info(vo.toString());
		
		System.out.println(vo.toString());
		
		service.insertMember(vo);
		
		session.setAttribute("temp", vo.getMbId());
		
		return "redirect:authkey";
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkId/{mbId}", method = RequestMethod.GET)
	public ResponseEntity<String> checkId(@PathVariable("mbId") String mbId) {
		
		ResponseEntity<String> entity = null;
		
		try {
			
			if(service.checkId(mbId) > 0)
				entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
			else
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} catch (Exception e) {
			
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			
		}
		
		return entity;
	}
	
	@RequestMapping(value = "/authkey", method = RequestMethod.GET)
	public void authkey(String id, HttpSession session, Model model) {
		
		logger.info("authkey page");

		model.addAttribute("temp", id);
	}
	
	@RequestMapping(value = "/authkey", method = RequestMethod.POST)
	public String authkey(@RequestParam("mbAuth") String mbAuth, @RequestParam("mbId") String mbId, RedirectAttributes rttr, HttpSession session) {
		
		logger.info(mbAuth);

		MemberVO vo = service.getMember(mbId);
		
		if(mbAuth.equals(vo.getMbAuthkey())) {
			rttr.addFlashAttribute("msg", "SUCCESS");
			service.updateAuth(mbId);
			return "redirect:login";
		} else {
			rttr.addFlashAttribute("msg", "FAIL");
			return "redirect:authkey";
		}
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyMember() {
		
		logger.info("modify page");
		
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyMember(HttpSession session, MemberVO vo) {
		
		logger.info(vo.toString());
		
		service.updateMember(vo);
		session.setAttribute("member", vo);
		
		return "/home";
	}
	
	@RequestMapping(value = "/basket", method = RequestMethod.GET)
	public void basket(HttpSession session, Model model) {
		
		MemberVO vo = (MemberVO) session.getAttribute("member");
		
		model.addAttribute("basketList", service.getBaskets(vo.getMbId()));
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/insertBasket", method = RequestMethod.POST)
	public void insertBasket() {
		
	}
}
