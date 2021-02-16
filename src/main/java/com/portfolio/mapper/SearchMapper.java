package com.portfolio.mapper;

import java.util.List;
import com.portfolio.domain.SearchVo;

public interface SearchMapper {
	
	// 검색한 글 갯수
	public int getCountSearch(String address);

	// 검색한 글 목록
	public List<SearchVo> getNoticesBySearch(String address);
	
}