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
		<h1>투표하기</h1>
		<form name="insertform" action="./insertvote">
			<table border=1>
				<tr>
					<td>주민번호</td>
					<td><input type="text" name="v_jumin" id="주민번"> 예
						:8906153154726</td>
				</tr>
				<tr>
					<td>성명</td>
					<td><input type="text" name="v_name" id="성명"></td>
				</tr>
				<tr>
					<td>후보번호</td>
					<td>
						<select name="m_no" id="후보번호">
							<option value=''>후보선택</option>
							<c:forEach var="member" items="${vList}">
								<option value="${member.m_no}">[${member.m_no}]${member.m_name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>투표시간</td>
					<td><input type="text" name="v_time" id="투표시간"></td>
				</tr>
				<tr>
					<td>투표장소</td>
					<td><input type="text" name="v_area" id="투표장소"></td>
				</tr>
				<tr>
					<td>유권자확인</td>
					<td><input type="radio" name="v_confirm" id="유권자확인" value="Y"> 확인
					<input type="radio" name="v_confirm" id="유권자확인" value="N"> 미확인</td>
				</tr>
				<tr>
				<td colspan=2> <input type="button" onclick="listgo()" value="투표하기">
				<input type="button" onclick="votereset()" value="다시하기"> </td>
				
				</tr>
			</table>
		</form>
	</section>
	<script type="text/javascript">
	function votereset() {
		let frm = document.insertform;
		alert("정보를 지우고 처음부터 다시 입력합니다.")
		frm.reset();
		frm[0].focus();
		
	}
	function listgo() {
		let frm = document.insertform;
		let len = frm.length-2;
		
		for(let i = 0; i < len; i++){
			if(frm[i].value === "" || frm[i].value === null){
				alert(frm[i].id+"이(가) 입력되지않았습니다.");
				frm[i].focus();
				return false;
			}
		}
		frm.submit();
	}
	</script>
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>