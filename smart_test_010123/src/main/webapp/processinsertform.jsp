<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
if('${msg}'!=''){
	alert('${msg}');
}
</script>
<body>
<jsp:include page="header_nav.jsp"></jsp:include>
<section>
<form name="psinsertform" action="./processinsert" method="post">
	<table border=1>
		<tr>
			<td>작업지시번호</td>
			<td> <input type="text" name="w_workno"></td>
		</tr>
		<tr>
		<td>재료준비</td>
		<td> <input type="radio" name="p_p1" value="Y">완료 
		 <input type="radio" name="p_p1" value="N">작업중 </td>
		</tr>
		<tr>
		<td>인쇄공정</td>
		<td> <input type="radio" name="p_p2" value="Y">완료 
		 <input type="radio" name="p_p2" value="N">작업중 </td>
		</tr>
		<tr>
		<td>코팅공정</td>
		<td> <input type="radio" name="p_p3" value="Y">완료 
		 <input type="radio" name="p_p3" value="N">작업중 </td>
		</tr>
		<tr>
		<td>합지공정</td>
		<td> <input type="radio" name="p_p4" value="Y">완료 
		 <input type="radio" name="p_p4" value="N">작업중 </td>
		</tr>
		<tr>
		<td>접합공정</td>
		<td> <input type="radio" name="p_p5" value="Y">완료 
		 <input type="radio" name="p_p5" value="N">작업중 </td>
		</tr>
		<tr>
		<td>포장적재</td>
		<td> <input type="radio" name="p_p6" value="Y">완료 
		 <input type="radio" name="p_p6" value="N">작업중 </td>
		</tr>
		<tr>
		<td>최종작업일자</td>
		<td> <input type="text" name="w_lastdate">예)20190101 </td>
		</tr>
		<tr>
		<td>최종작업시간</td>
		<td> <input type="text" name="w_lasttime">예)1300 </td>
		</tr>
		<tr>
		<td colspan=2> <input type="button" onclick="psIn()" value="공정등록" >
		 <input type="button" onclick="psUp()" value="공정수정" > 
		<input type="reset" value="다시쓰기"></td>
		</tr>
	</table>
</form>
</section>
<script type="text/javascript">
function psIn() {
	
	const frm = document.psinsertform;
	
	for (let i=1; i < frm.length - 3; i++) {
		if(frm[i].value ==='' || frm[i].value === null){
			alert(frm[i].name+"값을 입력하지않았습니다.")
			return false;
		}
	}
	
	frm.submit();
}

function psUp() {
	const frm = document.psinsertform;
	frm.action = "./processupdate";
	frm.submit();
}

</script>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>