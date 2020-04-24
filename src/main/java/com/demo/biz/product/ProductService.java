package com.demo.biz.product;

import java.util.List;

import com.demo.biz.common.SearchCriteria;

/**
 * @ClassName : ProductService.java
 * @Description : 관리자의 상품 정보 관리를 위한 서비스 인터페이스
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
public interface ProductService {

    /**
     * 새로운 상품을 등록한다.
     *
     * @param ProductVO 상품 정보
     * @return
     */
	public void registerProduct(ProductVO vo);
	
    /**
     * 검색된 모든 상품 목록을 가져온다.
     *
     * @param SearchCriteria 검색 페이징 정보
     * @return List - 상품 목록
     */
	public List<ProductVO> getProductList(SearchCriteria cri);
	
    /**
     * 해당 카테고리에 해당하는 상품 목록을 가져온다.
     *
     * @param ctgyCd 카테고리 고유코드
     * @return List - 상품 목록
     */
	public List<ProductVO> getProductList(Integer ctgyCd);
	
    /**
     * 검색된 상품의 총 개수를 반환한다.
     *
     * @param SearchCriteria 검색 페이징 정보
     * @return 상품의 총 개수
     */
	public int countProductList(SearchCriteria cri);
	
    /**
     * 해당 카테고리의 자식 카테고리들을 반환한다.
     *
     * @param ctgyParent 부모 카테고리 고유코드
     * @return List - 카테고리 목록
     */
	public List<CategoryVO> getCategoryList(Integer ctgyParent);
	
    /**
     * 해당 상품의 정보를 가져온다.
     *
     * @param pdNo 상품 고유번호
     * @return ProductVO - 상품 정보
     */
	public ProductVO getProduct(Integer pdNo);
	
    /**
     * 해당 상품의 정보를 삭제한다.
     *
     * @param pdNo 상품 고유번호
     * @return
     */
	public void deleteProduct(Integer pdNo);
	
    /**
     * 선택된 상품들의 정보를 삭제한다.
     *
     * @param List 선택된 상품 목록
     * @return
     */
	public void deleteProducts(List<Integer> productList);
	
    /**
     * 해당 상품을 수정한다.
     *
     * @param ProductVO 상품 정보
     * @return
     */
	public void modifyProduct(ProductVO vo);
	
    /**
     * 선택된 상품들을 수정한다.
     *
     * @param List 상품 정보 목록
     * @return
     */
	public void modifyCheckedProducts(List<ProductVO> productList);
}
