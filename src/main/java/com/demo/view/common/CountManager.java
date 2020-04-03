package com.demo.view.common;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CountManager implements HttpSessionListener {

	private static int count;
	
	public static int getCount() {
		return count;
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
        HttpSession session = se.getSession(); //request에서 얻는 session과 동일한 객체
        session.setMaxInactiveInterval(60 * 20);
         
        count++;
         
        session.getServletContext().log(session.getId() + " 세션생성 " + ", 접속자수 : " + count);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
        if(--count < 0)
            count = 0;
         
        HttpSession session = se.getSession();
        session.getServletContext().log(session.getId() + " 세션소멸 " + ", 접속자수 : " + count);
	}

}
