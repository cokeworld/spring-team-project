package com.portfolio.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portfolio.domain.NoticeVo;
import com.portfolio.domain.PageDto;
import com.portfolio.service.MysqlService;
import com.portfolio.service.NoticeService;


@Controller
@RequestMapping("/customerCenter/*")
public class NoticeController {

	@Autowired
	private NoticeService noticeService2;
	
	@Autowired
	private MysqlService mySqlService;


	@GetMapping("/list")
	public String list(
			@RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "") String category,
			@RequestParam(defaultValue = "") String search,
			Model model) {
		
		int count = noticeService2.getCountAll();
		//int count = noticeService2.getCountBySearch(category, search);
		
		
		int pageSize = 9;
		
		int startRow = (pageNum - 1) * pageSize;		
		
		List<NoticeVo> noticeList = null;
		if (count > 0) {
			noticeList = noticeService2.getNotices(startRow, pageSize);
			//noticeList = noticeService2.getNoticesBySearch(startRow, pageSize, category, search);
		}
		
		//줄바꿈 처리
		String content = "";
		for(NoticeVo noticeVo2 : noticeList) {
			if (noticeVo2.getContent() != null) {
				content = noticeVo2.getContent().replace("\n", "<br>");
				noticeVo2.setContent(content);
			}
		}
		
		PageDto pageDto = new PageDto();
		
		if (count > 0) {
			int pageCount = (count / pageSize) + (count % pageSize == 0 ? 0 : 1);
			//int pageCount = (int) Math.ceil((double) count / pageSize);
			
			int pageBlock = 5;
			
			// 1~5          6~10          11~15          16~20       ...
			// 1~5 => 1     6~10 => 6     11~15 => 11    16~20 => 16
			int startPage = ((pageNum / pageBlock) - (pageNum % pageBlock == 0 ? 1 : 0)) * pageBlock + 1;
			
			int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount) {
				endPage = pageCount;
			}
			
			pageDto.setCategory(category);
			pageDto.setSearch(search);
			pageDto.setCount(count);
			pageDto.setPageCount(pageCount);
			pageDto.setPageBlock(pageBlock);
			pageDto.setStartPage(startPage);
			pageDto.setEndPage(endPage);
		} // if
		
		
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("pageDto", pageDto);
		model.addAttribute("pageNum", pageNum);
		
		return "hwh/customerCenter";
	} // list
	
	
	@GetMapping("/write")
	public String write(@ModelAttribute("pageNum") String pageNum, HttpSession session, Model model) {
		
		// 로그인 했을때는 글쓰기 화면으로 보여줌
		return "hwh/writeForm";
	} // Get - write
	
	
	@PostMapping("/write")
	public String write(String pageNum, NoticeVo noticeVo2,
			HttpSession session, HttpServletRequest request) {
		
		// 클라이언트 IP주소, 등록날짜, 조회수 값 설정
		noticeVo2.setRegDate(new Timestamp(System.currentTimeMillis()));
		noticeVo2.setReadcount(0); 
		
		int num = mySqlService.getNextNum("notice2");
		
		// 주글쓰기
		noticeService2.addNotice(noticeVo2);
		
		return "redirect:/customerCenter/content?num=" + num + "&pageNum=" + pageNum;
	} // Post - write
	
	
	@GetMapping("/content")
	public String content(int num, String pageNum, Model model) {
		// 상세보기 대상 글의 조회수 1 증가
		noticeService2.updateReadcount(num);
		
		// 상세보기 대상 글내용 VO로 가져오기
		NoticeVo noticeVo2 = noticeService2.getNoticeByNum(num);
		
		String content = "";
		if (noticeVo2.getContent() != null) {
			content = noticeVo2.getContent().replace("\n", "<br>");
			noticeVo2.setContent(content);
		}
		
		model.addAttribute("noticeVo2", noticeVo2);
		model.addAttribute("pageNum", pageNum);
		
		return "hwh/customerCenterContent";
	} // content
	
	
	@GetMapping("/delete")
	public String delete(int num, String pageNum, RedirectAttributes rttr) {
		// 글번호에 해당하는 글 한개 삭제하기
		noticeService2.deleteNoticeByNum(num);
		
		// 글목록 페이지로 리다이렉트 이동시키기
		rttr.addAttribute("pageNum", pageNum);
		
		return "redirect:/customerCenter/list";
		//return "redirect:/notice/list?pageNum=" + pageNum;
	} // delete
	
	
	@GetMapping("/modify")
	public String modify(int num, @ModelAttribute("pageNum") String pageNum, Model model) {
		// 글번호 num에 해당하는 글내용 VO로 가져오기
		NoticeVo noticeVo2 = noticeService2.getNoticeByNum(num);
		
		model.addAttribute("noticeVo2", noticeVo2);
		//model.addAttribute("pageNum", pageNum);
		
		return "hwh/modifyForm";
	} // GET - modify
	
	
	@PostMapping("/modify")
	public String modify(NoticeVo noticeVo2, String pageNum, RedirectAttributes rttr) {
		
		noticeService2.updateBoard(noticeVo2);
		
		rttr.addAttribute("num", noticeVo2.getNum());
		rttr.addAttribute("pageNum", pageNum);
		
		// 수정된 글의 상세보기 화면으로 리다이렉트 이동
		return "redirect:/customerCenter/content";
	} // POST - modify
	
	
}







