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
	<jsp:include page="header_nav.jsp"></jsp:include>
	<section>
		<table border=1>
			<tr>
				<th>후보번호</th>
				<th>성명</th>
				<th>소속정당</th>
				<th>학력</th>
				<th>주민번호</th>
				<th>지역구</th>
				<th>대표전화</th>
			</tr>
			<c:forEach var="member" items="${mList}">
			<tr>
				<th>${member.m_no}</th>
				<th>${member.m_name}</th>
				<th>${member.p_name}</th>
				<th>${member.p_school}</th>
				<th>${member.m_jumin}</th>
				<th>${member.m_city}</th>
				<th>${member.p_tel}</th>
			</tr>
			</c:forEach>
		</table>
	</section>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>