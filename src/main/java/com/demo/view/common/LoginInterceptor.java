package com.demo.view.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.demo.biz.member.MemberVO;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView mav)
			throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		ModelMap modelMap = mav.getModelMap();
		MemberVO memberVO = (MemberVO) modelMap.get("member");
		
		if (memberVO != null) {

			logger.info("new login success");

			if(memberVO.getMbAuth().equals("N")) {
				response.sendRedirect("/member/authkey?id=" + memberVO.getMbId());
				
			} else {
			
				session.setAttribute("member", memberVO);
				Object dest = session.getAttribute("dest");
	
				response.sendRedirect(dest != null ? (String) dest : "/");
			}
		}
	}
}
