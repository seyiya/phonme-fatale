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
<!-- 파비콘 -->
<link rel="shortcut icon" href="<%=cp%>/image/favicon.ico">
<link rel="icon" href="<%=cp%>/image/favicon.ico">
<!-- css -->
<link rel="stylesheet" href="<%=cp%>/css/style.css" type="text/css">
<link rel="stylesheet" href="<%=cp%>/css/login.css" type="text/css" />
<!-- 폰트 -->
<link
	href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap"
	rel="stylesheet">


<title>로그인</title>
<script type="text/javascript">

	
	function sendIt(){
		
		var f = document.myForm;
		
		if(!f.name.value){
			alert("이름 입력하세요")
			f.name.focus();
			return;
		}
		if(!f.tel.value){
			alert("휴대폰 번호를 입력하세요")
			f.tel.focus();
			return;
		}
	
		f.action = "<%=cp%>/fatale/idSearch_ok.do";
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
				<font color="#7a7ebf"><h1>아이디 찾기</h1></font>
				
				<form action="" method="post" name="myForm">
					<hr width="350px">
					<br><font size="4pt" color="#2E338C">이&nbsp;&nbsp;&nbsp;&nbsp;름&nbsp;</font> 
					<input type="text" name="name"
						style="width: 250px; height: 35px; padding-left: 15px; text-align: left; background-color: #DFDFEB; font-family: 'Do Hyeon', sans-serif; font-size: 14pt; color: #666; vertical-align: middle; border-color: white; border: 0">



					<br>
					<br> <font size="4pt" color="#2E338C">휴대전화&nbsp;</font> <input
						type="text" name="tel"
						style="width: 250px; height: 35px; padding-left: 15px; text-align: left; background-color: #DFDFEB; font-family: 'Do Hyeon', sans-serif; font-size: 14pt; color: #666; vertical-align: middle; border-color: white; border: 0">
					<br><br><hr width="350px"><br>

					<!-- 버튼 -->
					<input type="button" value="뒤로 가기"  class="bokyung_button1"
							onclick ="javascript:location.href='<%=cp%>/fatale/login.do';">
					<input type="button" value="아이디 찾기" class="bokyung_button1"
						onclick="sendIt();"> <br><br>
					<input type="hidden" name="mid"> <br><br>
					<hr color="white" style="height: 1px; margin: 2px 0px;">
					
					<!-- 메세지 -->
					<a><img alt="그림" src="<%=cp %>/image/logo.png" width="100px"></a>
		      		<font size="4pt" color="#BDBDBD" style="cursor:default;">
		      			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		      			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
		      			아이디를 찾아보세요</font>



				</form>
			</div>
			</section>
		</div>
	</div>
	<div style="height: 180px;"></div>


	<br />
	<br />
	<br />
	<!-- floating bar jsp -->
	<jsp:include page="../html/floating.jsp"></jsp:include>
	<!-- footer jsp-->
	<jsp:include page="../html/footer.jsp"></jsp:include>
</body>
</html>