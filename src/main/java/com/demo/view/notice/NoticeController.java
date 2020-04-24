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

/**
 * @ClassName : NoticeController.java
 * @Description : 사용자 공지사항 정보에 대한 컨트롤러 클래스
 * @Modification Information
 *
 *    수정일			수정자		수정내용
 *    -------		-------     -------------------
 *    2020. 4. 23.	전일배		최초생성
 *
 * @author 전일배
 * @since 2020. 4. 23.
 * @version
 * @see
 *
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	private final NoticeService service;
	
	@Autowired
	public NoticeController(NoticeService service) {
		this.service = service;
	}
	
    /**
     * 공지사항 목록 페이지.
     *
     * @param
     * @return JSP
     */
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
	
    /**
     * 공지사항 읽기 페이지.
     *
     * @param ntNo 공지사항 고유번호
     * @return JSP
     */
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void readNotice(@RequestParam("ntNo") Integer ntNo, @ModelAttribute("cri") SearchCriteria cri, Model model) {

		logger.info(ntNo + cri.toString());
		
		model.addAttribute("noticeVO", service.getNotice(ntNo));
		
	}
	
    /**
     * 회사 소개 페이지.
     *
     * @param
     * @return JSP
     */
	@RequestMapping(value = "/company", method = RequestMethod.GET)
	public void company() {
		
	}
}
