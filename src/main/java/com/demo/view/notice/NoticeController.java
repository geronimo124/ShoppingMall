package com.demo.view.notice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.biz.common.PageMaker;
import com.demo.biz.common.SearchCriteria;
import com.demo.biz.notice.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	private final NoticeService service;
	
	@Autowired
	public NoticeController(NoticeService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listNotice(@ModelAttribute("cri") SearchCriteria cri, Model model) {
		
		logger.info(cri.toString());
		
		model.addAttribute("noticeList", service.getNoticeList(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		pageMaker.setTotalCount(service.countNoticeList(cri));
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "/notice/list";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void readNotice(@RequestParam("ntNo") Integer ntNo, @ModelAttribute("cri") SearchCriteria cri, Model model) {

		logger.info(ntNo + cri.toString());
		
		model.addAttribute("noticeVO", service.getNotice(ntNo));
		
	}
}
