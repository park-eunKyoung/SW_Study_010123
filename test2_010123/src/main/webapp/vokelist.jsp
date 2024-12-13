<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header_nav.jsp"></jsp:include>
	<section>
	<h1 align="center">투표검수조회</h1>
		<table border=1>
			<tr>
				<th>성명</th>
				<th>생년월일</th>
				<th>나이</th>
				<th>성별</th>
				<th>후보번호</th>
				<th>투표시간</th>
				<th>유권자확인</th>
			</tr>
			<c:forEach var="vokelist" items="${vList}">
				<tr>
				<th>${vokelist.v_name}</th>
				<th>${vokelist.v_jumin}</th>
				<th>${vokelist.v_age}</th>
				<th>${vokelist.v_gender}</th>
				<th>${vokelist.m_no}</th>
				<th>${vokelist.v_time}</th>
				<th>${vokelist.v_confirm}</th>
				</tr>
			</c:forEach>
		</table>
	</section>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>