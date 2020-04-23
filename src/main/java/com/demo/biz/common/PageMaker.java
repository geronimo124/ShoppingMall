package com.demo.biz.common;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName : PageMaker.java
 * @Description : 페이징을 위한 페이지 인덱스 클래스
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
@Getter
@Setter
@ToString
public class PageMaker {

	/**
	 * 항목의 총 개수
	 */
	private int totalCount;
	/**
	 * 시작 페이지
	 */
	private int startPage;
	/**
	 * 끝 페이지
	 */
	private int endPage;
	/**
	 * 이전 페이지 플래그
	 */
	private boolean prev;
	/**
	 * 다음 페이지 플래그
	 */
	private boolean next;
	/**
	 * 페이지 인덱스 개수
	 */
	private int displayPageNum = 10;
	/**
	 * 페이지 정보
	 */
	private Criteria cri;

    /**
     * 항목의 총 개수를 초기화 한 후, 다른 필드의 값을 계산한다.
     *
     * @param totalCount - 항목의 총 개수
     * @return
     */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		calcData();
	}

    /**
     * 필드의 값을 계산한다.
     *
     * @param
     * @return
     */
	private void calcData() {
		
		endPage = (int) (Math.ceil(cri.getPage() / (double)displayPageNum ) * displayPageNum);
		
		startPage = (endPage - displayPageNum) + 1;
		
		int tempEndPage = (int)(Math.ceil(totalCount / (double)cri.getPerPageNum()));
		
		if(endPage > tempEndPage){
			endPage = tempEndPage;
		}
		
		prev = startPage ==1 ? false : true;
		
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
		
	}

    /**
     * 페이징 쿼리를 생성 후 반환한다.
     *
     * @param page - 목적 페이지
     * @return 완성된 페이징 쿼리
     */
	public String makeQuery(int page){
		
		UriComponents uriComponents =
	            UriComponentsBuilder.newInstance()
	            .queryParam("page", page)
	            .queryParam("perPageNum", cri.getPerPageNum())
	            .build();	            
		
		return uriComponents.toUriString();
	}

    /**
     * 검색 페이징 쿼리를 생성 후 반환한다.
     *
     * @param page - 목적 페이지
     * @return 완성된 검색 페이징 쿼리
     */
	public String makeSearch(int page){
		
		UriComponents uriComponents =
	            UriComponentsBuilder.newInstance()
	            .queryParam("page", page)
	            .queryParam("perPageNum", cri.getPerPageNum())
	            .queryParam("searchType", ((SearchCriteria)cri).getSearchType())
	            .queryParam("keyword", ((SearchCriteria)cri).getKeyword())
	            .build();	            
		
		return uriComponents.toUriString();
	}	
	
}
