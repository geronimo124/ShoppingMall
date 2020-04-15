package com.demo.view.member;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.biz.common.PageMaker;
import com.demo.biz.common.SearchCriteria;
import com.demo.biz.product.CategoryVO;
import com.demo.biz.product.MProductService;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private final MProductService service;
	
	@Autowired
	public HomeController(MProductService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(@ModelAttribute("cri") SearchCriteria cri, Model model) {
		
		logger.info(cri.toString());
		
		model.addAttribute("productList", service.getAllProductList(cri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		pageMaker.setTotalCount(service.countAllProductList(cri));

		model.addAttribute("pageMaker", pageMaker);
		
		return "home";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getSubCateList/{ctgyParent}", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryVO>> getSubCateList(@PathVariable("ctgyParent") Integer ctgyParent) {

		ResponseEntity<List<CategoryVO>> entity = null;

		try {
			entity = new ResponseEntity<List<CategoryVO>>(service.getCategoryList(ctgyParent), HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<List<CategoryVO>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
