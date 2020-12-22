<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/jsp/menu/menu.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보조회</title>
<script type="text/javascript">
	function SelectFunction(str) {
		if(str == 'E') 
			document.frm.action = "/Member/MemberEdit.do";
		else
			document.frm.action = "/Member/MemberList.do";
		document.frm.submit();
	}
</script>
</head>
<body>
<div align="center">
	<div><h1>회원정보 조회</h1></div>
	<div>
		<table border="1">
			<tr>
				<th width="100">아이디</th>
				<td width="100">${vo.memberId }</td>
				<th width="100">이 름</th>
				<td width="100">${vo.memberName }</td>
			</tr>
			<tr>
				<th width="100">권 한</th>
				<td width="100">${vo.memberAuth }</td>
				<th width="100">포인트</th>
				<td width="100">${vo.memberPoint }</td>
			</tr>
		</table>
		<br/>
	</div>
	<div>
		<form id="frm" name="frm" method="post">
			<input type="hidden" id="mid" name="mid">
			<button type="button" onclick="SelectFunction('E')">회원정보수정</button>&nbsp;&nbsp;
			<button type="reset">취 소</button>&nbsp;&nbsp;
			<button type="button" onclick="SelectFunction('L')">회원목록</button>
		</form>
	</div>
</div>
</body>
</html>