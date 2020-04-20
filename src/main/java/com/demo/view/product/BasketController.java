package com.demo.view.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.biz.member.MemberVO;
import com.demo.biz.product.BasketService;
import com.demo.biz.product.BasketVO;

@Controller
@RequestMapping("/basket")
public class BasketController {

	private static final Logger logger = LoggerFactory.getLogger(BasketController.class);

	private final BasketService service;
	
	@Autowired
	public BasketController(BasketService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void basket(HttpSession session, Model model) {
		
		MemberVO vo = (MemberVO) session.getAttribute("member");

		logger.info(vo.getMbId() + "'s basket list");
		
		model.addAttribute("basketList", service.getBaskets(vo.getMbId()));
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<String> insertBasket(HttpSession session, @RequestBody BasketVO vo) {
		
		ResponseEntity<String> entity = null;
		
		logger.info(vo.toString());
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		
		vo.setMbId(member.getMbId());
		
		try {
			if(service.insertBasket(vo))
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			else
				entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete/{pdNo}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBasket(HttpSession session, @PathVariable Integer pdNo) {
		
		ResponseEntity<String> entity = null;
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		BasketVO vo = new BasketVO();
		
		vo.setMbId(member.getMbId());
		vo.setPdNo(pdNo);
		
		logger.info(vo.toString());
		
		try {
			service.deleteBasket(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ResponseEntity<String> modifyBasket(HttpSession session, BasketVO vo) {
		
		ResponseEntity<String> entity = null;
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		
		vo.setMbId(member.getMbId());
		
		logger.info(vo.toString());
		
		try {
			service.updateBasket(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteChecked", method = RequestMethod.POST)
	public ResponseEntity<String> deleteCheckedBasket(HttpSession session, @RequestParam("basketList[]") List<Integer> basketList) {
		
		ResponseEntity<String> entity = null;
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("mbId", member.getMbId());
		map.put("basketList", basketList);
		
		try {
			service.deleteBaskets(map);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value = "/modifyChecked", method = RequestMethod.POST)
	public ResponseEntity<String> modifyCheckedBasket(HttpSession session, @RequestBody List<BasketVO> basketList) {
		
		ResponseEntity<String> entity = null;
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		
		for(BasketVO vo : basketList)
			vo.setMbId(member.getMbId());
		
		try {
			service.updateBaskets(basketList);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
