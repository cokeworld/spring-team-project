package com.example.mapper;

import org.apache.ibatis.annotations.Param;

import com.example.domain.BookVo;

public interface BookMapper {
	
	public void addBook(BookVo bookVo);
	
	public BookVo getBookByNum(@Param("num") int num);
}
