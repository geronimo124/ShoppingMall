package com.demo.view.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.biz.common.Criteria;
import com.demo.biz.common.PageMaker;
import com.demo.biz.order.ReviewService;
import com.demo.biz.order.ReviewVO;

@RestController
@RequestMapping("/review")
public class ReviewController {

	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

	private final ReviewService service;

	@Autowired
	public ReviewController(ReviewService service) {
		this.service = service;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> insertReview(@RequestBody ReviewVO vo) {

		ResponseEntity<String> entity = null;

		logger.info(vo.toString());

		try {

			service.insertReview(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		return entity;

	}

	@RequestMapping(value = "/{pdNo}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listReview(
			@PathVariable("pdNo") Integer pdNo, @PathVariable("page") Integer page) {

		logger.info(pdNo.toString() + page.toString());
		
		ResponseEntity<Map<String, Object>> entity = null;

		try {
			Criteria cri = new Criteria();
			cri.setPage(page);

			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);

			Map<String, Object> map = new HashMap<>();
			List<ReviewVO> list = service.listReview(pdNo, cri);

			map.put("list", list);

			int reviewCount = service.countReviewList(pdNo);
			pageMaker.setTotalCount(reviewCount);

			map.put("pageMaker", pageMaker);

			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);

		}
		return entity;
	}

	@RequestMapping(value = "/{revNo}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteReview(@PathVariable("revNo") Integer revNo) {

		logger.info(revNo.toString());
		
		ResponseEntity<String> entity = null;

		try {

			service.deleteReview(revNo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

	@RequestMapping(value = "/{revNo}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> updateReview(@PathVariable("revNo") Integer revNo, @RequestBody ReviewVO vo) {

		logger.info(vo.toString());
		
		ResponseEntity<String> entity = null;
		
		try {
			vo.setRevNo(revNo);
			service.modifyReview(vo);

			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value = "/get/{ordNo}/{pdNo}", method = RequestMethod.GET)
	public ResponseEntity<ReviewVO> getReview(@PathVariable("ordNo") Integer ordNo, @PathVariable("pdNo") Integer pdNo) {
		
		logger.info(ordNo.toString() + pdNo.toString());
		
		ResponseEntity<ReviewVO> entity = null;
		
		try {
			ReviewVO vo = service.getReview(ordNo, pdNo);

			if(vo != null) 
				entity = new ResponseEntity<ReviewVO>(service.getReview(ordNo, pdNo), HttpStatus.OK);
			else
				entity = new ResponseEntity<ReviewVO>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<ReviewVO>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}

}
