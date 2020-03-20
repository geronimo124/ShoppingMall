package com.demo.biz.product;

import java.util.List;
import java.util.Map;

import com.demo.biz.common.SearchCriteria;

public interface ProductService {

	public void registerProduct(ProductVO vo);
	public List<ProductVO> getProductList(SearchCriteria cri);
	public List<ProductVO> getProductList(Integer ctgyCd);
	public int countProductList(SearchCriteria cri);
	public List<CategoryVO> getCategoryList(Integer ctgyParent);
	public ProductVO getProduct(Integer pdNo);
	public void deleteProduct(Integer pdNo);
	public void deleteProducts(List<Integer> productList);
	public void modifyProduct(ProductVO vo);
	public void modifyCheckedProducts(List<ProductVO> productList);
}
