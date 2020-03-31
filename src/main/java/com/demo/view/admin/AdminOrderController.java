package com.demo.view.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.biz.common.PageMaker;
import com.demo.biz.common.SearchCriteria;
import com.demo.biz.order.OrderService;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {

	private static final Logger logger = LoggerFactory.getLogger(AdminOrderController.class);
	
	private final OrderService service;
	
	@Autowired
	public AdminOrderController(OrderService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listOrder(@ModelAttribute("cri") SearchCriteria cri, Model model) {
		
		logger.info(cri.toString());

		model.addAttribute("orderList", service.getAllOrderList(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		pageMaker.setTotalCount(service.countAllOrderList(cri));

		model.addAttribute("pageMaker", pageMaker);
		
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public void detailOrder(Integer ordNo, Model model) {
		
		model.addAttribute("orderList", service.getOrderDetail(ordNo));
		model.addAttribute("order", service.getOrder(ordNo));
		
	}
}
