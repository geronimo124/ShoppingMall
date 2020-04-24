package com.demo.view.common;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @ClassName : CountManager.java
 * @Description : 페이지에 연결된 사용자의 수를 알려주는 리스너 클래스
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
public class CountManager implements HttpSessionListener {

	private static int count;
	
	public static int getCount() {
		return count;
	}
	
    /**
     * 새로운 세션이 연결되었을 경우 카운트를 증가한다.
     *
     * @param
     * @return
     */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		
        HttpSession session = se.getSession();
        session.setMaxInactiveInterval(60 * 20);
         
        count++;
         
        session.getServletContext().log(session.getId() + " 세션생성 " + ", 접속자수 : " + count);
	}

    /**
     * 세션이 끊어졌을 경우 카운트를 감소한다.
     *
     * @param
     * @return
     */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
        if(--count < 0)
            count = 0;
         
        HttpSession session = se.getSession();
        
        session.getServletContext().log(session.getId() + " 세션소멸 " + ", 접속자수 : " + count);
	}

}
