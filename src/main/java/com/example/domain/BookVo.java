package com.example.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookVo {
	
	private int bookNum;
	private String impUid;
	private String merchantUid;
	private String paidAmount;
	private String pgProvider;
	private String buyerName;
}



	
