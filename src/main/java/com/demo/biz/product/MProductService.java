package com.demo.biz.product;

import java.util.List;

import com.demo.biz.common.Criteria;

public interface MProductService {

	public List<ProductVO> getProductList(String ctgyCd, Criteria cri);
	public int countProductList(String ctgyCd);
	public List<CategoryVO> getCategoryList(Integer ctgyParent);
	public ProductVO getProduct(Integer pdNo);
}
