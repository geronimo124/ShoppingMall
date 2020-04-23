package com.demo.biz.notice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.biz.common.SearchCriteria;
import com.demo.biz.notice.NoticeDAO;
import com.demo.biz.notice.NoticeService;
import com.demo.biz.notice.NoticeVO;

/**
 * @ClassName : NoticeServiceImpl.java
 * @Description : 공지사항 정보의 관리를 위한 서비스 클래스
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
@Service
public class NoticeServiceImpl implements NoticeService {

	private final NoticeDAO dao;
	
	@Autowired
	public NoticeServiceImpl(NoticeDAO dao) {
		this.dao = dao;
	}

    /**
     * 검색된 공지사항 목록을 가져온다.
     *
     * @param SearchCriteria - 검색 정보
     * @return List<NoticeVO> - 검색된 공지사항 리스트
     */
	@Override
	public List<NoticeVO> getNoticeList(SearchCriteria cri) {
		return dao.getNoticeList(cri);
	}

    /**
     * 검색된 공지사항의 총 개수를 가져온다.
     *
     * @param SearchCriteria - 검색 정보
     * @return 검색된 공지사항의 총 개수
     */
	@Override
	public int countNoticeList(SearchCriteria cri) {
		return dao.countNoticeList(cri);
	}

    /**
     * 관리자가 공지사항을 작성한다.
     *
     * @param NoticeVO - 공지사항 내용
     * @return
     */
	@Override
	public void writeNotice(NoticeVO vo) {
		dao.writeNotice(vo);
	}

    /**
     * 공지사항의 자세한 내용을 가져온다.
     *
     * @param ntNo - 공지사항 고유번호
     * @return NoticeVO - 공지사항 내용
     */
	@Override
	public NoticeVO getNotice(Integer ntNo) {
		return dao.getNotice(ntNo);
	}

    /**
     * 공지사항을 삭제한다.
     *
     * @param ntNo - 공지사항 고유번호
     * @return
     */
	@Override
	public void deleteNotice(Integer ntNo) {
		dao.deleteNotice(ntNo);
	}

    /**
     * 공지사항을 수정한다.
     *
     * @param NoticeVO - 수정된 공지사항 내용
     * @return
     */
	@Override
	public void modifyNotice(NoticeVO vo) {
		dao.modifyNotice(vo);
	}
	
}
