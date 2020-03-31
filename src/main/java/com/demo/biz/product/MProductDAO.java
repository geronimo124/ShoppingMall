package com.demo.biz.product;

import java.util.List;

import com.demo.biz.common.Criteria;
import com.demo.biz.common.SearchCriteria;

public interface MProductDAO {

	public List<ProductVO> getProductList(String ctgyCd, Criteria cri);
	public List<ProductVO> getAllProductList(SearchCriteria cri);
	public int countProductList(String ctgyCd);
	public int countAllProductList(SearchCriteria cri);
	public List<CategoryVO> getCategoryList(Integer ctgyParent);
	public ProductVO getProduct(Integer pdNo);
}
