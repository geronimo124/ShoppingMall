package com.demo.view.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.biz.member.MemberVO;
import com.demo.biz.order.OrderService;
import com.demo.biz.order.OrderVO;
import com.demo.biz.product.BasketVO;

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
	public void insertOrder(HttpServletRequest request, Model model) {

		String[] arrayParam = request.getParameterValues("pdNo");
		MemberVO vo = (MemberVO) request.getSession().getAttribute("member");
		
		List<Integer> productList = new ArrayList<>();
		for(String tmp : arrayParam)
			productList.add(Integer.parseInt(tmp));
		
		Map<String, Object> map = new HashMap<>();
		map.put("mbId", vo.getMbId());
		map.put("productList", productList);

		model.addAttribute("basketList", service.getBaskets(map));
		
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertOrder(HttpServletRequest request, OrderVO vo, RedirectAttributes rttr) {
		
		logger.info(vo.toString());
		System.out.println(vo.toString());

		String[] arrayParam = request.getParameterValues("pdNo");
		Integer mile = Integer.parseInt(request.getParameter("mile"));
		
		List<Integer> productList = new ArrayList<>();
		for(String tmp : arrayParam)
			productList.add(Integer.parseInt(tmp));
		
		System.out.println(productList);
		System.out.println(mile);
		
		service.insertOrder(vo, productList, mile);
		
		rttr.addFlashAttribute("orderMsg", "SUCCESS");
		
		return "redirect:/";
	}
}
