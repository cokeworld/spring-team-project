package com.portfolio.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.portfolio.domain.NoticeVo;


public interface NoticeMapper {

//	@Insert("INSERT INTO notice (id, subject, content, readcount, reg_date, ip, re_ref, re_lev, re_seq) "
//			+ "VALUES (#{id}, #{subject}, #{content}, #{readcount}, #{regDate}, #{ip}, #{reRef}, #{reLev}, #{reSeq})")
	void addNotice(NoticeVo noticeVo);
	
	@Select("SELECT * FROM airbnb_notice WHERE num = #{num}")
	NoticeVo getNoticeByNum(int num);
	
	void updateReadcount(int num);
	
//	@Select("SELECT COUNT(*) FROM airbnb_notice")
	int getCountAll();
	
	
	List<NoticeVo> getNotices(@Param("startRow") int startRow, @Param("pageSize") int pageSize);
	
	
	void updateBoard(NoticeVo noticeVo);
	
	@Delete("DELETE FROM airbnb_notice WHERE num = #{num}")
	void deleteNoticeByNum(int num);
	
	@Delete("DELETE FROM airbnb_notice")
	void deleteAll();
	
	
	List<NoticeVo> getNoticesByNums(@Param("numList") List<Integer> numList);
	
	
}






