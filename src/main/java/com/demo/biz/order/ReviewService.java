package com.demo.biz.order;

import java.util.List;

import com.demo.biz.common.Criteria;

/**
 * @ClassName : ReviewService.java
 * @Description : 리뷰 정보의 관리를 위한 서비스 인터페이스
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
public interface ReviewService {

    /**
     * 상품 리뷰를 작성한다.
     *
     * @param ReviewVO 리뷰 내용
     * @return
     */
	public void insertReview(ReviewVO vo);
	
    /**
     * 페이징된 상품 리뷰 목록을 가져온다.
     *
     * @param pdNo 상품 고유번호
     * @param Criteria 페이징 정보
     * @return List - 리뷰 목록
     */
	public List<ReviewVO> listReview(Integer pdNo, Criteria cri);
	
    /**
     * 리뷰의 총 개수를 반환한다.
     *
     * @param pdNo 상품 고유번호
     * @return 리뷰의 총 개수
     */
	public int countReviewList(Integer pdNo);
	
    /**
     * 상품 리뷰를 상품 테이블에서 삭제한다.
     *
     * @param revNo 리뷰 고유번호
     * @return
     */
	public void deleteReview(Integer revNo);
	
    /**
     * 상품 리뷰를 수정한다.
     *
     * @param revNo 리뷰 고유번호
     * @return
     */
	public void modifyReview(ReviewVO vo);
	
    /**
     * 상품 리뷰에 대한 상세 내용을 가져온다.
     *
     * @param ordNo 주문 고유번호
     * @param pdNo 상품 고유번호
     * @return ReviewVO - 리뷰 내용
     */
	public ReviewVO getReview(Integer ordNo, Integer pdNo);
	
}
