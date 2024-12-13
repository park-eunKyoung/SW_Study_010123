<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
if("${msg}"!=''){
	alert("${msg}");
}
</script>
<body>
	<jsp:include page="header_nav.jsp"></jsp:include>
	<section>
		내용....... <br>
		내용....... <br>
		내용....... <br>
		내용....... <br>
		내용....... <br>
	</section>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>