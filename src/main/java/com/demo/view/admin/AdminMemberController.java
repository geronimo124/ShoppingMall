package com.demo.view.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.biz.common.PageMaker;
import com.demo.biz.common.SearchCriteria;
import com.demo.biz.member.MemberService;

/**
 * @ClassName : AdminMemberController.java
 * @Description : 관리자 회원관리 정보에 대한 컨트롤러 클래스
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
@RequestMapping("/admin/member")
public class AdminMemberController {

	private static final Logger logger = LoggerFactory.getLogger(AdminMemberController.class);
	
	private final MemberService service;
	
	@Autowired
	public AdminMemberController(MemberService service) {
		this.service = service;
	}
	
    /**
     * 회원 목록 페이지.
     *
     * @param
     * @return JSP
     */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listMember(@ModelAttribute("cri") SearchCriteria cri, Model model) {
		
		logger.info(cri.toString());

		model.addAttribute("memberList", service.getMemberList(cri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		pageMaker.setTotalCount(service.countMemberList(cri));

		model.addAttribute("pageMaker", pageMaker);
	}
	
    /**
     * 관리자가 선택한 회원들을 탈퇴시킨다.
     *
     * @param List 선택한 회원들의 ID 목록
     * @return ResponseEntity - 성공 여부
     */
	@ResponseBody
	@RequestMapping(value = "deleteChecked", method = RequestMethod.POST)
	public ResponseEntity<String> deleteCheckedMember(@RequestParam("memberList[]") List<String> memberList) {
		
		ResponseEntity<String> entity = null;
		
		try {
			service.deleteMembers(memberList);
			entity = new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
