package com.portfolio.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.domain.BookVo;
import com.portfolio.domain.HostVo;
import com.portfolio.domain.ImagesVo;
import com.portfolio.domain.ReviewVo;
import com.portfolio.service.BookService;
import com.portfolio.service.HostService;
import com.portfolio.service.MysqlService;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/book/*")
@Log
public class BookController {
	
	@Autowired
	BookService bookService;
	@Autowired
	HostService hostService;
	@Autowired
	MysqlService mysqlService;

	@GetMapping("/bookMain")
	public String bookMain(BookVo bookVo, Model model) {
		log.info("GET - bookMain 호출");
		
		//content.jsp에서 받아온 결제 데이터 처리
		model.addAttribute("bookVo", bookVo);
		int num = bookVo.getNoNum();
		
		//hostData 받아오기
		HostVo hostVo = hostService.getHostVo(num);
		model.addAttribute("hostVo", hostVo);
		
		//image, count, score 데이터 받아오기
		Map<String, Object> contentInfo = hostService.getContentInfo(num);
		
		List<ImagesVo> imageList = (List<ImagesVo>) contentInfo.get("imageList");
		
		int count = (int) contentInfo.get("count");
		Double score = (Double) contentInfo.get("score");
		score = Double.isNaN(score) ? 0.0 : score;
		
		model.addAttribute("imageList", imageList);
		model.addAttribute("count", count);
		model.addAttribute("score", score);
		return "book/bookMain";
	}
	
	
	@GetMapping("/iamport")
	public String iamport(BookVo bookVo, Model model) {
		log.info("GET - iamport 호출");
		model.addAttribute("bookVo", bookVo);
		
		HostVo hostVo = hostService.getHostVo(bookVo.getNoNum());
		model.addAttribute("hostVo", hostVo);
		return "book/iamport";
	}
	
	@PostMapping("iamportAjax")
	@ResponseBody
	public int addBook(BookVo bookVo) {
		log.info("POST - addBook 호출");
		
		bookVo.setBookNum(mysqlService.getNextNum("book"));
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
