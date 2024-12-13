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
	<th>전표번호</th>
	<th>출판사</th>
	<th>판매일자</th>
	<th>도서코드</th>
	<th>도서명</th>
	<th>판매수량</th>
	<th>매출액</th>
</tr>
<c:forEach var="total" items="${tList}">
<tr>
<th>${total.saleno}</th>
<th>${total.comcodename}</th>
<th>${total.saledate}</th>
<th>${total.bcode}</th>
<th>${total.bname}</th>
<th>${total.amount}</th>
<th>${total.total}</th>
</tr>
</c:forEach>
</table>
</section>

<jsp:include page="./footer.jsp"></jsp:include>
</body>
</html>