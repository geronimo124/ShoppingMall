package com.demo.view.common;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * @ClassName : SessionListener.java
 * @Description : 사용자들의 세션 정보를 확인 및 제어하는 리스너 클래스
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
public class SessionListener implements HttpSessionBindingListener {

	/**
	 * 세션에 등록된 회원 정보 목록
	 */
	private static Hashtable<HttpSession, String> loginUsers = new Hashtable<HttpSession, String>();
	
	private static SessionListener sessionListener = null;
	
    public static synchronized SessionListener getInstance() {
        if(sessionListener == null) {
            sessionListener = new SessionListener();
        }
        return sessionListener;
    }
	
    /**
     * 세션에 정보가 바인딩되면 회원 목록에 추가한다.
     *
     * @param
     * @return
     */
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		
        loginUsers.put(event.getSession(), event.getName());
        
        System.out.println(event.getName() + " 로그인 완료");
        System.out.println("현재 접속자 수 : " +  getUserCount());
        
	}

    /**
     * 세션에 정보가 언바인딩되면 회원 목록에서 삭제한다.
     *
     * @param
     * @return
     */
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		
        loginUsers.remove(event.getSession());
        
        System.out.println(event.getName() + " 로그아웃 완료");
        System.out.println("현재 접속자 수 : " +  getUserCount());
        
	}

    /**
     * 접속한 특정 아이디의 접속을 끊는다.
     *
     * @param userId 사용자 ID
     * @return
     */
    public void removeSession(String userId) {
    	
        Enumeration<HttpSession> e = loginUsers.keys();
        HttpSession session = null;
        
        while(e.hasMoreElements()){
        	
            session = e.nextElement();
            
            if(loginUsers.get(session).equals(userId))
                session.invalidate();
       }
    }

    /**
     * 중복 로그인 여부를 확인한다.
     *
     * @param userId 사용자 ID
     * @return 중복 로그인 여부
     */
   public boolean isUsing(String userId){
       return loginUsers.containsValue(userId);
   }
    
   
   /**
    * 접속한 사용자를 세션에 등록한다.
    *
    * @param userId 사용자 ID
    * @return
    */
   public void setSession(HttpSession session, String userId){
	   
       session.setAttribute(userId, this);
       
   }
   
   /**
    * 세션 객체를 통해 사용자 ID를 가져온다.
    *
    * @param
    * @return 사용자 ID
    */
   public String getUserID(HttpSession session){
	   
       return (String)loginUsers.get(session);
       
   }
    
   /**
    * 현재 접속자 수를 반환한다.
    *
    * @param
    * @return 접속자 수
    */
   public int getUserCount(){
	   
       return loginUsers.size();
       
   }
    
   /**
    * 현재 접속중인 모든 사용자 ID를 출력한다.
    *
    * @param
    * @return
    */
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
    
   /**
    * 현재 접속중인 모든 사용자 ID를 반환한다.
    *
    * @param
    * @return Collection - 접속 사용자 목록
    */
   public Collection<String> getUsers(){
	   
       Collection<String> collection = loginUsers.values();
       
       return collection;
       
   }
     
}
