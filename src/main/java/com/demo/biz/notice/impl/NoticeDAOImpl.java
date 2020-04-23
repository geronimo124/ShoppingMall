package com.demo.biz.notice.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.biz.common.SearchCriteria;
import com.demo.biz.notice.NoticeDAO;
import com.demo.biz.notice.NoticeVO;

/**
 * @ClassName : NoticeDAOImpl.java
 * @Description : 공지사항 정보의 관리를 위한 데이터 접근 클래스
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
@Repository
public class NoticeDAOImpl implements NoticeDAO {

	private final String NAMESPACE = "com.demo.mapper.NoticeMapper";
	
	private final SqlSession session;
	
	@Autowired
	public NoticeDAOImpl(SqlSession session) {
		this.session = session;
	}

    /**
     * 검색된 공지사항 목록을 가져온다.
     *
     * @param SearchCriteria - 검색 정보
     * @return List<NoticeVO> - 검색된 공지사항 리스트
     */
	@Override
	public List<NoticeVO> getNoticeList(SearchCriteria cri) {
		return session.selectList(NAMESPACE + ".getNoticeList", cri);
	}

    /**
     * 검색된 공지사항의 총 개수를 가져온다.
     *
     * @param SearchCriteria - 검색 정보
     * @return 검색된 공지사항의 총 개수
     */
	@Override
	public int countNoticeList(SearchCriteria cri) {
		return session.selectOne(NAMESPACE + ".countNoticeList", cri);
	}

    /**
     * 관리자가 공지사항을 작성한다.
     *
     * @param NoticeVO - 공지사항 내용
     * @return
     */
	@Override
	public void writeNotice(NoticeVO vo) {
		session.insert(NAMESPACE + ".writeNotice", vo);
	}

    /**
     * 공지사항의 자세한 내용을 가져온다.
     *
     * @param ntNo - 공지사항 고유번호
     * @return NoticeVO - 공지사항 내용
     */
	@Override
	public NoticeVO getNotice(Integer ntNo) {
		return session.selectOne(NAMESPACE + ".readNotice", ntNo);
	}

    /**
     * 공지사항을 삭제한다.
     *
     * @param ntNo - 공지사항 고유번호
     * @return
     */
	@Override
	public void deleteNotice(Integer ntNo) {
		session.delete(NAMESPACE + ".deleteNotice", ntNo);
	}

    /**
     * 공지사항을 수정한다.
     *
     * @param NoticeVO - 수정된 공지사항 내용
     * @return
     */
	@Override
	public void modifyNotice(NoticeVO vo) {
		session.update(NAMESPACE + ".modifyNotice", vo);
	}
}
