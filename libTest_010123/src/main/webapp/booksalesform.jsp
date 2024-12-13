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
 	<th>도서 코드</th>
 	<th>도서 명</th>
 	<th>총매출액</th>
 	</tr>
 	<c:forEach var="booksales" items="${bList}">
 	 	<tr>
 	 	<th>${booksales.bcode}</th>
 	 	<th>${booksales.bname}</th>
 	 	<th>${booksales.total}</th>
 	 	</tr>
 	</c:forEach>
 </table>
</section>

</body>
</html>