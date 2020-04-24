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
import com.demo.view.common.SessionListener;

/**
 * @ClassName : AdminController.java
 * @Description : 관리자 정보에 대한 컨트롤러 클래스
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
@RequestMapping("/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	private final AdminService service;
	
	@Autowired
	public AdminController(AdminService service) {
		this.service = service;
	}

    /**
     * 관리자 홈페이지. 로그인이 되어있지 않으면 로그인 페이지로 이동한다.
     *
     * @param
     * @return JSP
     */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homeAdmin(HttpSession session) {
		
		if(session.getAttribute("admin") == null)
			return "admin/login";
		else
			return "admin/home";
		
	}

    /**
     * 로그인페이지. 로그인이 되어있다면 관리자 홈페이지로 이동한다.
     *
     * @param
     * @return JSP
     */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginAdmin(HttpSession session) {
		
		logger.info("Admin login.jsp");
		
		if(session.getAttribute("admin") == null)
			return "admin/login";
		else
			return "admin/home";
		
	}
	
    /**
     * 관리자 계정으로 로그인한다.
     *
     * @param LoginDTO 입력 ID, PW
     * @return redirect URL
     */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginAdmin(HttpSession session, RedirectAttributes rttr, LoginDTO dto) {
		
		logger.info(dto.toString());
		
		AdminVO vo = service.loginAdmin(dto);
		
		SessionListener listener = SessionListener.getInstance();
		
		// ID 또는 PW가 일치하지 않는 경우
		if(vo == null) {
			rttr.addFlashAttribute("msg", "FAIL");
			return "redirect:login";
		}
		
		// 이미 로그인 되어있는 경우
		if(listener.isUsing(dto.getId())) {
			rttr.addFlashAttribute("msg", "DUPLICATE");
			return "redirect:login";
		}

		// 세션에 계정 정보를 등록하고 홈페이지로 이동
		listener.setSession(session, dto.getId());
		session.setAttribute("admin", vo);
		session.setMaxInactiveInterval(60 * 60 * 24);
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:home";
	}

    /**
     * 관리자 계정을 로그아웃 한다.
     *
     * @param
     * @return redirect URL
     */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logoutAdmin(HttpSession session) {
		
		AdminVO vo = (AdminVO) session.getAttribute("admin");
		
		logger.info(vo.getAdmNm() + " Logout");
		
		session.invalidate();
		
		return "redirect:login";
	}
	
}
