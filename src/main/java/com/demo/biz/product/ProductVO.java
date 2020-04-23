package com.demo.biz.product;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : ProductVO.java
 * @Description : 상품 정보 VO 클래스
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
@Getter
@Setter
@ToString
@SuppressWarnings("serial")
public class ProductVO implements Serializable {

	/**
	 * 상품 고유번호
	 */
	private Integer pdNo;
	/**
	 * 부모 카테고리 고유코드
	 */
	private Integer ctgyPtcd;
	/**
	 * 부모 카테고리 이름
	 */
	private String ctgyPtnm;
	/**
	 * 상품 카테고리 고유코드
	 */
	private Integer ctgyCd;
	/**
	 * 상품 카테고리 이름
	 */
	private String ctgyNm;
	/**
	 * 상품 이름
	 */
	private String pdNm;
	/**
	 * 상품 가격
	 */
	private Integer pdTag;
	/**
	 * 상품 할인율
	 */
	private Integer pdSale;
	/**
	 * 상품 진열상태
	 */
	private String pdStatus;
	/**
	 * 상품 이미지
	 */
	private String pdImg;
	/**
	 * 상품 상세설명
	 */
	private String pdDetl;
	/**
	 * 상품 재고
	 */
	private Integer pdStock;
	/**
	 * 상품 등록일자
	 */
	private Date pdEnldt;
	/**
	 * 상품 업데이트일자
	 */
	private Date pdUpddt;
	
	
	/**
	 * 상품 파일 업로드
	 */
	private MultipartFile file;

}
