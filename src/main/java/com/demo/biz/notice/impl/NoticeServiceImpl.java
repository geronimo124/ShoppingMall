package com.demo.biz.notice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.biz.common.SearchCriteria;
import com.demo.biz.notice.NoticeDAO;
import com.demo.biz.notice.NoticeService;
import com.demo.biz.notice.NoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService {

	private final NoticeDAO dao;
	
	@Autowired
	public NoticeServiceImpl(NoticeDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<NoticeVO> getNoticeList(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return dao.getNoticeList(cri);
	}

	@Override
	public int countNoticeList(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return dao.countNoticeList(cri);
	}

	@Override
	public void writeNotice(NoticeVO vo) {
		// TODO Auto-generated method stub
		dao.writeNotice(vo);
	}

	@Override
	public NoticeVO getNotice(Integer ntNo) {
		// TODO Auto-generated method stub
		return dao.getNotice(ntNo);
	}

	@Override
	public void deleteNotice(Integer ntNo) {
		// TODO Auto-generated method stub
		dao.deleteNotice(ntNo);
	}

	@Override
	public void modifyNotice(NoticeVO vo) {
		// TODO Auto-generated method stub
		dao.modifyNotice(vo);
	}
	
}
