package com.demo.biz.product.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.biz.product.BasketDAO;
import com.demo.biz.product.BasketVO;

/**
 * @ClassName : BasketDAOImpl.java
 * @Description : 장바구니 정보의 관리를 위한 데이터 접근 클래스
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
@Repository
public class BasketDAOImpl implements BasketDAO {

	private final static String NAMESPACE = "com.demo.mapper.BasketMapper";

	private final SqlSession session;
	
	@Autowired
	public BasketDAOImpl(SqlSession session) {
		this.session = session;
	}
	
    /**
     * 장바구니 목록을 가져온다.
     *
     * @param mbId 회원 ID
     * @return List - 장바구니 목록
     */
	@Override
	public List<BasketVO> getBaskets(String mbId) {
		return session.selectList(NAMESPACE + ".getBaskets", mbId);
	}

    /**
     * 장바구니 테이블에 장바구니 정보를 삽입한다.
     *
     * @param BasketVO 장바구니 정보
     * @return
     */
	@Override
	public void insertBasket(BasketVO vo) {
		session.insert(NAMESPACE + ".insertBasket", vo);
	}

    /**
     * 장바구니 목록에 해당 상품이 있는지 확인하기 위해 정보를 가져온다.
     *
     * @param BasketVO 장바구니 정보
     * @return BasketVO - 장바구니 정보
     */
	@Override
	public BasketVO getBasket(BasketVO vo) {
		return session.selectOne(NAMESPACE + ".getBasket", vo);
	}

    /**
     * 장바구니 테이블에 장바구니 정보를 삭제한다.
     *
     * @param BasketVO 장바구니 정보
     * @return
     */
	@Override
	public void deleteBasket(BasketVO vo) {
		session.delete(NAMESPACE + ".deleteBasket", vo);
	}

    /**
     * 장바구니 정보를 업데이트한다.
     *
     * @param BasketVO 장바구니 정보
     * @return
     */
	@Override
	public void updateBasket(BasketVO vo) {
		session.update(NAMESPACE + ".updateBasket", vo);
	}

    /**
     * 선택된 장바구니 항목들을 삭제한다.
     *
     * @param Map 회원 ID와 삭제할 장바구니 목록 
     * @return
     */
	@Override
	public void deleteBaskets(Map<String, Object> map) {
		session.delete(NAMESPACE + ".deleteBaskets", map);
	}

}
