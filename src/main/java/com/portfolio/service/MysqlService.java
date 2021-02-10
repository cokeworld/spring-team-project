package com.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.mapper.MysqlMapper;

@Service
@Transactional
public class MysqlService {
	
	@Autowired
	MysqlMapper mysqlMapper;
	
	public int getNextNum(String tableName) {
		
		return mysqlMapper.getNextNum(tableName);
	}
}
