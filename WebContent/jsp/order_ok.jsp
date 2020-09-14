<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<link rel="stylesheet" href="<%=cp%>/css/order.css" type="text/css">

</head>
<body>
<div><jsp:include page="../html/head.jsp"></jsp:include></div> 


<div id="wrapper">
	<div class="container">
		<div style="height: 250px"></div>
			
			<center>
			<table cellpadding="10" cellspacing="10" align="center">
				<!-- <tr>
					<td colspan="2" height="3" bgcolor="#dbdbdb" align="center"></td>
				</tr> -->
				
				<tr>
					<tr>	
						<tr>
							<td width="120" height = "100" align = "center">
							<img alt="" src="<%=cp%>/image/ordergood.gif"> 
							</td>		
							
							<td width="300" height = "60">
							<font size="5">고객님의 주문이 완료 되었습니다</font><br/>
							<font size="3">주문내역 및 배송에 관한 안내는
							<a href="<%=cp %>/fatale/qna.do" class="bokyung_mypage_link_Q"><u>Q&amp;A</u></a>
							게시판으로 문의주시기 바랍니다</font>		
							</td>
						</tr>	
					<tr>
				<tr>	
					
					
				<!-- <tr>
					<td colspan="2" height="3" bgcolor="#dbdbdb" align="center"></td>
				</tr> -->	
			
			</table></center><br>
			
			<div class="btn_position">
				<input type="button" value="쇼핑 계속하기" class="bokyung_button1" 
					onclick="javascript:location.href='<%=cp%>/fatale/search.do';">&nbsp;&nbsp;
				<input type="button" value="주문 확인하기" class="bokyung_button1" 
					onclick="javascript:location.href='<%=cp%>/fatale/myPage.do?#link_order';">
			</div>
			
		<div style="height: 300px"></div>
	</div>	
	
</div>


<!-- floating bar jsp -->	
<jsp:include page="../html/floating.jsp"></jsp:include>
<!-- footer jsp-->
<jsp:include page="../html/footer.jsp"></jsp:include>
</body>
</html>