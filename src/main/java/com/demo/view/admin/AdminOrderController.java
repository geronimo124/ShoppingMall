package com.demo.view.admin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.biz.order.OrderSearchCriteria;
import com.demo.biz.order.OrderService;
import com.demo.biz.order.OrderVO;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {

	private static final Logger logger = LoggerFactory.getLogger(AdminOrderController.class);
	
	private final OrderService service;
	
	@Autowired
	public AdminOrderController(OrderService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public void mainOrder() {
		
	}
	
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public void listOrder(@RequestParam Map<String, String> paramMap, Model model) {
		
		OrderSearchCriteria cri = new OrderSearchCriteria();
		
		cri.setSearchMap(paramMap);
		
		System.out.println(cri.toString());

		model.addAttribute("cri", cri);
		model.addAttribute("orderList", service.getAllOrderList(cri));
//		
//		PageMaker pageMaker = new PageMaker();
//		pageMaker.setCri(cri);
//
//		pageMaker.setTotalCount(service.countAllOrderList(cri));
//
//		model.addAttribute("pageMaker", pageMaker);
		
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public void detailOrder(Integer ordNo, Model model) {
		
		model.addAttribute("orderList", service.getOrderDetail(ordNo));
		model.addAttribute("order", service.getOrder(ordNo));
		
	}
	
	@ResponseBody
	@RequestMapping(value = "modifyChecked", method = RequestMethod.POST)
	public ResponseEntity<String> modifyCheckedOrder(@RequestBody List<OrderVO> orderList) {
		
		logger.info(orderList.toString());
		
		ResponseEntity<String> entity = null;
		
		try {
			service.modifyCheckedOrders(orderList);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value = "deleteChecked", method = RequestMethod.POST)
	public ResponseEntity<String> deleteCheckedOrder(HttpServletRequest request, @RequestParam("orderList[]") List<Integer> orderList) {
		
		ResponseEntity<String> entity = null;
		
		try {
			service.deleteOrders(orderList);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
