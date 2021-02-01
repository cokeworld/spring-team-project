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
	
//	public void setTimeStamp(BookVo bookVo) {
//	try {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
//		Date parseDate = (Date) dateFormat.parse(bookVo.getStrPaidAt());
//		Timestamp timeStamp = new Timestamp(parseDate.getTime());
//		bookVo.setPaidAt(timeStamp);
//	} catch (ParseException e) {
//		e.printStackTrace();
//	}
//}
}
