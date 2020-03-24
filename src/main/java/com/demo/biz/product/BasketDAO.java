package com.demo.biz.product;

import java.util.List;
import java.util.Map;

public interface BasketDAO {

	public List<BasketVO> getBaskets(String mbId);
	public void insertBasket(BasketVO vo);
	public BasketVO getBasket(BasketVO vo);
	public void deleteBasket(BasketVO vo);
	public void updateBasket(BasketVO vo);
	public void deleteBaskets(Map<String, Object> map);
}
