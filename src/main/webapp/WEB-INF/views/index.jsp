<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.1.js" integrity="sha256-VuhDpmsr9xiKwvTIHfYWCIQ84US9WqZsLfR4P7qF6O8=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.10/dist/vue.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

<link href="/css/hwh.css" rel="stylesheet" type="text/css"  media="all">

</head>
<body>
<div id="wrap">

	<%-- header 영역 --%>
	<jsp:include page="/WEB-INF/views/hwh/header.jsp" />

	<div class="clear"></div>
	
	<section id="a">
		<div class="txt">
			Explore The Beautiful World
		</div>
		
	</section>
	
	<div class="clear"></div>

	<section id="d">
		<div class="abc">
			지금 바로 출발하세요!
			<br>
		</div>
	</section>
	
	<form action="/search/result" method="get">
		<div class="container2">
				<div class="search">
					<label>체크인</label>
					<input type="text" class="form-control" id="checkin" name="checkIn" readonly>
				</div>

				<div class="search">
					<label>체크아웃</label>
					<input type="text" class="form-control" id="checkout" name="checkOut" readonly>
				</div>

				<div class="search">
					<label>인원</label>
					<input type="number" class="form-control" name="headCount" min=1 required>
				</div>
				
				<div class="search">
					<label>검색</label>
					<select name="address" class="form-control" required>
						<option value="" disabled selected>지역을 선택해주세요</option>
						<option value="서울">서울</option>
						<option value="부산">부산</option>
						<option value="울산">울산</option>
					</select>
					<br><br>
					<input type="submit" value="검색시작" class="btn2">
				</div>
		</div>
	</form>	
	
	<section id="b">
		<div class="clear"></div>
		<div class="txt2">
			<h2>호텔 종류</h2>
		</div>
		
		<div class="row1">
			<div class="hotel">
				<img src="/images/p1.jpg">
				<div class="txt3">
					<h3>호텔</h3>
					<div class="txt4">
						<p>호텔 설명</p>
						<p>1</p><p>2</p><p>3</p>
					</div>
				</div>
			</div>
			<div class="hotel">
				<img src="/images/p2.jpg">
				<div class="txt3">
					<h3>게스트하우스</h3>
					<div class="txt4">
						<p>게스트하우스 설명</p>
						<p>1</p><p>2</p><p>3</p>
					</div>
				</div>
			</div>
			<div class="hotel">
				<img src="/images/p3.jpg">
				<div class="txt3">
					<h3>도미토리</h3>
					<div class="txt4">
						<p>도미토리 설명</p>
						<p>1</p><p>2</p><p>3</p>
					</div>
				</div>
			</div>
			<div class="hotel">
				<img src="/images/p4.jpg">
				<div class="txt3">
					<h3>쉐어하우스</h3>
					<div class="txt4">
						<p>쉐어하우스 설명</p>
						<p>1</p><p>2</p><p>3</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<section id="c">
		<div class="clear"></div>
		<div class="txt2">
			<h2>리뷰</h2>
		</div>
		
		<div class="row2">
			<div class="review">
				<img src="/images/re1.jpg">
				<div class="txt3">
					<h3>리뷰1</h3>
					<div class="txt4">
						<p>리뷰1 내용</p>
						<p>1</p><p>2</p><p>3</p>
					</div>
				</div>
			</div>
			<div class="review">
				<img src="/images/re2.jpg">
				<div class="txt3">
					<h3>리뷰2</h3>
					<div class="txt4">
						<p>리뷰2 내용</p>
						<p>1</p><p>2</p><p>3</p>
					</div>
				</div>
			</div>
			<div class="review">
				<img src="/images/re1.jpg">
				<div class="txt3">
					<h3>리뷰3</h3>
					<div class="txt4">
						<p>리뷰3 내용</p>
						<p>1</p><p>2</p><p>3</p>
					</div>
				</div>
			</div>
		</div>
		
	</section>
	
	<div class="clear"></div>
	<%-- footer 영역 --%>
	<jsp:include page="/WEB-INF/views/hwh/footer.jsp" />
	
</div>

<script>
	var rangeDate = 31; // set limit day
	var setSdate, setEdate;
	$("#checkin").datepicker({
		dateFormat : 'yy-mm-dd',
		minDate : 0,
		onSelect : function(selectDate) {
			var stxt = selectDate.split("-");
			stxt[1] = stxt[1] - 1;
			var sdate = new Date(stxt[0], stxt[1], stxt[2]);
			var edate = new Date(stxt[0], stxt[1], stxt[2]);
			edate.setDate(sdate.getDate() + rangeDate);

			$('#checkout').datepicker('option', {
				minDate : selectDate,
				beforeShow : function() {
					$("#checkout").datepicker("option", "maxDate", edate);
					setSdate = selectDate;
					console.log(setSdate)
				}
			});
			//checkout 설정
		}
	//checkin 선택되었을 때
	});

	$("#checkout").datepicker({
		dateFormat : 'yy-mm-dd',
		onSelect : function(selectDate) {
			setEdate = selectDate;
			console.log(setEdate)
		}
	});
	$('.btn2').on('click', function(e) {
		if ($('input#checkin').val() == '') {
			alert('시작일을 선택해주세요.');
			$('input#checkin').focus();
			return false;
		} else if ($('input#checkout').val() == '') {
			alert('종료일을 선택해주세요.');
			$('input#checkout').focus();
			return false;
		}

		var t1 = $('input#checkin').val().split("-");
		var t2 = $('input#checkout').val().split("-");
		var t1date = new Date(t1[0], t1[1], t1[2]);
		var t2date = new Date(t2[0], t2[1], t2[2]);
		var diff = t2date - t1date;
		var currDay = 24 * 60 * 60 * 1000;
		if (parseInt(diff / currDay) > rangeDate) {
			alert('로그 조회 기간은 ' + rangeDate + '일을 초과할 수 없습니다.');
			return false;
		}
	});
	//조회 버튼 클릭
</script>

</body>
</html>


