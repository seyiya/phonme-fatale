<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>폰므파탈</title>
	
	<!-- 파비콘 -->
	<link rel="shortcut icon" href="<%=cp %>/image/favicon.ico">
	<link rel="icon" href="<%=cp %>/image/favicon.ico">
	<!-- css -->
	<link rel="stylesheet" href="<%=cp%>/css/style.css" type="text/css">
	<link rel="stylesheet" href="<%=cp%>/css/notice.css" type="text/css">
	<link rel="stylesheet" href="<%=cp%>/css/qna.css" type="text/css">
	<!-- 폰트 -->
	<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@500&display=swap" rel="stylesheet">
	
</head>
<body>
<!-- header jsp-->
<jsp:include page="../html/head.jsp"></jsp:include>
				
				
		<div id="wrapper">
			<br><br><br>
			<font style="font-size: 20pt; float: left;">&nbsp;&nbsp;Q&A 질문게시판</font>
			<!-- 버튼 -->
			<input align="right" type="button" class="bokyung_button_qna" value="마이페이지" 
					onclick="javascript:location.href='<%=cp%>/fatale/myPage.do?mid=${dto.mid }';"/>
			
			<input align="right" type="button" class="bokyung_button_qna" value="글쓰기"
					onclick="javascript:location.href='<%=cp%>/fatale/qnaWrite.do';"/>
			
			<br>
			
			<div class="lists">
				<c:forEach var="dto" items="${lists }">
					<button class="accordion"><font color="#2E338C">[${dto.num}]</font>&nbsp;&nbsp;${dto.title }</button>
					<div class="panel">
						<p class="date">작성자: ${dto.mid} | ${dto.qcreated }</p>
						<p class="content">${dto.content }</p>
					</div>
				</c:forEach>
			</div>
			
			<div id="paging">
			<br>
				<c:if test="${dataCount!=0 }">
					${pageIndexList }
				</c:if>
				<c:if test="${dataCount==0 }">
					등록된 게시물이 없습니다.
				</c:if>
			</div>
		</div>
		
		<div style="height: 150px">
		</div>

<script type="text/javascript" src="<%=cp %>/js/notice.js"></script>
<!-- floating bar jsp -->	
<jsp:include page="../html/floating.jsp"></jsp:include>
<!-- footer jsp-->
<jsp:include page="../html/footer.jsp"></jsp:include>
</body>
</html>