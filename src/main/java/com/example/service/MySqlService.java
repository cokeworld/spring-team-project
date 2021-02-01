package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.MySqlMapper;

@Service
@Transactional
public class MySqlService {
	
	@Autowired
	MySqlMapper mySqlMapper;
	
	public int getNextNum(String tableName) {
		
		return mySqlMapper.getNextNum(tableName);
	}
}
