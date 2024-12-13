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
<h1 align="center"> 제품조회</h1>
<table border=1>
<tr>
<th>제품코드</th>
<th>제품명</th>
<th>제품규격</th>
<th>제품구분</th>
<th>제품단가</th>
</tr>
<c:forEach var="product" items="${pList}">
<tr>
<th>${product.p_code }</th>
<th>${product.p_name }</th>
<th>${product.p_size }</th>
<th>${product.p_type }</th>
<th>${product.p_price }</th>
</tr>
</c:forEach>
</table>
</section>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>