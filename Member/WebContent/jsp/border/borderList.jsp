<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="/jsp/menu/menu.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판목록</title>
<style type="text/css">
<!--
tr.row:hover {
	background-color: lightyellow;
}
-->
</style>
</head>
<body>
	<div align="center">
		<div>
			<h1>게시판</h1>
		</div>
		(총 레코드 수 : ${count })
		<div>
			<table border="1">
				<tr>
					<th width="100">번 호</th>
					<th width="250">제 목</th>
					<th width="100">작성자</th>
					<th width="100">작성일자</th>
					<th width="100">조회수</th>
				</tr>
				<c:forEach var="vo" items="${glist }">
					<tr class="row"
						onclick="location.href='/Member/BorderRead.do?id=${vo.borderId }'">
						<td width="100" align="center">${vo.borderId }</td>
						<td width="250">${vo.borderTitle }</td>
						<td width="100" align="center">${vo.borderWrite }</td>
						<td width="100" align="center">${vo.borderDate }</td>
						<td width="100" align="center">${vo.borderHit }</td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<!-- 페이지 작동 시키기  -->
			<div class="paginate">
				<a href="javascript:goPage(${params.firstPageNo})" class="first">처음
					페이지</a> <a href="javascript:goPage(${params.prevPageNo})" class="prev">이전
					페이지</a> <span> 
					
					<c:forEach var="i" begin="${params.startPageNo}"
						end="${params.endPageNo}" step="1">
						<c:choose>
							<c:when test="${i eq params.pageNo}">
								<a href="javascript:goPage(${i})" class="choice">${i}</a>
							</c:when>
							<c:otherwise>
								<a href="javascript:goPage(${i})">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</span> 
				<a href="javascript:goPage(${params.nextPageNo})" class="next">다음 페이지</a>
					 <a href="javascript:goPage(${params.finalPageNo})" class="last">마지막
					페이지</a>

			</div>



			<div>
			<%-- 	${params } --%>

			</div>

			<div>
				<br />

				<button type="button"
					onclick="location.href='jsp/border/borderInput.jsp'">글쓰기</button>
			</div>
		</div>
	</div>
	
	
	<script type="text/javascript">
		function goPage(str) {
			location.href = "/Member/BorderList.do?pageNum="+str;
		}
	</script>
	
	<br/>


</body>

</html>