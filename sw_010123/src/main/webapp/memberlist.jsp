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
	<h1>memberlist.jsp_회원목록조회/수정</h1>
	<table border=1>
		<tr>
			<th>회원번호</th>
			<th>회원성명</th>
			<th>전화번호</th>
			<th>주소</th>
			<th>가입일자</th>
			<th>고객등급</th>
			<th>거주지역</th>
		</tr>
		<c:forEach var="member" items="${mList}">
			<tr>
				<td><a href="./updateform?custno=${member.custno}">${member.custno}</a></td>
				<td>${member.custname}</td>
				<td>${member.phone}</td>
				<td>${member.address}</td>
				<td>${member.joindate}</td>
				<td>${member.grade}</td>
				<td>${member.city}</td>
			</tr>
		</c:forEach>
	</table>
		
	</section>
</body>
</html>