<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	if ('${msg}' !== '' ) {
		alert('${msg}');
	}
</script>
<body>
	<jsp:include page="header_nav.jsp"></jsp:include>
	<section>
		<form name="salesform" action="./salesjoin" method="post">
			<table border=1>
				<tr>
					<td>전표번호</td>
					<td><input type="text" name="saleno" readonly value = '${saleno}'></td>
				</tr>
				<tr>
					<td>판매일자</td>
					<td><input type="text" name="saledate"> (예)20241211</td>
				</tr>
				<tr>
					<td>도서코드</td>
					<td><select name=bcode>
							<option value="">도서선택</option>
							<c:forEach var="book" items="${bList}">
								<option value="${book.bcode}">[${book.bcode}]${book.bname}</option>
							</c:forEach>

					</select></td>
				</tr>
				<tr>
					<td>판매수량</td>
					<td><input type="text" name="amount"></td>
				</tr>
				<tr>
					<td colspan=2><input type="button" onclick="join()"
						value="매출등록"> <input type="reset" value="다시쓰기"></td>
				</tr>
			</table>

		</form>
	</section>
	<jsp:include page="footer.jsp"></jsp:include>

	<script type="text/javascript">
		/* function resetfrm() {
		 document.getElementById('salesform').reset();
		 } */
		function join() {
			const frm = document.salesform;
			const len = frm.length - 2;
			for (let i = 1; i < len; i++) {
				if (frm[i].value === '' || frm[i].value === null) {
					alert(frm[i].name + "를 입력하지않았습니다.");
					frm[i].focus();
					return false;
				}
				frm.submit();
			}
		}
	</script>
	<jsp:include page="./footer.jsp"></jsp:include>
</body>
</html>