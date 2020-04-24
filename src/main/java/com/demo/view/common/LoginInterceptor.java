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

/**
 * @ClassName : LoginInterceptor.java
 * @Description : 사용자 로그인 후 인증 정보와 세션을 제어하는 인터셉터 클래스
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
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
    /**
     * 로그인 후 인증 여부에 따라 인증메일 송신 또는 세션에 등록한다.
     *
     * @param
     * @return ModelAndView
     * @throws Exception
     */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView mav)
			throws Exception {
		
		HttpSession session = request.getSession();

		ModelMap modelMap = mav.getModelMap();
		MemberVO memberVO = (MemberVO) modelMap.get("member");
		SessionListener listener = SessionListener.getInstance();
		
		// 로그인이 되었다면
		if (memberVO != null) {

			logger.info("new login success");

			// 회원 인증이 되어있지 않다면 메일 송신
			if(memberVO.getMbAuth().equals("N")) {
				response.sendRedirect("/member/authkey?id=" + memberVO.getMbId());
				
			} 
			
			// 인증이 되었다면 세션 등록 후 페이지 이동
			else {
			
				session.setAttribute("member", memberVO);
				listener.setSession(session, memberVO.getMbId());
				Object dest = session.getAttribute("dest");
	
				response.sendRedirect(dest != null ? (String) dest : "/");
			}
		}
	}
}
