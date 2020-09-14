<%@page import="com.phonme.PhonmeDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.phonme.PhonmeDAO"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.util.DBCPConn"%>
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
<title>로그인</title>

	<!-- 파비콘 -->
	<link rel="shortcut icon" href="<%=cp %>/image/favicon.ico">
	<link rel="icon" href="<%=cp %>/image/favicon.ico">
	<!-- css -->
	<link rel="stylesheet" href="<%=cp%>/css/style.css" type="text/css">
	<link rel="stylesheet" href="<%=cp%>/css/login.css" type="text/css">
	<!-- 폰트 -->
	<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">


<script type="text/javascript">

	function sendIt(){
		
		var f = document.myForm;
		
		if(!f.mid.value){
			alert("아이디를 입력하세요")
			f.mid.focus();
			return;
		}
		if(!f.pw.value){
			alert("비밀번호를 입력하세요")
			f.pw.focus();
			return;
		}
	
		f.action = "<%=cp%>/fatale/login_ok.do";
		f.submit();
	}


</script>
</head>
<body>
<!-- header jsp-->
<jsp:include page="../html/head.jsp"></jsp:include>
	
	<br>
	<div class="wrapper">
	  <div class="wrapper_inner">
	    <!-- Gallery -->
	    <section class="gallery">
	      
		   <div class="gallery_item">
				<br><br>		      	
		        <font color="#7a7ebf"><h1>LOGIN</h1></font>

		      <form action="" name="myForm" method="post">
		            <hr width="350px"><br>
	            	<font size="4pt" color="#2E338C">아&nbsp;이&nbsp;디&nbsp;</font>
						<input type="text" name="mid"
								style="width: 250px; height: 35px; text-align: left; padding-left: 15px; background-color: #DFDFEB;
								font-family: 'Do Hyeon', sans-serif; font-size: 14pt; color: #666; vertical-align: middle;
								border-color: white; border: 0">
					<br><br>
					<font size="4pt" color="#2E338C">비밀번호&nbsp;</font>
						<input type="password" name="pw"
							style="width: 250px; height: 35px; text-align: left; padding-left: 15px; background-color: #DFDFEB;
							font-family: 'Do Hyeon', sans-serif; font-size: 14pt; color: #666; vertical-align: middle;
							border-color: white; border: 0">
				
						
					<br><br><hr width="350px"><br>
					<!-- 버튼 -->
					<input type = "checkbox">아이디저장 
					<input type = "checkbox" checked = "checked">보안접속&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" class="bokyung_button1" value=" 로그인 " onclick="sendIt();"/>
					<br><br>
					<input type="button" value="회원 가입"  class="bokyung_button2"
							onclick ="javascript:location.href='<%=cp%>/fatale/member.do';">
					<input type="button" value="아이디 찾기"  class="bokyung_button2"
							onclick ="javascript:location.href='<%=cp%>/fatale/idSearch.do';">
					<input type="button" value="비밀번호 찾기"  class="bokyung_button2"
							onclick ="javascript:location.href='<%=cp%>/fatale/pwSearch.do';">
					
				<br><br>
					<!-- 메세지 -->
					<c:if test="${!empty message}"><font color="#F23D4C">${message}</font></c:if>
					<c:if test="${empty message}">
					<a><img alt="그림" src="<%=cp %>/image/logo.png" width="100px"></a>
		      		<font size="4pt" color="#BDBDBD" style="cursor:default;">
		      			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		      			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
		      			환영합니다!</font></c:if>
			</form>
		  </div>
	    </section>
	  </div>
	 </div>
	
	<div style="height: 180px;"></div>

		
<!-- floating bar jsp -->	
<jsp:include page="../html/floating.jsp"></jsp:include>
<!-- footer jsp-->
<jsp:include page="../html/footer.jsp"></jsp:include>
</body>
</html>