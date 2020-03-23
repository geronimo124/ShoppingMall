package com.demo.view.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.demo.biz.product.ProductService;

@ControllerAdvice("com.demo.view")
public class CommonModelHandler {

	private final ProductService service;
	
	@Autowired
	public CommonModelHandler(ProductService service) {
		this.service = service;
	}
	
	@ModelAttribute
	public void categoryList(Model model) throws Exception {
		model.addAttribute("mainCateList", service.getCategoryList(1)); // left - 상위 카테고리 목록 출력
	}
}
