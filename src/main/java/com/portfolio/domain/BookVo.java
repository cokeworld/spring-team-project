package com.portfolio.domain;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookVo {
	
	private int bookNum;
	
	private String checkIn;
	private String checkOut;
	private int nights;
	private int expectedCost;
	private int cntOfPerson;
	private int noNum;
	private Timestamp regDate;
	
	private String impUid;
	private String merchantUid;
	private String paidAmount;
	private String pgProvider;
	private String buyerName;
	private Timestamp paidAt;
}