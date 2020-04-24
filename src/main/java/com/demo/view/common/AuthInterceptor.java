package com.demo.view.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @ClassName : AuthInterceptor.java
 * @Description : 홈페이지 이용 시 접근권한을 제어하는 인터셉터 클래스
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
public class AuthInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    /**
     * 로그인되지 않았다면 현재 페이지 저장 후 로그인 페이지로 이동시킨다.
     *
     * @param
     * @return 인증 성공 여부
     * @throws Exception
     */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();  

		// 로그인이 되지 않았을 경우
		if(session.getAttribute("member") == null){

			logger.info("current user is not logined");
			
			// Ajax 방식으로 요청이 들어올 경우
			if("XMLHttpRequest".equals(request.getHeader("x-requested-with")))
				response.sendError(500);

			saveDest(request);

			response.sendRedirect("/member/login");

			return false;
		}
		
		return true;
	}

    /**
     * 현재 사용자의 페이지 경로를 저장한다.
     *
     * @param
     * @return
     */
	private void saveDest(HttpServletRequest req) {

		String uri = req.getRequestURI();

		String query = req.getQueryString();

		if (query == null || query.equals("null"))
			query = "";
		else
			query = "?" + query;

		if (req.getMethod().equals("GET")) {
			
			logger.info("dest: " + (uri + query));
			
			req.getSession().setAttribute("dest", uri + query);
			
		}
	}

}
