package com.demo.view.admin;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.biz.admin.AdminVO;
import com.demo.biz.common.PageMaker;
import com.demo.biz.common.SearchCriteria;
import com.demo.biz.notice.NoticeService;
import com.demo.biz.notice.NoticeVO;

/**
 * @ClassName : AdminNoticeController.java
 * @Description : 관리자 공지사항관리 정보에 대한 컨트롤러 클래스
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
@RequestMapping("/admin/notice")
public class AdminNoticeController {

	private static final Logger logger = LoggerFactory.getLogger(AdminNoticeController.class);
	
	private final NoticeService service;
	
	@Autowired
	public AdminNoticeController(NoticeService service) {
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
		
		return "/admin/notice/list";
	}
	
    /**
     * 공지사항 글쓰기 페이지.
     *
     * @param
     * @return JSP
     */
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void writeNotice() {
		
	}
	
    /**
     * 관리자가 공지사항을 작성한다.
     *
     * @param NoticeVO 공지사항 정보
     * @return redirect URL
     */
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writeNotice(HttpSession session, RedirectAttributes rttr, NoticeVO vo) {
		
		logger.info(vo.toString());
		
		AdminVO admin = (AdminVO) session.getAttribute("admin");
		
		vo.setAdmNm(admin.getAdmNm());
		vo.setAdmId(admin.getAdmId());
		
		service.writeNotice(vo);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/admin/notice";
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
     * 관리자가 공지사항 글을 삭제한다.
     *
     * @param ntNo 공지사항 고유번호
     * @return ResponseEntity - 성공 여부
     */
	@ResponseBody
	@RequestMapping(value = "/delete/{ntNo}", method = RequestMethod.GET)
	public ResponseEntity<String> deleteNotice(@PathVariable("ntNo") Integer ntNo) {
		
		logger.info(ntNo.toString());
		
		ResponseEntity<String> entity = null;
		
		try {
			service.deleteNotice(ntNo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
    /**
     * 공지사항 수정 페이지.
     *
     * @param ntNo 공지사항 고유번호
     * @return JSP
     */
	@RequestMapping(value = "modify", method = RequestMethod.GET)
	public void modifyNotice(@RequestParam("ntNo") Integer ntNo, @ModelAttribute("cri") SearchCriteria cri, Model model) {
		
		NoticeVO vo = service.getNotice(ntNo);
		logger.info(vo.toString());
		
		model.addAttribute("noticeVO", vo);
	}
	
    /**
     * 관리자가 공지사항을 수정한다.
     *
     * @param NoticeVO 공지사항 정보
     * @return redirect URL
     */
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String modifyNotice(NoticeVO vo, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		
		logger.info(vo.toString());
		
		service.modifyNotice(vo);
		
		rttr.addAttribute("page", cri.getPage());
	    rttr.addAttribute("perPageNum", cri.getPerPageNum());
	    rttr.addAttribute("searchType", cri.getSearchType());
	    rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/admin/notice";
	}
}
