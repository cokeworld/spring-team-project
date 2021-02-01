package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

	
	@GetMapping("/iamport")
	public String iamport() {
		log.info("GET - iamport 호출");
		return "book/iamport";
	}
	
	@PostMapping("/bookAjax")
	public void addBook(BookVo bookVo) {
		log.info("POST - addBook 호출");
		bookService.addBook(bookVo);
		bookService.outData(bookVo);
	}
	
	@GetMapping("/bookList")
	public String bookList(Model model, int num) {
		log.info("GET-bookList 호출");
		
		// bookVo에 값이 안들어 오는 문제~~~~
		BookVo bookVo = bookService.getBookByNum(num);
		log.info("bookVo = " + bookVo.toString());
		model.addAttribute("bookVo", bookVo);
		
		return "book/bookList";
	}

}
