package com.demo.view.admin;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.biz.admin.AdminService;
import com.demo.biz.admin.AdminVO;
import com.demo.biz.common.LoginDTO;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	private final AdminService service;
	
	@Autowired
	public AdminController(AdminService service) {
		this.service = service;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homeAdmin(HttpSession session) {
		
		if(session.getAttribute("admin") == null)
			return "admin/login";
		else
			return "admin/home";
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginAdmin(HttpSession session) {
		
		logger.info("Admin login.jsp");
		
		if(session.getAttribute("admin") == null)
			return "admin/login";
		else
			return "admin/home";
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginAdmin(HttpSession session, RedirectAttributes rttr, LoginDTO dto) {
		
		logger.info(dto.toString());
		
		AdminVO vo = service.loginAdmin(dto);
		
		if(vo == null) {
			rttr.addFlashAttribute("msg", "FAIL");
			return "redirect:login";
		}

		session.setAttribute("admin", vo);
		session.setMaxInactiveInterval(60 * 60 * 24);
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:home";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logoutAdmin(HttpSession session) {
		
		AdminVO vo = (AdminVO) session.getAttribute("admin");
		
		logger.info(vo.getAdmNm() + " Logout");
		
		session.invalidate();
		
		return "redirect:login";
	}
	
}
