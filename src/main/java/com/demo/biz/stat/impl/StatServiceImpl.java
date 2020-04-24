package com.demo.biz.stat.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.biz.product.CategoryVO;
import com.demo.biz.product.ProductVO;
import com.demo.biz.stat.StatDAO;
import com.demo.biz.stat.StatService;

/**
 * @ClassName : StatServiceImpl.java
 * @Description : 통계 정보의 관리를 위한 서비스 클래스
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
@Service
public class StatServiceImpl implements StatService {

	private final StatDAO dao;
	
	@Autowired
	public StatServiceImpl(StatDAO dao) {
		this.dao = dao;
	}

    /**
     * 관리자가 마지막으로 접속한 이후부터의 새로운 주문개수를 반환한다.
     *
     * @param admId 관리자 ID
     * @return 새로운 주문 개수
     */
	@Override
	public int getCountNewOrders(String admId) {
		return dao.getCountNewOrders(admId);
	}

    /**
     * 관리자가 마지막으로 접속한 이후부터의 새로 가입한 회원 수를 반환한다.
     *
     * @param admId 관리자 ID
     * @return 새로 가입한 회원 수
     */
	@Override
	public int getCountNewMembers(String admId) {
		return dao.getCountNewMembers(admId);
	}

    /**
     * 일주일 동안의 일일 매출을 가져온다.
     *
     * @param
     * @return List - 일자별 매출 목록
     */
	@Override
	public List<Map<String, Object>> getSalesGraph() {
		return dao.getSalesGraph();
	}

    /**
     * 카테고리별 베스트 셀러 상품 목록을 가져온다.
     *
     * @param
     * @return List - 베스트 셀러 상품 목록
     */
	@Transactional
	@Override
	public List<ProductVO> getBestSellers() {
		
		List<CategoryVO> cateList = dao.getCategoryList(1);
		
		List<ProductVO> bestSellerList = new ArrayList<>();
		
		for(CategoryVO category : cateList)
			bestSellerList.add(dao.getBestSeller(category.getCtgyCd()));
		
		return bestSellerList;
		
	}

}
