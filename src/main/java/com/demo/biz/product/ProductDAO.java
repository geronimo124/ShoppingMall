package com.demo.biz.product;

import java.util.List;

import com.demo.biz.common.SearchCriteria;

public interface ProductDAO {

	public void registerProduct(ProductVO vo);
	public List<ProductVO> getProductList(SearchCriteria cri);
	public int countProductList(SearchCriteria cri);
	public List<CategoryVO> getCategoryList(Integer ctgyParent);
	public ProductVO getProduct(Integer pdNo);
	public void deleteProduct(Integer pdNo);
	public void modifyProduct(ProductVO vo);
}
