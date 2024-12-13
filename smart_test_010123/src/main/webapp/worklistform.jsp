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
		<h1>작업지시서조회</h1>
		<table border=1>
			<tr>
				<th>작업지시번호</th>
				<th>제품코드</th>
				<th>제품명</th>
				<th>제품규격</th>
				<th>제품구분</th>
				<th>수량</th>
				<th>작업시작일</th>
			</tr>

			<c:forEach var="pspd" items="${pList}">
				<tr>
					<th>${pspd.w_workno}</th>
					<th>${pspd.p_code}</th>
					<th>${pspd.p_name}</th>
					<th>${pspd.p_size}</th>
					<th>${pspd.p_type}</th>
					<th>${pspd.w_quantity}</th>
					<th>${pspd.w_workdate}</th>
				</tr>
			</c:forEach>

		</table>
	</section>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>