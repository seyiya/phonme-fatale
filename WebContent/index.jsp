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
	<link rel="stylesheet" href="<%=cp%>/css/index.css" type="text/css">
</head>
<body>
<!-- header jsp-->
<jsp:include page="./html/head.jsp"></jsp:include>
	
<!-- 슬라이드 쇼 -->
	<div id="slideshow_wrap">
		<!-- slide -->
		<div class="slideshow-container">
	
		<div class="mySlides fade">
		  <a href="<%=cp%>/fatale/article.do/?pid=C03&pageNum=1&searchKey=gname&searchValue=S9">
		  <img src="<%=cp%>/image/big1.jpg"></a>
		</div>
		
		<div class="mySlides fade">
		  <a href="<%=cp%>/fatale/article.do/?pid=B04&pageNum=3&searchKey=gname&searchValue=8">
		  <img src="<%=cp%>/image/big2.jpg"></a>
		</div>
		
		<div class="mySlides fade">
		  <a href="<%=cp%>/fatale/article.do/?pid=G04&pageNum=2&searchKey=gname&searchValue=S9">
		  <img src="<%=cp%>/image/big3.jpg"></a>
		</div>
		  
		</div>
		<br>
		
		<!-- dot -->
		<div style="text-align:center">
		  <span class="dot"></span> 
		  <span class="dot"></span> 
		  <span class="dot"></span> 
		</div>	
		
	</div>
	<br><br>
	
<!-- big pic area -->	
	<center><table style="width: 100%" border="0" cellpadding="5">
	<tr>
		<td align="center">
		<a href="<%=cp%>/fatale/article.do/?pid=M01&pageNum=1&searchKey=gname&searchValue=S9">
		<img alt="박" src="<%=cp%>/image/mid1.jpg" height="430px" width="100%">
		</a></td>
		
		<td align="center">
		<a href="<%=cp%>/fatale/article.do/?pid=I04&pageNum=1&searchKey=gname&searchValue=G7">
		<img alt="박" src="<%=cp%>/image/mid2.jpg" height="430px" width="100%">
		</a></td>
		
		<td align="center">
		<a href="<%=cp%>/fatale/article.do/?pid=E02&pageNum=2&searchKey=gname&searchValue=SE">
		<img alt="박" src="<%=cp%>/image/mid3.jpg" height="430px" width="100%">
		</a></td>
	<tr>
	
	
		
		<td align="center">
		<a href="<%=cp%>/fatale/article.do/?pid=G03&pageNum=1&searchKey=gname&searchValue=S10">
		<img alt="박" src="<%=cp%>/image/mid4.jpg" height="430px" width="100%">
		</a></td>
		
		<td align="center">
		<a href="<%=cp%>/fatale/article.do/?pid=F03&pageNum=2&searchKey=gname&searchValue=G8">
		<img alt="박" src="<%=cp%>/image/mid5.jpg" height="430px" width="100%">
		</a></td>
		
		<td align="center">
		<a href="<%=cp%>/fatale/article.do/?pid=K03&pageNum=1&searchKey=getNew&searchValue=true">
		<img alt="박" src="<%=cp%>/image/mid6.jpg" height="430px" width="100%">
		</a></td>
	</table></center>
	
	<br><br>
	
	</center>
	</div>
			
	
<!-- floating bar jsp -->	
<jsp:include page="./html/floating.jsp"></jsp:include>
<!-- footer jsp-->
<jsp:include page="./html/footer.jsp"></jsp:include>
<script type="text/javascript" src="<%=cp %>/js/index.js"></script>
</body>
</html>