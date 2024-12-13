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
	alert('${msg}')
}
</script>
<body>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="nav.jsp"></jsp:include>
<section>
	<h3 align="center">홈쇼핑 회원 등록</h3>
	<form name = "joinform" action="./memberjoin" method="get">
	<table border="1" >
	<tr>
	<td> 회원번호 (자동발생)</td>
	<td> <input type="text" name="custno" readonly value = "${custno}"> </td>
	</tr>
	<tr>
	<td> 회원성명</td>
	<td> <input type="text" name="custname"> </td>
	</tr>
	<tr>
	<td> 회원전화</td>
	<td> <input type="text" name="phone"> </td>
	</tr>
	<tr>
	<td> 회원주소</td>
	<td> <input type="text" name="address"> </td>
	</tr>
	<tr>
	<td> 가입일자 </td>
	<td> <input type="text" name="joindate"> </td>
	</tr>
	<tr>
	<td> 고객등급 [A:VIP, B:일반, C:직원] </td>
	<td> <input type="text" name="grade"> </td>
	</tr>
	<tr>
	<td> 도시코드 </td>
	<td> <input type="text" name="city"> </td>
	</tr>
	<tr>
	<td colspan=2> <input type="button" onclick="check()" value="등록"> 
	<input type="button" onclick="move()" value="조회"> </td>
	</tr>
	</table>
	</form>
</section>
<jsp:include page="footer.jsp"></jsp:include>
<script type="text/javascript">
	function move(){
		location.href="./memberlist"
	} 
	function check(){
		const frm=document.joinform;
		console.log(frm);
		const len=frm.length-2;
		for(let i=1; i<len;i++){
			if(frm[i].value===null || frm[i].value===''){
				alert(frm[i].name+"가 입력되지않았습니다.");
				frm[i].focus();
				return false;
			}
		}
		frm.submit();
	}
</script>

</body>
</html>