package com.demo.view.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.biz.member.MessageService;
import com.demo.biz.member.MessageVO;

@RestController
@RequestMapping("/msg")
public class MessageController {

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	private final MessageService service;
	
	@Autowired
	public MessageController(MessageService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public ResponseEntity<String> sendMsg(@RequestBody MessageVO vo) {
	
		logger.info(vo.toString());
		
		ResponseEntity<String> entity = null;
		
		try {
			if(service.sendMsg(vo))
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
