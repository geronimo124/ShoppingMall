package com.demo.biz.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.biz.common.Criteria;
import com.demo.biz.product.CategoryVO;
import com.demo.biz.product.MProductDAO;
import com.demo.biz.product.MProductService;
import com.demo.biz.product.ProductVO;

@Service
public class MProductServiceImpl implements MProductService{

	private final MProductDAO dao;
	
	@Autowired
	public MProductServiceImpl(MProductDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public List<ProductVO> getProductList(String ctgyCd, Criteria cri) {
		// TODO Auto-generated method stub
		return dao.getProductList(ctgyCd, cri);
	}
	
	@Override
	public int countProductList(String ctgyCd) {
		// TODO Auto-generated method stub
		return dao.countProductList(ctgyCd);
	}
	
	@Override
	public List<CategoryVO> getCategoryList(Integer ctgyParent) {
		// TODO Auto-generated method stub
		return dao.getCategoryList(ctgyParent);
	}
	
	@Override
	public ProductVO getProduct(Integer pdNo) {
		// TODO Auto-generated method stub
		return dao.getProduct(pdNo);
	}

}
