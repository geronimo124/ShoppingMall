package com.demo.view.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.biz.member.MessageService;

@Controller
@RequestMapping("/msg")
public class MessageController {

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	private final MessageService service;
	
	@Autowired
	public MessageController(MessageService service) {
		this.service = service;
	}
}
