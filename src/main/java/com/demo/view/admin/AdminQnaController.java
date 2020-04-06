package com.demo.view.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.biz.common.PageMaker;
import com.demo.biz.common.SearchCriteria;
import com.demo.biz.product.QnaService;
import com.demo.biz.product.QnaVO;

@Controller
@RequestMapping("/admin/product/qna")
public class AdminQnaController {

	private static final Logger logger = LoggerFactory.getLogger(AdminQnaController.class);

	private final QnaService service;

	@Autowired
	public AdminQnaController(QnaService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public void qnaList(@ModelAttribute("cri") SearchCriteria cri, Model model) {
		
		logger.info(cri.toString());
		
		model.addAttribute("qnaList", service.getQnaList(cri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		pageMaker.setTotalCount(service.countQnaList(cri));

		model.addAttribute("pageMaker", pageMaker);
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<String> insertQna(@RequestBody QnaVO vo) {

		logger.info(vo.toString());
		
		ResponseEntity<String> entity = null;
		
		try {
			service.insertQna(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
