package com.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.domain.SearchVo;
import com.portfolio.mapper.SearchMapper;


@Service
public class SearchService {
	
	@Autowired
	private SearchMapper searchMapper;
	
	public int getCountSearch(String address) {
		int count = searchMapper.getCountSearch(address);
		return count;
	}
	
	public List<SearchVo> getNoticesBySearch(String address) {
		return searchMapper.getNoticesBySearch(address);
	}
	
}