package com.example.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.BookVo;
import com.example.service.BookService;
import com.example.service.MySqlService;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/book/*")
@Log
public class BookController {
	
	@Autowired
	BookService bookService;
	@Autowired
	MySqlService mySqlService;

	@GetMapping("/bookMain")
	public String bookMain() {
		log.info("GET - 호출");
		return "book/bookMain";
	}
	
	
	@GetMapping("/iamport")
	public String iamport() {
		log.info("GET - iamport 호출");
		
		return "book/iamport";
	}
	
	@PostMapping("iamportAjax")
	@ResponseBody
	public int addBook(BookVo bookVo) {
		log.info("POST - addBook 호출");
		
		bookVo.setBookNum(mySqlService.getNextNum("book"));
//		bookVo.setPaidAt(bookService.getTimeStamp(bookVo.getPaidAt()));
		// paidAt API 콜백 값이 아닌 자바에서 처리
		bookVo.setPaidAt(new Timestamp(System.currentTimeMillis()));
		bookService.addBook(bookVo);
		
		return bookVo.getBookNum();
	}
	
	@GetMapping("/bookList")
	public String bookList(Model model, int num) {
		log.info("GET-bookList 호출");
		
		BookVo bookVo = bookService.getBookByNum(num);
		log.info("bookVo = " + bookVo.toString());
		model.addAttribute("bookVo", bookVo);
		
		return "book/bookList";
	}

}
