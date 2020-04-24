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

/**
 * @ClassName : AdminQnaController.java
 * @Description : 관리자 QNA관리 정보에 대한 컨트롤러 클래스
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
@RequestMapping("/admin/product/qna")
public class AdminQnaController {

	private static final Logger logger = LoggerFactory.getLogger(AdminQnaController.class);

	private final QnaService service;

	@Autowired
	public AdminQnaController(QnaService service) {
		this.service = service;
	}
	
    /**
     * QNA 목록 페이지.
     *
     * @param
     * @return JSP
     */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public void qnaList(@ModelAttribute("cri") SearchCriteria cri, Model model) {
		
		logger.info(cri.toString());
		
		model.addAttribute("qnaList", service.getQnaList(cri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		pageMaker.setTotalCount(service.countQnaList(cri));

		model.addAttribute("pageMaker", pageMaker);
		
	}
	
    /**
     * 관리자가 QNA 답변을 등록한다.
     *
     * @param QnaVO QNA 정보
     * @return ResponseEntity - 성공 여부
     */
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
	
    /**
     * 관리자가 QNA 글을 삭제한다.
     *
     * @param QnaVO QNA 정보
     * @return ResponseEntity - 성공 여부
     */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteQna(@RequestBody QnaVO vo) {
		
		ResponseEntity<String> entity = null;

		logger.info(vo.toString());
		
		try {
			service.deleteQna(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
	}
	
    /**
     * 관리자가 QNA 글을 수정한다.
     *
     * @param QnaVO QNA 정보
     * @return ResponseEntity - 성공 여부
     */
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
