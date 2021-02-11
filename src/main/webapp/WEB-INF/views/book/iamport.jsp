<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>결제</title>
</head>

<!-- 결제 API CDN -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

<body>
    <script>
        var IMP = window.IMP; // 생략가능
        IMP.init('imp00454002'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용

		// name, amount 구현 계획(코드 합치는 단계에서는)
        // buyer관련은 로그인 구현 완료후 값을 입력한다.
        IMP.request_pay({
            pg : `${ bookVo.pgProvider }`,
            pay_method : 'card',
            merchant_uid : 'merchant_' + new Date().getTime(),
            name : `${ hostVo.title }`,
            amount : `${ bookVo.expectedCost }`,
            buyer_email : 'iamport@siot.do',
            buyer_name : '김두한',
            buyer_tel : '010-1234-5678',
            buyer_addr : '서울특별시 강남구 삼성동',
            buyer_postcode : '123-456',
            m_redirect_url : 'https://www.yourdomain.com/payments/complete' 
            // 모바일 결제시, 결제가 끝나고 랜딩되는 URL을 지정 (카카오페이, 페이코, 다날의 경우는 필요없음. PC와 마찬가지로 callback함수로 결과가 떨어짐)
        }, function(rsp) {
            if ( rsp.success ) {
				let rqsData = { 
						impUid: rsp.imp_uid,
		        		merchantUid: rsp.merchant_uid,
		        		paidAmount: rsp.paid_amount,
		        		pgProvider: rsp.pg_provider,
		        		buyerName: rsp.buyer_name,
		        		// paidAt: rsp.paid_at
				}
                $.ajax({
                    url: "iamportAjax",
                	type: 'POST',
//                 	dataType: 'json',
                	data: rqsData,
                   success: function(data) {
                       	alert('결제완료');
                  		location.href = '/book/bookList?num=' + data
                     	},
                   	error:function(jqXHR, textStatus, errorThrown) {
       			  		console.log('데이터 전송 에러');
       		        }
              	});

            } else {
                var msg = '결제에 실패하였습니다.';
                msg += '에러내용 : ' + rsp.error_msg;
                alert(msg);
            }
        });
    </script>
</body>
</html>