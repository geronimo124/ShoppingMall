package com.demo.view.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.biz.common.PageMaker;
import com.demo.biz.order.OrderSearchCriteria;
import com.demo.biz.order.OrderService;
import com.demo.biz.order.OrderVO;

/**
 * @ClassName : AdminOrderController.java
 * @Description : 관리자 주문관리 정보에 대한 컨트롤러 클래스
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
@RequestMapping("/admin/order")
public class AdminOrderController {

	private static final Logger logger = LoggerFactory.getLogger(AdminOrderController.class);
	
	private final OrderService service;
	
	@Autowired
	public AdminOrderController(OrderService service) {
		this.service = service;
	}
	
    /**
     * 주문조회 페이지.
     *
     * @param
     * @return JSP
     */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public void mainOrder() {
		
	}
	
    /**
     * 주문조회 페이지. 다중 검색 페이징 기능을 이용한다.
     *
     * @param Map 여러 검색 정보
     * @return JSP
     */
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public void listOrder(@RequestParam Map<String, String> paramMap, Model model) {
		
		OrderSearchCriteria cri = new OrderSearchCriteria(paramMap);
		
		if(paramMap.get("page") != null)
			cri.setPage(Integer.parseInt(paramMap.get("page")));
		if(paramMap.get("perPageNum") != null)
			cri.setPerPageNum(Integer.parseInt(paramMap.get("perPageNum")));
		
		model.addAttribute("cri", cri);
		model.addAttribute("orderList", service.getAllOrderList(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		pageMaker.setTotalCount(service.countAllOrderList(cri));

		model.addAttribute("pageMaker", pageMaker);
		
	}

    /**
     * 주문 상세보기 페이지.
     *
     * @param ordNo 주문 고유번호
     * @return JSP
     */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public void detailOrder(Integer ordNo, Model model) {
		
		model.addAttribute("orderList", service.getOrderDetail(ordNo));
		model.addAttribute("order", service.getOrder(ordNo));
		
	}
	
    /**
     * 관리자가 선택된 주문정보를 수정한다.
     *
     * @param List 주문정보 목록
     * @return ResponseEntity - 성공 여부
     */
	@ResponseBody
	@RequestMapping(value = "modifyChecked", method = RequestMethod.POST)
	public ResponseEntity<String> modifyCheckedOrder(@RequestBody List<OrderVO> orderList) {
		
		logger.info(orderList.toString());
		
		ResponseEntity<String> entity = null;
		
		try {
			service.modifyCheckedOrders(orderList);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
    /**
     * 관리자가 선택된 주문정보를 삭제한다.
     *
     * @param List 주문 고유번호 목록
     * @return ResponseEntity - 성공 여부
     */
	@ResponseBody
	@RequestMapping(value = "deleteChecked", method = RequestMethod.POST)
	public ResponseEntity<String> deleteCheckedOrder(HttpServletRequest request, @RequestParam("orderList[]") List<Integer> orderList) {
		
		ResponseEntity<String> entity = null;
		
		try {
			service.deleteOrders(orderList);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
