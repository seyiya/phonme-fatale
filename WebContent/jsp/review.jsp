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
<script type="text/javascript">

	function update(reviewNum) {
	
	var f = document.myForm;
	
	f.action = "<%=cp%>/fatale/reviewWrite.do?num=" + reviewNum;
	f.submit();
	
	}
	
</script>
</head>
<body>
<!-- header jsp-->
<jsp:include page="../html/head.jsp"></jsp:include>

	<!-- ★★★★★★form 넣으면 작동안됨!!!!!! -->
		<div id="wrapper">
			<br><br><br>
			<font style="font-size: 20pt; float: left;">&nbsp;&nbsp;리뷰 게시판</font>
			<!-- 버튼 -->
			<input align="right" type="button" class="bokyung_button_qna" value="마이페이지" 
					onclick="javascript:location.href='<%=cp%>/fatale/myPage.do?mid=${dto.mid }';"/>
			
			<br>
			<div class="lists">
				<c:forEach var="dto" items="${lists }">
					<button class="accordion">
						<font color="#2E338C">[${dto.num}]&nbsp;</font>
						제품:${dto.pid} | ${dto.pname}&nbsp;&nbsp;제목 : ${dto.title }
					</button>
					<div class="panel">
						<p class="date">작성자: ${dto.mid}</p>
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
					<font color="#666">등록된 게시물이 없습니다.</font>
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