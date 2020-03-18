package com.demo.biz.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.biz.common.SearchCriteria;
import com.demo.biz.product.CategoryVO;
import com.demo.biz.product.ProductDAO;
import com.demo.biz.product.ProductService;
import com.demo.biz.product.ProductVO;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductDAO dao;
	
	@Autowired
	public ProductServiceImpl(ProductDAO dao) {
		this.dao = dao;
	}

	@Override
	public void registerProduct(ProductVO vo) {
		// TODO Auto-generated method stub
		dao.registerProduct(vo);
	}
	
	@Override
	public List<ProductVO> getProductList(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return dao.getProductList(cri);
	}

	@Override
	public int countProductList(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return dao.countProductList(cri);
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

	@Override
	public void deleteProduct(Integer pdNo) {
		// TODO Auto-generated method stub
		dao.deleteProduct(pdNo);
	}

	@Override
	public void deleteProducts(List<Integer> productList) {
		// TODO Auto-generated method stub
		dao.deleteProducts(productList);
	}
	
	@Override
	public void modifyProduct(ProductVO vo) {
		// TODO Auto-generated method stub
		dao.modifyProduct(vo);
	}

	@Override
	public void modifyCheckedProduct(ProductVO vo) {
		// TODO Auto-generated method stub
		dao.modifyCheckedProduct(vo);
	}



}
