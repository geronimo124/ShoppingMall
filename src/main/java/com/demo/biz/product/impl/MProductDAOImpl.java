package com.demo.biz.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.biz.common.Criteria;
import com.demo.biz.common.SearchCriteria;
import com.demo.biz.product.CategoryVO;
import com.demo.biz.product.MProductDAO;
import com.demo.biz.product.ProductVO;

@Repository
public class MProductDAOImpl implements MProductDAO {

	private final static String NAMESPACE = "com.demo.mapper.MProductMapper";

	private final SqlSession session;
	
	@Autowired
	public MProductDAOImpl(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<ProductVO> getProductList(@Param("ctgyCd") String ctgyCd, @Param("cri") Criteria cri) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		
		map.put("ctgyCd", ctgyCd);
		map.put("cri", cri);
		
		return session.selectList(NAMESPACE + ".getProductList", map);
	}

	@Override
	public int countProductList(String ctgyCd) {
		// TODO Auto-generated method stub
		return session.selectOne(NAMESPACE + ".countProductList", ctgyCd);
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
	public List<ProductVO> getAllProductList(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return session.selectList(NAMESPACE + ".getAllProductList", cri);
	}

	@Override
	public int countAllProductList(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return session.selectOne(NAMESPACE + ".countAllProductList", cri);
	}

}
