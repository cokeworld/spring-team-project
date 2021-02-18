package com.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.domain.HostVo;
import com.portfolio.mapper.SearchMapper;


@Service
public class SearchService {
	
	@Autowired
	private SearchMapper searchMapper;
	
	public int getCountSearch(String address1) {
		int count = searchMapper.getCountSearch(address1);
		return count;
	}
	
	public List<HostVo> getNoticesBySearch(String address1) {
		return searchMapper.getNoticesBySearch(address1);
	}
	
}