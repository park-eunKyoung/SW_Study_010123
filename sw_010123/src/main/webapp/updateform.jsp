<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="./header.jsp"></jsp:include>
<jsp:include page="./nav.jsp"></jsp:include>
<section>
<form action="./memberupdate">
			회원번호
			<input type="text" name="custno" readonly value="${member.custno}">
			<br>
			회원성명
			<input type="text" name="custname" value="${member.custname}">
			<br>
			회원전화
			<input type="text" name="phone" value="${member.phone}">
			<br>
			회원주소
			<input type="text" name="address" value="${member.address}">
			<br>
			가입일자
			<input type="text" name="joindate" value="${member.joindate}">
			<br>
			고객등급
			<input type="text" name="grade" value="${member.grade}">
			<br>
			도시코드
			<input type="text" name="city" value="${member.city}">
			<br>
			<input type="button" onclick="modify()" value="수정">
			<input type="button" onclick="memberlist()" value="조회">
		</form>
</section>
<script type="text/javascript">
	function modify(){
		alert('트릭> 회원정보 수정이 완료되었습니다.')
		document.forms[0].action="./memberupdate";
		document.forms[0].submit();
	}
	function memberlist(){
		location.href="./memberlist";
		// document.forms[0].action="./memberlist";
		// document.forms[0].submit(); 
	}
</script>
</body>
</html>