<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
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
		<h1>회원별 매출 정보</h1>
		<table border=1>
			<tr>
				<th>회원정보</th>
				<th>회원성명</th>
				<th>회원등급</th>
				<th>매출</th>
			</tr>
			<c:forEach var="member" items="${salesList}">
				<tr>
					<td>${member.custno}</td>
					<td>${member.custname}</td>
					<td>${member.grade}</td>
					<td>${member.total}</td>
				</tr>
			</c:forEach>
		</table>
	</section>
</body>
</html>