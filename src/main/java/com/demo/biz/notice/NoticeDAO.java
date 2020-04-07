package com.demo.biz.notice;

import java.util.List;

import com.demo.biz.common.SearchCriteria;

public interface NoticeDAO {

	public List<NoticeVO> getNoticeList(SearchCriteria cri);
	public int countNoticeList(SearchCriteria cri);
	public void writeNotice(NoticeVO vo);
	public NoticeVO getNotice(Integer ntNo);
	public void deleteNotice(Integer ntNo);
	public void modifyNotice(NoticeVO vo);

}
