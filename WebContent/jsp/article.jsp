<%@page import="com.phonme.PhonmeInfo"%>
<%@page import="com.util.DBCPConn"%>
<%@page import="com.phonme.PhonmeDAO"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.phonme.PhonmeDTO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
	
	PhonmeInfo info = null;
	String mid = null;
	HttpSession se = request.getSession();
	// 로그인한 아이디 받음
	
	Object ob = null;
	ob = se.getAttribute("phonmeInfo");
	if (ob != null) {
		info = (PhonmeInfo) ob;
		mid = info.getMid();
	}
	
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
	<link rel="stylesheet" href="<%=cp%>/css/article.css" type="text/css">
	<!-- 폰트 -->
	<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
	<!-- js -->
	<script type="text/javascript">
	function sendIt() {
	
	var f = document.myForm;
	
	//비로그인 시 로그인 창으로
	if ('<%=mid%>'==null) {
		f.action = "<%=cp%>/fatale/login.do";
		f.submit();
		return;
	}
	
	//수량 빈칸 체크
	str = f.count.value;
	str = str.trim();
	if (!str) {
		alert("\n수량을 입력하세요.");
		f.count.focus();
		return;
	}
	f.count.value = str;
	
	//기종선택 유무 체크
	str = f.gid.value;
	str = str.trim();
	if (!str||str=="") {
		alert("\n기종을 선택하세요.");
		f.gid.focus();
		return;
	}
	
	//색상선택 유무 체크
	str = f.color.value;
	str = str.trim();
	if (!str||str=="") {
		alert("\n색상을 선택하세요.");
		f.color.focus();
		return;
	}
	
	f.action = "<%=cp%>/fatale/article_ok.do";
	f.submit();
	
	}
	
	function jjim() {
		
	var f = document.myForm;
		
	f.action = "<%=cp%>/fatale/jjim_ok.do";
	f.submit();
		
	}
	
	function onlyNumber(event){
	    event = event || window.event;
	    var keyID = (event.which) ? event.which : event.keyCode;
	    if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
	        return;
	    else
	        return false;
	}
	 
	function removeChar(event) {
	    event = event || window.event;
	    var keyID = (event.which) ? event.which : event.keyCode;
	    if ( keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
	        return;
	    else
	        event.target.value = event.target.value.replace(/[^0-9]/g, "");
	}
</script>
</head>
<body>
<!-- header jsp-->
<jsp:include page="../html/head.jsp"></jsp:include>



	<div class="wrapper">
	  <div class="wrapper_inner">
	    <!-- Gallery -->
	    <font color="#7a7ebf" size="3pt">&nbsp;&nbsp;상품코드 - ${dto.pid }  |  조회수 - ${dto.hit } </font>
	    <section class="gallery">
	      

		   <div class="gallery_item">
		      
		      <div class="gallery_article gallery_item_preview">
		          <a href="${imagePath }/${dto.img}">
		          <img alt="그림" src="${imagePath }/${dto.img}" width="250" height="250"></a>
		      </div>
		      
		      <div class = "article_item">
		      <form action="" name="myForm" method="post">
		      		<!-- 제목 -->
		      		<font color="#8C8C8C">${dto.pid}&nbsp;&nbsp;</font>
		      		<font size="4pt" color="#BDBDBD">Phonme_Fatale</font><br>
		            <font style="font-size: 35pt" color="black">${fn:substring(dto.pname, 0, 20)}</font>
		            <br><hr>
		      		
		      		<!-- 가격 -->      
	            	<font size="6pt" color="#2E338C">${dto.price }원&nbsp;</font>
	            	
	            	<!-- 히트상품 -->
					<c:if test="${dto.hit>=20 }">
					<font size="4pt" color="#F23D4C" style="font-weight: bold">히트상품</font>
					<font size="4pt">&nbsp;|&nbsp;조회:${dto.hit }</font>
					</c:if>
					
					<c:if test="${dto.hit<20 }">
					<font>&nbsp;</font>
					</c:if>
	            	
	            	<!-- 옵션 -->
					<br><br><br>     
	            	
						<input type="hidden" name="pid" value="${dto.pid }"/>
						<!-- 기종 선택 -->
						<select name="gid"
								style="width: 260px; height: 35px; text-align: center; background-color: #DFDFEB;
								font-family: 'Do Hyeon', sans-serif; font-size: 14pt; color: #666; vertical-align: middle;
								border-color: white;">
							<option value="">&nbsp;기종선택</option>
							<c:forEach var="g" items="${gijong }">
								<option value="${g.gid}">&nbsp;${g.gname}</option>
							</c:forEach>
						</select><br><br>
						
						
						<!-- 색상 선택 -->
						<select name="color" 
								style="width: 260px; height: 35px; text-align: center; background-color: #DFDFEB;
								font-family: 'Do Hyeon', sans-serif; font-size: 14pt; color: #666; vertical-align: middle;
								border-color: white;">
							<option value="">&nbsp;색상선택</option>
							<c:forEach var="c" items="${color }">
								<option value="${c}">&nbsp;${c}</option>
							</c:forEach>
						</select> <br><br>
						
						
						<!-- 수량 -->
						<font style="font-size:16pt; color: #666; vertical-align: middle;">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						갯수&nbsp;&nbsp;</font>
						<input type="text" name="count" 
						style="width: 50px; height: 45px; padding-top: 2px; text-align: center;
						color:#F23D4C; font-size: 20pt; font-family: 'Do Hyeon', sans-serif; vertical-align: middle;" 
						onkeydown ='return onlyNumber(event)' onkeyup ='removeChar(event)'>
				
						
						<br><br><hr><br>
						<!-- 버튼 -->
						<input type="button" class="bokyung_button1" value=" 장바구니 " onclick="sendIt();"/>
						<input type="button" class="bokyung_button1" value="찜" onclick="jjim();"/>
						<br><br>
						
						<!-- 상품설명 -->
						<font size="4px" color="#8C8C8C" >&nbsp;${dto.content }&nbsp;</font>
						
						<!-- 리뷰보기 -->
						<input type="button" onclick="location.href='<%=cp%>/fatale/review.do?searchKey=pname&searchValue=${dto.pname}'" 
						value="리뷰 보기" class="bokyung_review" ><br>
						<br><br>
						<input type="button" class="bokyung_back" value="◀뒤로" 
				         onclick="javascript:history.back();"/> 
							
						<c:if test="${!empty message}">
							<font color="red">${message}</font>
						</c:if>
					</form>
		      </div>
		  </div>
	    </section>
	  </div>
	</div>
	
	
	

<!-- floating bar jsp -->	
<jsp:include page="../html/floating.jsp"></jsp:include>
<!-- footer jsp-->
<jsp:include page="../html/footer.jsp"></jsp:include>
</body>
</html>