package com.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.java.Log;

@Controller // 클래스 안에서 @GetMapping 등의 애노테이션을 사용 가능
@Log
public class HomeController  {
	
	@GetMapping("/")
	public String indexBlank() {
		log.info("index() 호출됨");
		return "index";
	}
	
	@GetMapping("/index")
	public String index() {
		log.info("index() 호출됨");
		return "index";
	}
	
	@GetMapping("/writeForm")
	public String writeForm() {
		log.info("writeForm() 호출됨");
		return "/customerCenter/writeForm";
	}
	
	@GetMapping("/customerCenter")
	public String customerCenter() {
		log.info("customerCenter() 호출됨");
		return "/customerCenter/customerCenter";
	}

//	@GetMapping("/search")
//	public String search() {
//		log.info("search() 호출됨");
//		return "serach/search";
//	}
//	
//	@GetMapping("/result")
//	public String result() {
//		log.info("result() 호출됨");
//		return "serach/result";
//	}
	
//	@GetMapping("/company/welcome")
//	public void welcome() {
//		log.info("welcome() 호출됨");
////		return "company/welcome";
//		
//		// 리턴타입이 void면 애노테이션 url 요청경로를
//		// 실행할 jsp뷰 이름으로 사용함
//	}
//	
//	@GetMapping("/company/history")
//	public void history() {
//		log.info("history() 호출됨");
//	}

	
}
