package com.portfolio.mapper;

import java.util.List;

import com.portfolio.domain.HostVo;

public interface SearchMapper {
	
	// 검색한 글 갯수
	public int getCountSearch(String address1);

	// 검색한 글 목록
	public List<HostVo> getNoticesBySearch(String address1);
	
}