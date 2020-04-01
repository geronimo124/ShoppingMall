package com.demo.view.admin;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.biz.admin.AdminVO;
import com.demo.biz.stat.StatService;

@RestController
@RequestMapping("/admin/stat")
public class AdminStatController {

	private static final Logger logger = LoggerFactory.getLogger(AdminStatController.class);

	private final StatService service;

	@Autowired
	public AdminStatController(StatService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "/getCountNewOrders/{admId}", method = RequestMethod.GET)
	public ResponseEntity<Integer> getCountNewOrders(@PathVariable("admId") String admId) {
		
		ResponseEntity<Integer> entity = null;
		
		try {
			int count = service.getCountNewOrders(admId);
			entity = new ResponseEntity<Integer>(count, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
	
		return entity;
	}
	
	@RequestMapping(value = "/getCountNewMembers/{admId}", method = RequestMethod.GET)
	public ResponseEntity<Integer> getCountNewMembers(@PathVariable("admId") String admId) {
		
		ResponseEntity<Integer> entity = null;
		
		try {
			int count = service.getCountNewMembers(admId);
			entity = new ResponseEntity<Integer>(count, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}