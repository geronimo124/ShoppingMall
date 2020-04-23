package com.demo.biz.order.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.biz.common.Criteria;
import com.demo.biz.order.ReviewDAO;
import com.demo.biz.order.ReviewService;
import com.demo.biz.order.ReviewVO;

/**
 * @ClassName : ReviewServiceImpl.java
 * @Description : 리뷰 정보의 관리를 위한 서비스 클래스
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
public class ReviewServiceImpl implements ReviewService {

	private final ReviewDAO dao;
	
	@Autowired
	public ReviewServiceImpl(ReviewDAO dao) {
		this.dao = dao;
	}

    /**
     * 상품 리뷰를 작성한다.
     *
     * @param ReviewVO - 리뷰 내용
     * @return
     */
	@Override
	public void insertReview(ReviewVO vo) {
		dao.insertReview(vo);
	}

    /**
     * 페이징된 상품 리뷰 목록을 가져온다.
     *
     * @param pdNo - 상품 고유번호
     * @param Criteria - 페이징 정보
     * @return List<ReviewVO> - 리뷰 목록
     */
	@Override
	public List<ReviewVO> listReview(Integer pdNo, Criteria cri) {
		return dao.listReview(pdNo, cri);
	}

    /**
     * 리뷰의 총 개수를 반환한다.
     *
     * @param pdNo - 상품 고유번호
     * @return 리뷰의 총 개수
     */
	@Override
	public int countReviewList(Integer pdNo) {
		return dao.countReviewList(pdNo);
	}

    /**
     * 상품 리뷰를 상품 테이블에서 삭제한다.
     *
     * @param revNo - 리뷰 고유번호
     * @return
     */
	@Override
	public void deleteReview(Integer revNo) {
		dao.deleteReview(revNo);
	}

    /**
     * 상품 리뷰를 수정한다.
     *
     * @param revNo - 리뷰 고유번호
     * @return
     */
	@Override
	public void modifyReview(ReviewVO vo) {
		dao.modifyReview(vo);
	}

    /**
     * 상품 리뷰에 대한 상세 내용을 가져온다.
     *
     * @param ordNo - 주문 고유번호
     * @param pdNo - 상품 고유번호
     * @return ReviewVO - 리뷰 내용
     */
	@Override
	public ReviewVO getReview(Integer ordNo, Integer pdNo) {
		return dao.getReview(ordNo, pdNo);
	}
}
