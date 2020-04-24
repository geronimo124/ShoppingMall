package com.demo.biz.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.biz.common.Criteria;
import com.demo.biz.common.SearchCriteria;
import com.demo.biz.product.QnaDAO;
import com.demo.biz.product.QnaService;
import com.demo.biz.product.QnaVO;

/**
 * @ClassName : QnaServiceImpl.java
 * @Description : QNA 정보 관리를 위한 서비스 클래스
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
public class QnaServiceImpl implements QnaService {

	private final QnaDAO dao;
	
	@Autowired
	public QnaServiceImpl(QnaDAO dao) {
		this.dao = dao;
	}

    /**
     * QNA 테이블에 정보를 삽입한다.
     *
     * @param QnaVO QNA 정보
     * @return
     */
	@Override
	public void insertQna(QnaVO vo) {
		dao.insertQna(vo);
	}

    /**
     * 특정 상품의 QNA 목록을 페이징을 적용하여 가져온다. 
     *
     * @param pdNo 상품 고유번호
     * @param Criteria 페이징 정보
     * @return List - QNA 목록
     */
	@Override
	public List<QnaVO> listQna(Integer pdNo, Criteria cri) {
		
		Map<String, Object> map = new HashMap<>();

	    map.put("pdNo", pdNo);
	    map.put("cri", cri);
		
		return dao.listQna(map);
	}

    /**
     * QNA 목록의 총 항목 개수를 반환한다.
     *
     * @param pdNo 상품 고유번호
     * @return QNA 목록 개수
     */
	@Override
	public int countQnaList(Integer pdNo) {
		return dao.countQnaList(pdNo);
	}

    /**
     * 검색 페이징을 적용하여 QNA 목록을 가져온다.
     *
     * @param SearchCriteria 검색 페이징 정보
     * @return List - QNA 정보 목록
     */
	@Override
	public List<QnaVO> getQnaList(SearchCriteria cri) {
		return dao.getQnaList(cri);
	}

    /**
     * 검색 페이징이 적용된 QNA 목록 개수를 반환한다.
     *
     * @param SearchCriteria 검색 페이징 정보
     * @return QNA 목록 개수
     */
	@Override
	public int countQnaList(SearchCriteria cri) {
		return dao.countQnaList(cri);
	}

    /**
     * 특정 QNA 글을 삭제한다.
     *
     * @param QnaVO QNA 정보
     * @return
     */
	@Override
	public void deleteQna(QnaVO vo) {
		dao.deleteQna(vo);
	}

    /**
     * 답변이 있는 QNA인지 체크한 뒤 삭제하고 성공 여부를 반환한다.
     *
     * @param QnaVO QNA 정보
     * @return 성공 및 실패 여부
     */
	@Override
	public boolean deleteQnaCheck(QnaVO vo) {
		
		QnaVO child = dao.checkChild(vo);

		if(child != null)
			return false;
		
		dao.deleteQna(vo);
		
		return true;
	}

    /**
     * QNA 정보를 수정한다.
     *
     * @param QnaVO QNA 정보
     * @return
     */
	@Override
	public void modifyQna(QnaVO vo) {
		dao.modifyQna(vo);
	}
}
