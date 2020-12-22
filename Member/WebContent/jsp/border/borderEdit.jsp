<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/jsp/menu/menu.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 수정</title>
</head>
<body>
<div align="center">
	<div><h1>글 수 정</h1></div>
	<div>
		<form id="frm" name="frm" action="/Member/BorderEdit.do" method="post">
			<table border="1">
				<tr>
					<th width="100">작 성 자</th>
					<td width="300">
						${vo.borderWrite }
					</td>
				</tr>
				<tr>
					<th width="100">작 성 일</th>
					<td width="300">
						<input type="date" id="wdate" name="wdate" value="${vo.borderDate }">
					</td>
				</tr>
				<tr>
					<th width="100">제 목</th>
					<td width="300">
						${vo.borderTitle }
					</td>
				</tr>
				<tr>
					<th width="100">내 용</th>
					<td width="300">
						<textarea id="content" name="content" rows="5" cols="100">${vo.borderContent }</textarea>
					</td>
				</tr>
			</table>
			<br/>
			<input type="hidden" id="id" name="id" value="${vo.borderId }">
			<button type="submit">수정하기</button> &nbsp;&nbsp;&nbsp;
			<button type="reset">취 소</button> &nbsp;&nbsp;&nbsp;
			<button type="button" onclick="location.href='/Member/BorderList.do'">목록보기</button> &nbsp;&nbsp;&nbsp;
		</form>
	</div>
</div>
</body>
</html>