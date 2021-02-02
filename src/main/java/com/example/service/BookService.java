package com.example.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
	
	public BookVo getBookByNum(int num) {
		log.info("bookNum : " + num);
		log.info("bookVo : " + bookMapper.getBookByNum(num));
		return bookMapper.getBookByNum(num);
	}
	
//	public Timestamp getTimeStamp(String paidAt) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		Timestamp timeStamp = new Timestamp(Long.parseLong(paidAt));
////		String strPaidAt =  dateFormat.format(timeStamp);
//		log.info("getTimeStamp");
//		
//		return timeStamp;
//	}
}
