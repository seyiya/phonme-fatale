<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<link rel="stylesheet" href="<%=cp%>/css/review.css" type="text/css">
	<!-- 폰트 -->
	<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@500&display=swap" rel="stylesheet">
	
<script type="text/javascript">
function sendIt() {

	f = document.myForm;
	
	//제목 체크
	str = f.title.value;
	str = str.trim();
	if (!str) {
		alert("\n제목을 입력하세요.");
		f.title.focus();
		return;
	}
	f.title.value = str;
	
	//내용 체크
	str = f.content.value;
	str = str.trim();
	if (!str) {
		alert("\n내용을 입력하세요.");
		f.content.focus();
		return;
	}
	
	f.action = "<%=cp%>/fatale/reviewWrite_ok.do";
	f.submit();

}

function update(num) {

	f = document.myForm;

	//제목 체크
	str = f.title.value;
	str = str.trim();
	if (!str) {
		alert("\n제목을 입력하세요.");
		f.title.focus();
		return;
	}
	f.title.value = str;
	
	//내용 체크
	str = f.content.value;
	str = str.trim();
	if (!str) {
		alert("\n내용을 입력하세요.");
		f.content.focus();
		return;
	}
	
	f.action = "<%=cp%>/fatale/reviewUpdate.do?num=" + num;
	f.submit();

}
</script>

</head>
<body>
	
<div>
<jsp:include page="../html/head.jsp"></jsp:include>
</div>
	
<div id="wrapper_write">

	<form action="" name="myForm" method="post">
	        
	 	<br><br><br>
 		<table style="font-size: 15pt;">
			<tr>
				<td colspan="2" align="left">
					<font style="font-size: 20pt;">&nbsp;리뷰 작성</font>
					<hr><br></td>
			</tr>
			
			<tr>
				<td align="right"><font color="#2E338C">&nbsp;구매상품&nbsp;</font></td>
				<td>
					<c:if test="${!empty orderd}">
						<input type="text"  name="pname" class="bokyung_write_text"
						value="${orderd.pname}" readonly="readonly">
						<input type="hidden" name="pid" value="${orderd.pid}">
					</c:if>
					<c:if test="${!empty review}">
						<input type="text"  name="pnme" class="bokyung_write_text"
						value="${review.pname}" readonly="readonly">
						<input type="hidden" name="pid" value="${review.pid}">
					</c:if>
					<br>
				</td>
			</tr>
					
							
			<tr>
				<td align="right"><font color="#2E338C">&nbsp;제목&nbsp;</font></td> 
				<td><input type="text"  name="title" class="bokyung_write_text" value="${review.title}"></td>
			</tr>
			
			<tr valign="top" >
				<td align="right" style="padding-top: 15px;"><font color="#2E338C">
				&nbsp;내용&nbsp;</font></td> 
				<td><textarea name="content" rows="30" cols="20" class="bokyung_write_textarea">${review.content}</textarea></td>
			</tr>
			<c:if test="${!empty review}">
			<tr><td colspan="2" align="center"><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="수정" class="bokyung_button_qna" onclick="update('${review.num}')">
				<input type="button" value="돌아가기" class="bokyung_button_qna" onclick="history.back()">
				<font color="red"><b>${message }</b></font></td>
			</tr>
			</c:if>
			<c:if test="${!empty orderd}">
			<tr><td colspan="2" align="center"><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="등록" class="bokyung_button_qna" onclick="sendIt()">
				<input type="button" value="돌아가기" class="bokyung_button_qna" onclick="history.back()">
				<font color="red"><b>${message }</b></font></td>
			</tr>
			</c:if>
		</table>      
	        
	</form>	

</div>
			
<jsp:include page="../html/floating.jsp"></jsp:include>
<jsp:include page="../html/footer.jsp"></jsp:include>
</body>
</html>