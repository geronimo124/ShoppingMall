package com.demo.biz.product;

import java.util.List;
import java.util.Map;

/**
 * @ClassName : BasketDAO.java
 * @Description : 장바구니 정보의 관리를 위한 데이터 접근 인터페이스
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
public interface BasketDAO {

    /**
     * 장바구니 목록을 가져온다.
     *
     * @param mbId 회원 ID
     * @return List - 장바구니 목록
     */
	public List<BasketVO> getBaskets(String mbId);
	
    /**
     * 장바구니 테이블에 장바구니 정보를 삽입한다.
     *
     * @param BasketVO 장바구니 정보
     * @return
     */
	public void insertBasket(BasketVO vo);
	
    /**
     * 장바구니 목록에 해당 상품이 있는지 확인하기 위해 정보를 가져온다.
     *
     * @param BasketVO 장바구니 정보
     * @return BasketVO - 장바구니 정보
     */
	public BasketVO getBasket(BasketVO vo);
	
    /**
     * 장바구니 테이블에 장바구니 정보를 삭제한다.
     *
     * @param BasketVO 장바구니 정보
     * @return
     */
	public void deleteBasket(BasketVO vo);
	
    /**
     * 장바구니 정보를 업데이트한다.
     *
     * @param BasketVO 장바구니 정보
     * @return
     */
	public void updateBasket(BasketVO vo);
	
    /**
     * 선택된 장바구니 항목들을 삭제한다.
     *
     * @param Map 회원 ID와 삭제할 장바구니 목록 
     * @return
     */
	public void deleteBaskets(Map<String, Object> map);
}
