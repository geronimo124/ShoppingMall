package com.demo.view.common;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName : CommonExceptionHandler.java
 * @Description : 모든 에러를 제어하는 핸들러 클래스
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
@ControllerAdvice("com.demo.view")
public class CommonExceptionHandler {

    /**
     * SQL 에러 화면 페이지.
     *
     * @param Exception 에러 내용
     * @return ModelAndView
     */
	@ExceptionHandler(SQLException.class)
	public ModelAndView handlerSQLException(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/sqlError");
		return mav;
	}

    /**
     * 널 포인터 에러 화면 페이지.
     *
     * @param Exception 에러 내용
     * @return ModelAndView
     */
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handlerNullPointerException(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("common/nullPointerError");
		return mav;
	}
	
    /**
     * 모든 에러 화면 페이지.
     *
     * @param Exception 에러 내용
     * @return ModelAndView
     */
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("common/error");
		return mav;
	}
}
