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
<title>Q&A(글쓰는 곳 )</title>

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
		function sendIt(){
			
			f = document.myForm;
			
			f.action = "<%=cp%>/fatale/qnaWrite_ok.do";
			f.submit();
		}
	</script>

</head>
<body>
<!-- header jsp-->
<jsp:include page="../html/head.jsp"></jsp:include>
	
	<div id="wrapper_write">
	<form action="" name="myForm" method="post">
		<br><br><br>
		
		<table style="font-size: 15pt;">
			<tr>
				<td colspan="2" align="left">
					<font style="font-size: 20pt;">&nbsp;Q&A 질문 남기기</font>
					<hr><br></td>
			<tr>
				<td align="right"><font color="#2E338C">&nbsp;글번호&nbsp;</font></td>
				<td><input type="text" class="bokyung_write_text"
							disabled="disabled" value="${maxNum }" align="left">
					<input type="hidden" name="num" value="${maxNum }"/>
				</td>
					
			<tr>
				<td align="right"><font color="#2E338C">&nbsp;작성자&nbsp;</font></td> 
				<td><input type="text" value="${mid }" 
							disabled="disabled" class="bokyung_write_text">
							<input type="hidden" name="mid" value="${mid }"/>
							</td>
							
			<tr>
				<td align="right"><font color="#2E338C">&nbsp;제목&nbsp;</font></td> 
				<td><input type="text"  name="title" class="bokyung_write_text"></td>
			<tr valign="top" >
				<td align="right" style="padding-top: 15px;"><font color="#2E338C">
				&nbsp;내용&nbsp;</font></td> 
				<td><textarea  name="content" rows="30" cols="20" class="bokyung_write_textarea"></textarea></td>
			
			<tr><td colspan="2" align="center"><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="submit" value="제출" class="bokyung_button_qna" onclick="sendIt()">
				<input type="button" value="돌아가기" class="bokyung_button_qna" onclick="history.back()">
				<font color="red"><b>${message }</b></font></td>
		</table>
	</form>
	</div>
	
	<div style="height: 1px;"></div>


<script type="text/javascript" src="<%=cp %>/js/notice.js"></script>
<!-- floating bar jsp -->	
<jsp:include page="../html/floating.jsp"></jsp:include>
<!-- footer jsp-->
<jsp:include page="../html/footer.jsp"></jsp:include>
</body>
</html>