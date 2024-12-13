<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
if('${msg}'!=''){
	alert('${msg}')
}
</script>
<body>
	<jsp:include page="./header.jsp"></jsp:include>
	<jsp:include page="./nav.jsp"></jsp:include>
	<h1>joinform.jsp 쇼핑몰 회원등록</h1>
	<section>
	<form name = "joinform" action="./memberjoin" method="get">
		회원번호(자동발생) 
		<input type="text" name="custno" readonly value="${custno}">
		<br>
		회원성명 <input type="text" name="custname">
		<br>
		회원전화 <input type="text" name="phone">
		<br>
		회원주소 <input type="text" name="address">
		<br>
		가입일자 <input type="text" name="joindate">
		<br>
		고객등급 [A:VIP, B:일반, C:직원] 
		<input type="text" name="grade">
		<br>
		도시코드
		<input type="text" name="city">
		<br>
		<input type="button" value="등록" onclick="check()">
		<input	type="button" value="조회" onclick="move()">
	</form>
	</section>
	<script type="text/javascript">
		function move(){
			location.href="./memberlist"
		}
	
		function check() {
			const frm=document.joinform;
			//const frm=document.forms[0];
			console.log(frm);
			const len = frm.length-2;
			for(let i=1;i<len;i++){
				if(frm[i].value===''||frm[i].value===null){
					alert(frm[i].name+'가 입력되지 않았습니다.');
					frm[i].focus();
					return false;
				}
			}
			//alert('트릭>회원등록이 성공하였습니다.')
			frm.submit();
		}
	</script>
</body>
</html>