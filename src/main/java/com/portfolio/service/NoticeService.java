package com.portfolio.service;

import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.domain.NoticeVo;
import com.portfolio.mapper.NoticeMapper;


@Service
public class NoticeService {
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	
	// 주글쓰기
	public void addNotice(NoticeVo noticeVo) {
		noticeMapper.addNotice(noticeVo);
	}
	
	
	public NoticeVo getNoticeByNum(int num) {
		NoticeVo noticeVo = noticeMapper.getNoticeByNum(num);
		return noticeVo;
	}
	
	
	
	public void updateReadcount(int num) {
		noticeMapper.updateReadcount(num);
	}
	
	
	
	public int getCountAll() {
		int count = noticeMapper.getCountAll();
		return count;
	}
	
	
	public List<NoticeVo> getNotices(int startRow, int pageSize) {
		List<NoticeVo> list = noticeMapper.getNotices(startRow, pageSize);
		return list;
	}
	
	
	public void updateBoard(NoticeVo noticeVo) {
		noticeMapper.updateBoard(noticeVo);
	}
	
	public void deleteNoticeByNum(int num) {
		noticeMapper.deleteNoticeByNum(num);
	}
	
	public void deleteAll() {
		noticeMapper.deleteAll();
	}
	
	//public List<NoticeVo> getNoticesByNums(List<Integer> numList)
	public List<NoticeVo> getNoticesByNums(Integer... numArr) {
		
		List<Integer> numList = Arrays.asList(numArr);
		
		return noticeMapper.getNoticesByNums(numList);
	}
	

	
}





