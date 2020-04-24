package com.demo.view.admin;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.biz.product.ProductVO;
import com.demo.biz.stat.StatService;
import com.demo.view.common.SessionListener;

/**
 * @ClassName : AdminStatController.java
 * @Description : 관리자 홈페이지의 통계 정보에 대한 컨트롤러 클래스
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
@RestController
@RequestMapping("/admin/stat")
public class AdminStatController {

	private static final Logger logger = LoggerFactory.getLogger(AdminStatController.class);

	private final StatService service;

	@Autowired
	public AdminStatController(StatService service) {
		this.service = service;
	}
	
    /**
     * 관리자 접속 이후의 새로운 주문 개수를 반환한다.
     *
     * @param admId 관리자 ID
     * @return ResponseEntity - 새로운 주문 개수
     */
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
	
    /**
     * 관리자 접속 이후의 새로 가입한 회원 수를 반환한다.
     *
     * @param admId 관리자 ID
     * @return ResponseEntity - 새로 가입한 회원 수
     */
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
	
    /**
     * 일주일 동안의 일일 매출을 반환한다.
     *
     * @param
     * @return ResponseEntity - 일일 매출 목록
     */
	@RequestMapping(value = "/getSalesGraph", method = RequestMethod.GET)
	public ResponseEntity<List<Map<String, Object>>> getSalesGraph() {
		
		ResponseEntity<List<Map<String, Object>>> entity = null;
		
		try {
			
			List<Map<String, Object>> list = service.getSalesGraph();
			
			logger.info(list.toString());
			
			entity = new ResponseEntity<List<Map<String, Object>>>(list, HttpStatus.OK);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			entity = new ResponseEntity<List<Map<String, Object>>>(HttpStatus.BAD_REQUEST);
			
		}
		
		return entity;
	}
	
    /**
     * 카테고리별 베스트 셀러 상품을 가져온다.
     *
     * @param
     * @return ResponseEntity - 베스트 셀러 상품 목록
     */
	@RequestMapping(value = "/getBestSellers", method = RequestMethod.GET)
	public ResponseEntity<List<ProductVO>> getBestSellers() {
		
		ResponseEntity<List<ProductVO>> entity = null;
		
		try {
			
			List<ProductVO> list = service.getBestSellers();
			
			logger.info(list.toString());
			
			entity = new ResponseEntity<List<ProductVO>>(list, HttpStatus.OK);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			entity = new ResponseEntity<List<ProductVO>>(HttpStatus.BAD_REQUEST);
			
		}
		
		return entity;
	}
	
    /**
     * 현재 접속중인 회원 수를 반환한다.
     *
     * @param
     * @return ResponseEntity - 접속중인 회원 수
     */
	@RequestMapping(value = "/getConnectedMembers", method = RequestMethod.GET)
	public ResponseEntity<Integer> getConnectedMembers() {
		
		ResponseEntity<Integer> entity = null;
		
		SessionListener listener = SessionListener.getInstance();
		
		try {
			
			entity = new ResponseEntity<Integer>(listener.getUserCount(), HttpStatus.OK);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			entity = new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
			
		}
		
		return entity;
	}
}
