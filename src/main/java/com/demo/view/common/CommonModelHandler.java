package com.demo.view.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.demo.biz.common.WebCrawler;
import com.demo.biz.product.ProductService;

/**
 * @ClassName : CommonModelHandler.java
 * @Description : 화면 좌측 탭 메뉴를 구성하는 핸들러 클래스
 * @Modification Information
 *
 *    수정일			수정자		수정내용
 *    -------		-------     -------------------
 *    2020. 4. 23.	전일배		최초생성
 *
 * @author 전일배
 * @since 2020. 4. 23.
 * @version
 * @see
 *
 */
@ControllerAdvice("com.demo.view")
public class CommonModelHandler {

	private final ProductService service;
	
	@Autowired
	public CommonModelHandler(ProductService service) {
		this.service = service;
	}
	
    /**
     * 상위 카테고리 목록을 구성한다.
     *
     * @param
     * @return
     */
	@ModelAttribute
	public void categoryList(Model model) throws Exception {
		model.addAttribute("mainCateList", service.getCategoryList(1));
	}

    /**
     * 오늘의 NAVER 패션 키워드 목록을 구성한다. 
     *
     * @param
     * @return
     */
	@ModelAttribute
	public void keywordList(Model model) throws Exception {
		model.addAttribute("keywordList", WebCrawler.crawling());
	}
}
