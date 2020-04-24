package com.demo.biz.notice;

import java.util.List;

import com.demo.biz.common.SearchCriteria;

/**
 * @ClassName : NoticeDAO.java
 * @Description : 공지사항 정보의 관리를 위한 데이터 접근 인터페이스
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
public interface NoticeDAO {

    /**
     * 검색된 공지사항 목록을 가져온다.
     *
     * @param SearchCriteria 검색 정보
     * @return List - 검색된 공지사항 리스트
     */
	public List<NoticeVO> getNoticeList(SearchCriteria cri);
	
    /**
     * 검색된 공지사항의 총 개수를 가져온다.
     *
     * @param SearchCriteria 검색 정보
     * @return 검색된 공지사항의 총 개수
     */
	public int countNoticeList(SearchCriteria cri);
	
    /**
     * 관리자가 공지사항을 작성한다.
     *
     * @param NoticeVO 공지사항 내용
     * @return
     */
	public void writeNotice(NoticeVO vo);
	
    /**
     * 공지사항의 자세한 내용을 가져온다.
     *
     * @param ntNo 공지사항 고유번호
     * @return NoticeVO - 공지사항 내용
     */
	public NoticeVO getNotice(Integer ntNo);
	
    /**
     * 공지사항을 삭제한다.
     *
     * @param ntNo 공지사항 고유번호
     * @return
     */
	public void deleteNotice(Integer ntNo);
	
    /**
     * 공지사항을 수정한다.
     *
     * @param NoticeVO 수정된 공지사항 내용
     * @return
     */
	public void modifyNotice(NoticeVO vo);

}
