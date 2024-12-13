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
		<table border=1>
			<tr>
				<th>출판사 코드</th>
				<th>출판사 명</th>
				<th>총매출액</th>
			</tr>
			<c:forEach var="company" items="${cList}">
				<tr>
					<th>${company.comcode}</th>
					<th>${company.comname}</th>
					<th>${company.total}</th>
				</tr>
			</c:forEach>
		</table>
	</section>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>