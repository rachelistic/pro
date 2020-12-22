<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link href="/Member/css/menu.css" rel="stylesheet">
</head>
<body>
<div align="center">
<div><h1>행복한 사람들의 공간</h1></div>
<div>
	<ul id="nav">
		<c:if test="${id eq null || auth eq null}">
			<li><a href="/Member/jsp/member/loginForm.jsp">로그인</a></li>
		</c:if>
		<c:if test="${id ne null && auth ne null }">
			<li><a href="#">마이페이지</a>		</li>			
		</c:if>
		<li><a href="#">메인메뉴2</a>
			<ul>
				<li><a href="#">2-1</a></li>
				<li><a href="#">2-2</a></li>
			</ul>
		</li>
		<li><a href="#">회원가입</a></li>
		<c:if test="${id ne null && auth eq 'user' }">
			<li><a href="/Member/NoticeList.do">공지사항</a></li>
		</c:if>
		<li><a href="/Member/BorderList.do">자유게시판</a></li>
		<c:if test="${auth eq 'admin' }">
			<li><a href="#">기본정보관리</a>
				<ul>
					<li><a href="/Member/MemberList.do">회원관리</a></li>
					<li><a href="/Member/NoticeList.do">공지사항관리</a></li>
					<li><a href="#">3-3</a></li>
					<li><a href="#">3-4</a></li>
					<li><a href="#">3-5</a></li>
					<li><a href="#">3-6</a></li>
				</ul>
			</li>
		</c:if>
		<c:if test="${id ne null && auth ne null }">
			<li><a href="/Member/Logout.do">로그아웃</a></li>
		</c:if>
	</ul>
</div>
</div>
</body>
</html>