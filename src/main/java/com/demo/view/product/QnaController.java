package com.demo.view.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.biz.common.Criteria;
import com.demo.biz.common.PageMaker;
import com.demo.biz.product.QnaService;
import com.demo.biz.product.QnaVO;

@Controller
@RequestMapping("/qna")
public class QnaController {

	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);

	private final QnaService service;
	
	@Autowired
	public QnaController(QnaService service) {
		this.service = service;
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
	
	@ResponseBody
	@RequestMapping(value = "/{pdNo}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listQna(
			@PathVariable("pdNo") Integer pdNo, @PathVariable("page") Integer page) {

		logger.info(pdNo.toString() + page.toString());
		
		ResponseEntity<Map<String, Object>> entity = null;

		try {
			Criteria cri = new Criteria();
			cri.setPage(page);

			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);

			Map<String, Object> map = new HashMap<>();
			List<QnaVO> list = service.listQna(pdNo, cri);

			map.put("list", list);

			int qnaCount = service.countQnaList(pdNo);
			pageMaker.setTotalCount(qnaCount);

			map.put("pageMaker", pageMaker);

			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);

		}
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteQna(@RequestBody QnaVO vo) {
		
		ResponseEntity<String> entity = null;
		
		logger.info(vo.toString());
		
		try {
			if(service.deleteQnaCheck(vo))
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			else
				entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/modify", method = RequestMethod.PUT)
	public ResponseEntity<String> modifyQna(@RequestBody QnaVO vo) {
		
		ResponseEntity<String> entity = null;
		
		logger.info(vo.toString());
		
		try {
			service.modifyQna(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
	}
}
