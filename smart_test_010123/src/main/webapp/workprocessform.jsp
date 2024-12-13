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
<h1>작업공정조회</h1>
<table border=1>
	<tr>
	<th>작업지시번호</th>
	<th>제품코드</th>
	<th>제품명</th>
	<th>준비</th>
	<th>인쇄</th>
	<th>코팅</th>
	<th>합지</th>
	<th>접합</th>
	<th>포장</th>
	<th>최종공정일자</th>
	<th>최종공정시간</th>
	</tr>
	<c:forEach var="workprocess" items="${pList}">
	<tr>
	<th>${workprocess.w_workno}</th>
	<th>${workprocess.p_code}</th>
	<th>${workprocess.p_name}</th>
	<th>${workprocess.p_p1}</th>
	<th>${workprocess.p_p2}</th>
	<th>${workprocess.p_p3}</th>
	<th>${workprocess.p_p4}</th>
	<th>${workprocess.p_p5}</th>
	<th>${workprocess.p_p6}</th>
	<th>${workprocess.w_workdate}</th>
	<th>${workprocess.w_lasttime}</th>
	</tr>
	</c:forEach>
	
</table>
</section>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>