package com.demo.biz.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.biz.common.SearchCriteria;
import com.demo.biz.product.CategoryVO;
import com.demo.biz.product.ProductDAO;
import com.demo.biz.product.ProductService;
import com.demo.biz.product.ProductVO;

/**
 * @ClassName : ProductServiceImpl.java
 * @Description : 관리자의 상품 정보 관리를 위한 서비스 클래스
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
public class ProductServiceImpl implements ProductService {

	private final ProductDAO dao;
	
	@Autowired
	public ProductServiceImpl(ProductDAO dao) {
		this.dao = dao;
	}

    /**
     * 새로운 상품을 등록한다.
     *
     * @param ProductVO 상품 정보
     * @return
     */
	@Override
	public void registerProduct(ProductVO vo) {
		dao.registerProduct(vo);
	}
	
    /**
     * 검색된 모든 상품 목록을 가져온다.
     *
     * @param SearchCriteria 검색 페이징 정보
     * @return List - 상품 목록
     */
	@Override
	public List<ProductVO> getProductList(SearchCriteria cri) {
		return dao.getProductList(cri);
	}

    /**
     * 검색된 상품의 총 개수를 반환한다.
     *
     * @param SearchCriteria 검색 페이징 정보
     * @return 상품의 총 개수
     */
	@Override
	public int countProductList(SearchCriteria cri) {
		return dao.countProductList(cri);
	}

    /**
     * 해당 카테고리의 자식 카테고리들을 반환한다.
     *
     * @param ctgyParent 부모 카테고리 고유코드
     * @return List - 카테고리 목록
     */
	@Override
	public List<CategoryVO> getCategoryList(Integer ctgyParent) {
		return dao.getCategoryList(ctgyParent);
	}

    /**
     * 해당 상품의 정보를 가져온다.
     *
     * @param pdNo 상품 고유번호
     * @return ProductVO - 상품 정보
     */
	@Override
	public ProductVO getProduct(Integer pdNo) {
		return dao.getProduct(pdNo);
	}

    /**
     * 해당 상품의 정보를 삭제한다.
     *
     * @param pdNo 상품 고유번호
     * @return
     */
	@Override
	public void deleteProduct(Integer pdNo) {
		dao.deleteProduct(pdNo);
	}

    /**
     * 선택된 상품들의 정보를 삭제한다.
     *
     * @param List 선택된 상품 목록
     * @return
     */
	@Override
	public void deleteProducts(List<Integer> productList) {
		dao.deleteProducts(productList);
	}
	
    /**
     * 해당 상품을 수정한다.
     *
     * @param ProductVO 상품 정보
     * @return
     */
	@Override
	public void modifyProduct(ProductVO vo) {
		dao.modifyProduct(vo);
	}

    /**
     * 선택된 상품들을 수정한다.
     *
     * @param List 상품 정보 목록
     * @return
     */
	@Transactional
	@Override
	public void modifyCheckedProducts(List<ProductVO> productList) {
		
		for(ProductVO vo : productList)
			dao.modifyCheckedProduct(vo);
		
	}

    /**
     * 해당 카테고리에 해당하는 상품 목록을 가져온다.
     *
     * @param ctgyCd 카테고리 고유코드
     * @return List - 상품 목록
     */
	@Override
	public List<ProductVO> getProductList(Integer ctgyCd) {
		return dao.getProductList(ctgyCd);
	}

}
