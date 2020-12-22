<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/jsp/menu/menu.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<!-- Ajax 호출 -->
<script type="text/javascript">
$(function(){

	$.ajax({
		url:'/Member/AjaxNoticeList.do',
		dataType:"json",
		success: function(event) {
			var arr = event.data;  //데이터만 추출
			if(arr.length > 0){
				var tb = $("<table border='1' />");
				var row = $("<tr />").append(
						$("<th width='100'/>").text("순번"),	
						$("<th width='200' />").text("제목"),	
						$("<th width='100' />").text("작성자"),	
						$("<th width='100' />").text("작성일자"),
						$("<th width='100' />").text("조회수"),
						$("<th width='200' />").text("첨부파일")						
				);
				tb.append(row);
				for(var i in arr){
					row = $("<tr />").append(
						$("<td align='center' />").text(arr[i].noticeId),
						$("<td />").text(arr[i].noticeTitle),
						$("<td align='center' />").text(arr[i].noticeWriter),
						$("<td align='center' />").text(arr[i].noticeDate),
						$("<td align='center' />").text(arr[i].noticeHit),
						$("<td align='center' />").text(arr[i].noticeAttech)
					);
					tb.append(row);
				}
			}
			$(".notice").append(tb);
		},
		error: function(){
			alert("데이터를 가져오지 못했네요......");
		}
	});
});
</script>
</head>
<body>
<div align="center">
	<c:if test="${auth ne null}">
		<h1>${vo.memberName } 님 환영합니다.</h1>
				<!-- 공지사항 목록 -->
		<div id="notice" class="notice" align="center"></div>
	</c:if>
	<c:if test="${auth eq null }">
		<h1>${vo.memberId } 가 존재하지 않거나 패스워드가 일치하지 않습니다.</h1>
	</c:if>	
</div>
</body>
</html>