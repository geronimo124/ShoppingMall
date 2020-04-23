package com.demo.biz.common;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @ClassName : WebCrawler.java
 * @Description : 네이버 패션 인기검색어를 크롤링하기 위한 클래스
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
public class WebCrawler {

    /**
     * 네이버 데이터랩 사이트의 오늘 날짜 패션 인기검색어를 크롤링한다.
     *
     * @param
     * @return 인기검색어 리스트
     * @throws Exception
     */
	public static List<String> crawling() throws Exception {
		
		String url = "https://datalab.naver.com";
		Document doc = Jsoup.connect(url).get();
		Elements elm = doc.select(".title");
		
		String[] contents = elm.text().split(" ");
		List<String> resultList = new ArrayList<>();
		
		for(int i = 0; i < contents.length; i++) {
			
			if(contents[i].equals("{=keyword}")) {
				
				for(int j = i - 10; j < i; j++)
					resultList.add(contents[j]);
				
				break;
			}
		}
		
		return resultList;
	}
}
