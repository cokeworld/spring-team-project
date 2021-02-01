<%@page import="com.example.domain.BookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <title>예약정보-bookList</title>
</head>

<body>
    <div class="app verticality">
        <div class="horizontal">
            <a href=""><  </a>
            <h1>예약정보</h1>
        </div>

        <div class="verticality">

            <div class="verticality">
                 <h2>OOO(호스트) 님의 숙소</h2>
                 <div class="horizontal">
                    <div class="verticality">
                         <div>체크인</div>
                         <div>2021년 2월 1일</div>
                         <div>오후 2:00</div>
                    </div>
                    <div class="verticality">
                        <div>체크아웃</div>
                        <div>2021년 2월 2일</div>
                        <div>오후 12:00</div>
                    </div>
                 </div>
            </div>

            <div class="verticality">
                <h2>결제정보</h2>
                <div class="verticality">
                    <table>
                        <tr>
                            <th>결제금액</th>
                            <td>${ bookVo.paidAmount }</td>
                        </tr>
                        <tr>
                            <th>결제수단</th>
                            <td>${ bookVo.pgProvider }</td>
                        </tr>
                        <tr>
                            <th>결제자이름</th>
                            <td>${ bookVo.buyerName }</td>
                        </tr>
                        <tr>
                            <th>기타 여러 내역 가져올 수 있음.</th>
                            <td>결제일시, 구매자아이디, 판매자아이디, 결제번호 등등.</td>
                        </tr>
                    </table>
                </div>
            </div>

        </div>


    </div>
</body>
</html>