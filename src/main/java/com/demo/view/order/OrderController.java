package com.demo.view.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.ResponseBody;
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

		List<BasketVO> basketList = service.getBaskets(map);
		
		// 상품 상세보기에서 즉시구매를 클릭하였을 경우
		if(basketList.size() == 0) {
			BasketVO product = service.getProduct(productList.get(0));
			product.setBskQty(Integer.parseInt(request.getParameter("bskQty")));
			basketList.add(product);
		}
		
		model.addAttribute("basketList", basketList);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertOrder(HttpServletRequest request, OrderVO vo, RedirectAttributes rttr) {
		
		logger.info(vo.toString());

		String[] arrayParam = request.getParameterValues("pdNo");
		Integer mile = Integer.parseInt(request.getParameter("mile"));
		MemberVO member = (MemberVO) request.getSession().getAttribute("member");
		
		List<Integer> productList = new ArrayList<>();
		for(String tmp : arrayParam)
			productList.add(Integer.parseInt(tmp));
		
		service.insertOrder(vo, productList, mile, Integer.parseInt(request.getParameter("bskQty")));
		
		request.getSession().setAttribute("member", service.getMember(member.getMbId()));
		
		rttr.addFlashAttribute("orderMsg", "SUCCESS");
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listOrder(HttpSession session, Model model) {
		
		MemberVO vo = (MemberVO) session.getAttribute("member");
		
		model.addAttribute("orderList", service.getOrderList(vo.getMbId()));
		
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public void detailOrder(Integer ordNo, Model model) {
		
		model.addAttribute("orderList", service.getOrderDetail(ordNo));
		model.addAttribute("order", service.getOrder(ordNo));
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkStock", method = RequestMethod.POST)
	public ResponseEntity<String> checkStock(@RequestBody List<BasketVO> basketList) { 

		logger.info(basketList.toString());
		
		ResponseEntity<String> entity = null;
		
		try {
			if(service.checkStock(basketList))
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			else
				entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
