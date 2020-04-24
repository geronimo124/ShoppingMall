package com.demo.biz.product;

import java.util.List;

import com.demo.biz.common.Criteria;
import com.demo.biz.common.SearchCriteria;

/**
 * @ClassName : QnaService.java
 * @Description : QNA 정보 관리를 위한 서비스 인터페이스
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
public interface QnaService {
	
    /**
     * QNA 테이블에 정보를 삽입한다.
     *
     * @param QnaVO QNA 정보
     * @return
     */
	public void insertQna(QnaVO vo);
	
    /**
     * 특정 상품의 QNA 목록을 페이징을 적용하여 가져온다. 
     *
     * @param pdNo 상품 고유번호
     * @param Criteria 페이징 정보
     * @return List - QNA 목록
     */
	List<QnaVO> listQna(Integer pdNo, Criteria cri);
	
    /**
     * QNA 목록의 총 항목 개수를 반환한다.
     *
     * @param pdNo 상품 고유번호
     * @return QNA 목록 개수
     */
	public int countQnaList(Integer pdNo);
	
    /**
     * 답변이 있는 QNA인지 체크한 뒤 삭제하고 성공 여부를 반환한다.
     *
     * @param QnaVO QNA 정보
     * @return 성공 및 실패 여부
     */
	public boolean deleteQnaCheck(QnaVO vo);
	
    /**
     * QNA 정보를 수정한다.
     *
     * @param QnaVO QNA 정보
     * @return
     */
	public void modifyQna(QnaVO vo);

    /**
     * 검색 페이징을 적용하여 QNA 목록을 가져온다.
     *
     * @param SearchCriteria 검색 페이징 정보
     * @return List - QNA 정보 목록
     */
	public List<QnaVO> getQnaList(SearchCriteria cri);
	
    /**
     * 검색 페이징이 적용된 QNA 목록 개수를 반환한다.
     *
     * @param SearchCriteria 검색 페이징 정보
     * @return QNA 목록 개수
     */
	public int countQnaList(SearchCriteria cri);
	
    /**
     * 특정 QNA 글을 삭제한다.
     *
     * @param QnaVO QNA 정보
     * @return
     */
	public void deleteQna(QnaVO vo);

}
