package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.BookVo;
import com.example.mapper.BookMapper;

import lombok.extern.java.Log;

@Log
@Service
@Transactional
public class BookService {
	
	@Autowired
	private BookMapper bookMapper;
	
	public void addBook(BookVo bookVo) {
		log.info("Service-addBook bookVo = " + bookVo);
		bookMapper.addBook(bookVo);
	}
	
	public void outData(BookVo bookVo) {
		log.info("Service-outData bookVo = " + bookVo);
		//out.println(bookVo);
	}
	
	public BookVo getBookByNum(int num) {
		log.info("bookNum : " + num);
		log.info("bookVo : " + bookMapper.getBookByNum(num));
		return bookMapper.getBookByNum(num);
	}
}
