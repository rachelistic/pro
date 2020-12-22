<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/jsp/menu/menu.jsp" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
<style type="text/css">
<!--
    tr.row:hover { background-color: lightyellow; }
-->
</style>
<script type="text/javascript">
	function SearchFunction(str) {
		document.frm.mid.value = str;
		document.frm.action = "/Member/MemberRead.do";
		document.frm.submit();
	}
</script>

</head>
<body>
<div align="center">
	<div><h1>회 원 목 록</h1></div>
	<div>
		<table border="1">
			<tr>
				<th width="100">아이디</th>
				<th width="100">이 름</th>
				<th width="100">권 한</th>
				<th width="100">포인트</th>
			</tr>
			<c:forEach var="vo" items="${members }">
				<tr class="row" onclick="SearchFunction('${vo.memberId }')"> 
					<td align="center">${vo.memberId }</td>
					<td align="center">${vo.memberName }</td>
				 	<td align="center">${vo.memberAuth }</td>
					<td align="center">${vo.memberPoint }</td>
				</tr>
			</c:forEach>
		</table>
		<form id="frm" name="frm" method="post">
			<input type="hidden" id="mid" name="mid">
		</form>
	</div>
</div>
</body>
</html>