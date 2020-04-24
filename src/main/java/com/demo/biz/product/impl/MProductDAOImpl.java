package com.demo.biz.product.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.biz.common.SearchCriteria;
import com.demo.biz.product.CategoryVO;
import com.demo.biz.product.MProductDAO;
import com.demo.biz.product.ProductVO;

/**
 * @ClassName : MProductDAOImpl.java
 * @Description : 사용자의 상품 정보 관리를 위한 데이터 접근 클래스
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
public class MProductDAOImpl implements MProductDAO {

	private final static String NAMESPACE = "com.demo.mapper.MProductMapper";

	private final SqlSession session;
	
	@Autowired
	public MProductDAOImpl(SqlSession session) {
		this.session = session;
	}
	
    /**
     * 해당 카테고리에 해당하는 상품 목록을 페이징을 적용하여 가져온다.
     *
     * @param Map 카테고리 고유코드와 페이징 정보
     * @return List - 상품 목록
     */
	@Override
	public List<ProductVO> getProductList(Map<String, Object> map) {
		return session.selectList(NAMESPACE + ".getProductList", map);
	}

    /**
     * 해당 카테고리에 해당하는 상품의 총 개수를 반환한다.
     *
     * @param ctgyCd 카테고리 고유코드
     * @return 상품의 총 개수
     */
	@Override
	public int countProductList(Integer ctgyCd) {
		return session.selectOne(NAMESPACE + ".countProductList", ctgyCd);
	}

    /**
     * 해당 카테고리의 자식 카테고리들을 반환한다.
     *
     * @param ctgyParent 부모 카테고리 고유코드
     * @return List - 카테고리 목록
     */
	@Override
	public List<CategoryVO> getCategoryList(Integer ctgyParent) {
		return session.selectList(NAMESPACE + ".getCategoryList", ctgyParent);
	}

    /**
     * 해당 상품의 정보를 가져온다.
     *
     * @param pdNo 상품 고유번호
     * @return ProductVO - 상품 정보
     */
	@Override
	public ProductVO getProduct(Integer pdNo) {
		return session.selectOne(NAMESPACE + ".getProduct", pdNo);
	}

    /**
     * 검색된 모든 상품 목록을 가져온다.
     *
     * @param SearchCriteria 검색 페이징 정보
     * @return List - 상품 목록
     */
	@Override
	public List<ProductVO> getAllProductList(SearchCriteria cri) {
		return session.selectList(NAMESPACE + ".getAllProductList", cri);
	}

    /**
     * 검색된 상품의 총 개수를 반환한다.
     *
     * @param SearchCriteria 검색 페이징 정보
     * @return 상품의 총 개수
     */
	@Override
	public int countAllProductList(SearchCriteria cri) {
		return session.selectOne(NAMESPACE + ".countAllProductList", cri);
	}

}
