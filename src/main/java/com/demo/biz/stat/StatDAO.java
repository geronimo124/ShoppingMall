package com.demo.biz.stat;

import java.util.List;
import java.util.Map;

import com.demo.biz.product.CategoryVO;
import com.demo.biz.product.ProductVO;

/**
 * @ClassName : StatDAO.java
 * @Description : 통계 정보의 관리를 위한 데이터 접근 인터페이스
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
public interface StatDAO {

    /**
     * 관리자가 마지막으로 접속한 이후부터의 새로운 주문개수를 반환한다.
     *
     * @param admId 관리자 ID
     * @return 새로운 주문 개수
     */
	public int getCountNewOrders(String admId);
	
    /**
     * 관리자가 마지막으로 접속한 이후부터의 새로 가입한 회원 수를 반환한다.
     *
     * @param admId 관리자 ID
     * @return 새로 가입한 회원 수
     */
	public int getCountNewMembers(String admId);
	
    /**
     * 일주일 동안의 일일 매출을 가져온다.
     *
     * @param
     * @return List - 일자별 매출 목록
     */
	public List<Map<String, Object>> getSalesGraph();
	
    /**
     * 특정 카테고리의 베스트 셀러 상품을 가져온다.
     *
     * @param ctgyPtcd 부모 카테고리 고유코드
     * @return ProductVO - 상품 정보
     */
	public ProductVO getBestSeller(Integer ctgyPtcd);
	
    /**
     * 특정 카테고리의 하위 카테고리 목록을 가져온다.
     *
     * @param ctgyParent 부모 카테고리 고유코드
     * @return List - 카테고리 목록
     */
	public List<CategoryVO> getCategoryList(Integer ctgyParent);
	
}
