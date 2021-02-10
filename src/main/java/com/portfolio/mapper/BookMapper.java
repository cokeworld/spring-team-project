package com.portfolio.mapper;

import org.apache.ibatis.annotations.Param;

import com.portfolio.domain.BookVo;

public interface BookMapper {
	
	public void addBook(BookVo bookVo);
	
	public BookVo getBookByNum(@Param("num") int num);
}
