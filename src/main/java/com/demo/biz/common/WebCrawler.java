package com.demo.biz.common;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WebCrawler {

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
