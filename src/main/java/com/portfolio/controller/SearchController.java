package com.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.portfolio.domain.PageDto;
import com.portfolio.domain.SearchVo;
import com.portfolio.service.SearchService;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/search/*")
@Log
public class SearchController {

	@Autowired
	private SearchService searchService;

	@GetMapping("/result")
	public String result(
			@RequestParam(defaultValue = "1") int pageNum, 
			@ModelAttribute("address") String address,
			@ModelAttribute("checkIn") String checkIn, 
			@ModelAttribute("checkOut") String checkOut, 
			@ModelAttribute("headCount") int headCount, 
			Model model) {

		int count = searchService.getCountSearch(address);
		
		int pageSize = 5;
		
		int startRow = (pageNum - 1) * pageSize; 

		log.info("count : " + count);
		
		List<SearchVo> searchList = null;
		if (count > 0) {
			searchList = searchService.getNoticesBySearch(address);
		}

		// 페이지
		PageDto pageDto = new PageDto();
		
		if (count > 0) {
			int pageCount = (count / pageSize) + (count % pageSize == 0 ? 0 : 1);
			
			pageDto.setCount(count);
		}
		
		model.addAttribute("pageDto", pageDto);
		model.addAttribute("count", count);
		model.addAttribute("searchList", searchList);
		
		return "search/result";
	} // result
}