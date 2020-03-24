package com.demo.view.order;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.biz.order.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	private final OrderService service;
	
	@Autowired
	public OrderController(OrderService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insertOrder(HttpServletRequest request) {

		String[] arrayParam = request.getParameterValues("pdNo");
		for(String a : arrayParam)
			System.out.println(a);
	}
}
