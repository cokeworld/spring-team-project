<%@page import="com.example.domain.BookVo"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String imp_uid = request.getParameter("imp_uid");
String merchant_uid = request.getParameter("merchant_uid");
String paid_amount = request.getParameter("paid_amount");
String pg_provider = request.getParameter("pg_provider");
String buyer_name = request.getParameter("buyer_name");
String apply_num = request.getParameter("apply_num");

BookVo vo = new BookVo();

vo.setImp_uid(imp_uid);
vo.setMerchant_uid(merchant_uid);
vo.setPaid_amount(paid_amount);
vo.setPg_provider(pg_provider);
vo.setBuyer_name(buyer_name);
vo.setApply_num(apply_num);

System.out.println("vo : " + vo);

out.println(vo);
%>