package com.demo.biz.order.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.biz.order.ReviewDAO;
import com.demo.biz.order.ReviewVO;

/**
 * @ClassName : ReviewDAOImpl.java
 * @Description : 리뷰 정보의 관리를 위한 데이터 접근 클래스
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
public class ReviewDAOImpl implements ReviewDAO {
	
	private final static String NAMESPACE = "com.demo.mapper.ReviewMapper";

	private final SqlSession session;
	
	@Autowired
	public ReviewDAOImpl(SqlSession session) {
		this.session = session;
	}

    /**
     * 상품 리뷰를 작성한다.
     *
     * @param ReviewVO 리뷰 내용
     * @return
     */
	@Override
	public void insertReview(ReviewVO vo) {
		session.insert(NAMESPACE + ".insertReview", vo);
	}

    /**
     * 페이징된 상품 리뷰 목록을 가져온다.
     *
     * @param Map 상품 고유코드와 페이징 정보
     * @return List - 리뷰 목록
     */
	@Override
	public List<ReviewVO> listReview(Map<String, Object> map) {
	    return session.selectList(NAMESPACE + ".listReview", map);
	}

    /**
     * 리뷰의 총 개수를 반환한다.
     *
     * @param pdNo 상품 고유번호
     * @return 리뷰의 총 개수
     */
	@Override
	public int countReviewList(Integer pdNo) {
		return session.selectOne(NAMESPACE + ".countReviewList", pdNo);
	}

    /**
     * 상품 리뷰를 상품 테이블에서 삭제한다.
     *
     * @param revNo 리뷰 고유번호
     * @return
     */
	@Override
	public void deleteReview(Integer revNo) {
		session.delete(NAMESPACE + ".deleteReview", revNo);
	}

    /**
     * 상품 리뷰를 수정한다.
     *
     * @param revNo 리뷰 고유번호
     * @return
     */
	@Override
	public void modifyReview(ReviewVO vo) {
		session.update(NAMESPACE + ".modifyReview", vo);
	}

    /**
     * 상품 리뷰에 대한 상세 내용을 가져온다.
     *
     * @param Map 주문 고유번호, 상품 고유번호
     * @return ReviewVO - 리뷰 내용
     */
	@Override
	public ReviewVO getReview(Map<String, Integer> map) {
		return session.selectOne(NAMESPACE + ".getReview", map);
	}
}
