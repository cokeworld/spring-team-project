package com.portfolio.domain;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class NoticeVo {
	
	private int num;
	private String id;
	private String subject;
	private String content;
	private int readcount;
	private Timestamp regDate;
	
}
