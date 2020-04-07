package com.demo.biz.notice.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.biz.common.SearchCriteria;
import com.demo.biz.notice.NoticeDAO;
import com.demo.biz.notice.NoticeVO;

@Repository
public class NoticeDAOImpl implements NoticeDAO {

	private final String NAMESPACE = "com.demo.mapper.NoticeMapper";
	
	private final SqlSession session;
	
	@Autowired
	public NoticeDAOImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<NoticeVO> getNoticeList(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return session.selectList(NAMESPACE + ".getNoticeList", cri);
	}

	@Override
	public int countNoticeList(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return session.selectOne(NAMESPACE + ".countNoticeList", cri);
	}

	@Override
	public void writeNotice(NoticeVO vo) {
		// TODO Auto-generated method stub
		session.insert(NAMESPACE + ".writeNotice", vo);
	}

	@Override
	public NoticeVO getNotice(Integer ntNo) {
		// TODO Auto-generated method stub
		return session.selectOne(NAMESPACE + ".readNotice", ntNo);
	}

	@Override
	public void deleteNotice(Integer ntNo) {
		// TODO Auto-generated method stub
		session.delete(NAMESPACE + ".deleteNotice", ntNo);
	}

	@Override
	public void modifyNotice(NoticeVO vo) {
		// TODO Auto-generated method stub
		session.update(NAMESPACE + ".modifyNotice", vo);
	}
}
