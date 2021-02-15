<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <h1>예약정보</h1>
        </div>

        <div class="verticality">

            <div class="verticality">
                 <h2>OOO(호스트) 님의 숙소</h2>
                 <div class="horizontal">
                    <div class="verticality">
                         <div>체크인</div>
                         <div>${ bookVo.checkIn }</div>
                         <div>오후 2:00</div>
                    </div>
                    <div class="verticality">
                        <div>체크아웃</div>
                        <div>${ bookVo.checkOut }</div>
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
                            <td><fmt:formatNumber value="${ bookVo.paidAmount }" pattern="#,###"/>원</td>
                            
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
                            <th>결제시간</th>
                            <td><fmt:formatDate value="${ bookVo.paidAt }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="verticality">
                <h2>찾아가는 방법</h2>
                <div class="verticality">
                    <div>
                        <div>주소</div>
                        <div>${ hostVo.address1 } ${ hostVo.address2 }</div>
                    </div>
                    <!-- <div><a href="">주소 복사하기</a></div> -->
                </div>
            </div>

            
            <div class="verticality">
                <h2>숙소</h2>
                <div class="verticality">
                    <div>
                        <a href="/content/info?num=${ hostVo.num }">숙소보기</a>
                    </div>
                </div>
            </div>


            <div class="verticality">
                <h2>에어비앤비 지원</h2>
                <div class="verticality">
                    <div><a href="">도움말 센터</a></div>
                    <div><a href="">호스트에게 전화하기</a></div>
                </div>
            </div>
        </div>


    </div>
</body>
</html>