package com.demo.biz.product;

import java.util.List;
import java.util.Map;

public interface BasketService {

	public List<BasketVO> getBaskets(String mbId);
	public boolean insertBasket(BasketVO vo);
	public void deleteBasket(BasketVO vo);
	public void updateBasket(BasketVO vo);
	public void deleteBaskets(Map<String, Object> map);
	public void updateBaskets(List<BasketVO> basketList);
}
