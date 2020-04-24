package com.demo.biz.product.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.biz.product.BasketDAO;
import com.demo.biz.product.BasketService;
import com.demo.biz.product.BasketVO;

/**
 * @ClassName : BasketServiceImpl.java
 * @Description : 장바구니 정보의 관리를 위한 서비스 클래스
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
public class BasketServiceImpl implements BasketService {

	private final BasketDAO dao;
	
	@Autowired
	public BasketServiceImpl(BasketDAO dao) {
		this.dao = dao;
	}

    /**
     * 장바구니 목록을 가져온다.
     *
     * @param mbId 회원 ID
     * @return List - 장바구니 목록
     */
	@Override
	public List<BasketVO> getBaskets(String mbId) {
		return dao.getBaskets(mbId);
	}

    /**
     * 장바구니 테이블에 장바구니 정보를 삽입 후 성공 및 실패를 반환한다.
     *
     * @param BasketVO 장바구니 정보
     * @return 삽입 성공 및 실패
     */
	@Override
	public boolean insertBasket(BasketVO vo) {
		
		BasketVO getvo = dao.getBasket(vo);
		
		if(getvo != null)
			return false;
		else {
			dao.insertBasket(vo);
			return true;
		}
	}

    /**
     * 장바구니 테이블에 장바구니 정보를 삭제한다.
     *
     * @param BasketVO 장바구니 정보
     * @return
     */
	@Override
	public void deleteBasket(BasketVO vo) {
		dao.deleteBasket(vo);
	}

    /**
     * 장바구니 정보를 업데이트한다.
     *
     * @param BasketVO 장바구니 정보
     * @return
     */
	@Override
	public void updateBasket(BasketVO vo) {
		dao.updateBasket(vo);
	}

    /**
     * 선택된 장바구니 항목들을 삭제한다.
     *
     * @param Map 회원 ID와 삭제할 장바구니 목록 
     * @return
     */
	@Override
	public void deleteBaskets(Map<String, Object> map) {
		dao.deleteBaskets(map);
	}

    /**
     * 선택된 장바구니 정보를 업데이트한다.
     *
     * @param List 장바구니 정보 목록
     * @return
     */
	@Transactional
	@Override
	public void updateBaskets(List<BasketVO> basketList) {
		
		for(BasketVO vo : basketList)
			dao.updateBasket(vo);
		
	}

}
