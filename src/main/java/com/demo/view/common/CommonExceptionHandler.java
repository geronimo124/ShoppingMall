package com.demo.view.common;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("com.demo.view")
public class CommonExceptionHandler {

	@ExceptionHandler(SQLException.class)
	public ModelAndView handlerSQLException(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/sqlError");
		return mav;
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handlerNullPointerException(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("common/nullPointerError");
		return mav;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("common/error");
		return mav;
	}
}
