package com.demo.biz.common;

import lombok.Getter;
import lombok.ToString;

/**
 * @ClassName : Criteria.java
 * @Description : 페이징을 위한 페이지 정보 클래스
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
@ToString
public class Criteria {

	private int page;	
	private int perPageNum;
	
	public Criteria(){
		this.page = 1;
		this.perPageNum = 6;
	}

    /**
     * 현재 페이지를 설정한다.
     *
     * @param page - 현재 페이지
     * @return
     */
	public void setPage(int page){
		
		if(page <= 0){
			this.page = 1;
			return;
		}
		
		this.page = page;
	}
	
    /**
     * 페이지당 항목 개수를 설정한다.
     *
     * @param perPageNum - 페이지당 항목 개수
     * @return
     */
	public void setPerPageNum(int perPageNum){
		
		if(perPageNum <= 0 || perPageNum > 100){
			this.perPageNum = 10;
			return;
		}
		
		this.perPageNum = perPageNum;
	}
	
    /**
     * Mapper에서 필요한 메소드로, 시작 페이지를 반환한다.
     *
     * @param
     * @return 시작 페이지
     */	
	public int getPageStart() {
		
		return (this.page -1)* perPageNum + 1;
		
	}
}


