package com.demo.biz.product.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.biz.common.SearchCriteria;
import com.demo.biz.product.CategoryVO;
import com.demo.biz.product.ProductDAO;
import com.demo.biz.product.ProductVO;

@Repository
public class ProductDAOImpl implements ProductDAO {
	
	private final static String NAMESPACE = "com.demo.mapper.ProductMapper";

	private final SqlSession session;
	
	@Autowired
	public ProductDAOImpl(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public void registerProduct(ProductVO vo) {
		// TODO Auto-generated method stub
		session.insert(NAMESPACE + ".registerProduct", vo);
	}

	@Override
	public List<ProductVO> getProductList(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return session.selectList(NAMESPACE + ".getProductList", cri);
	}

	@Override
	public int countProductList(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return session.selectOne(NAMESPACE + ".countProductList", cri);
	}

	@Override
	public List<CategoryVO> getCategoryList(Integer ctgyParent) {
		// TODO Auto-generated method stub
		return session.selectList(NAMESPACE + ".getCategoryList", ctgyParent);
	}

	@Override
	public ProductVO getProduct(Integer pdNo) {
		// TODO Auto-generated method stub
		return session.selectOne(NAMESPACE + ".getProduct", pdNo);
	}

	@Override
	public void deleteProduct(Integer pdNo) {
		// TODO Auto-generated method stub
		session.delete(NAMESPACE + ".deleteProduct", pdNo);
	}
	
	@Override
	public void deleteProducts(List<Integer> productList) {
		// TODO Auto-generated method stub
		session.delete(NAMESPACE + ".deleteProducts", productList);
	}
	
	@Override
	public void modifyProduct(ProductVO vo) {
		// TODO Auto-generated method stub
		session.update(NAMESPACE + ".modifyProduct", vo);
	}

	@Override
	public void modifyCheckedProduct(ProductVO vo) {
		// TODO Auto-generated method stub
		session.update(NAMESPACE + ".modifyCheckedProduct", vo);
	}

}
