package com.demo.view.common;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class SessionListener implements HttpSessionBindingListener {

	private static Hashtable<HttpSession, String> loginUsers = new Hashtable<HttpSession, String>();
	
	private static SessionListener sessionListener = null;
	
    public static synchronized SessionListener getInstance() {
        if(sessionListener == null) {
            sessionListener = new SessionListener();
        }
        return sessionListener;
    }
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
        loginUsers.put(event.getSession(), event.getName());
        System.out.println(event.getName() + " 로그인 완료");
        System.out.println("현재 접속자 수 : " +  getUserCount());
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
        loginUsers.remove(event.getSession());
        System.out.println(event.getName() + " 로그아웃 완료");
        System.out.println("현재 접속자 수 : " +  getUserCount());
	}

	// 접속한 아이디를 해시테이블 컬렉션에서 삭제
    public void removeSession(String userId) {
        Enumeration<HttpSession> e = loginUsers.keys();
        HttpSession session = null;
        while(e.hasMoreElements()){
            session = e.nextElement();
            if(loginUsers.get(session).equals(userId)){
                //세션이 invalidate될때 HttpSessionBindingListener를 
                //구현하는 클레스의 valueUnbound()함수가 호출된다.
                session.invalidate();
            }
       }
    }

    /*
        아이디 중복로그인 방지목적으로 체크
    */
   public boolean isUsing(String userId){
       return loginUsers.containsValue(userId);
   }
    
   
   /*
        로그인을 확인 후 아이디를 세션형태로 저장
    */
   public void setSession(HttpSession session, String userId){
       //이순간에 Session Binding이벤트가 일어나는 시점
       //name값으로 userId, value값으로 자기자신(HttpSessionBindingListener를 구현하는 Object)
       session.setAttribute(userId, this);//login에 자기자신을 집어넣는다.
   }
   
   /*
     * 입력받은 세션Object로 아이디를 리턴한다.
     * @param session : 접속한 사용자의 session Object
     * @return String : 접속자 아이디
    */
   public String getUserID(HttpSession session){
       return (String)loginUsers.get(session);
   }
    
    
   // 현재 접속자수 확인
   public int getUserCount(){
       return loginUsers.size();
   }
    
   // 현재 접속중인 모든 사용자 아이디를 출력
   public void printloginUsers(){
       Enumeration<HttpSession> e = loginUsers.keys();
       HttpSession session = null;
       System.out.println("===========================================");
       int i = 0;
       while(e.hasMoreElements()){
           session = e.nextElement();
           System.out.println((++i) + ". 접속자 : " +  loginUsers.get(session));
       }
       System.out.println("===========================================");
    }
    
   // 현재 접속중인 모든 사용자리스트를 리턴
   public Collection<String> getUsers(){
       Collection<String> collection = loginUsers.values();
       return collection;
   }
     
}
