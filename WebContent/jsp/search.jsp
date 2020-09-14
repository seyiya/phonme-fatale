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
	<link href='<%=cp %>/css/search.css' rel='stylesheet' type='text/css'>
	<!-- 폰트 -->
	<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
	<!-- js -->
	<script type="text/javascript">
			function sendIt(){
			    
			    var f = document.searchForm;
			    
			    f.action = "<%=cp%>/fatale/search.do";
			    f.submit();
			 }
	</script>
	
</head>
<body>
<!-- header jsp-->
<jsp:include page="../html/head.jsp"></jsp:include>
	
	<div class="wrapper">
	  <div class="wrapper_inner">
	    <!-- Gallery -->
	    <font color="#7a7ebf">&nbsp;&nbsp;Total ${dataCount } articles, ${totalPage } pages / Now page is ${currentPage }</font>
	    <section class="gallery">
	      

		  <!-- for문 한칸 -->
	      <c:forEach var="dto" items="${lists }" >
		      <div class="gallery_item">
		        <span class="gallery_item_preview">
		          <a href="<%=cp %>/fatale/article.do/?pid=${dto.pid}&pageNum=${currentPage}&searchKey=${searchKey}&searchValue=${searchValue}" data-js="0	">
		            <img alt="그림" src="${imagePath }/${dto.img}" width="250" height="250">
		            <h3>${dto.pid}/${dto.pname}</h3>
		            <p>${dto.price }원
		            	
		            	<!-- 히트상품 --><br>
						<c:if test="${dto.hit>=20 }"><font color="#F23D4C" style="font-weight: bold">히트상품</font>&nbsp;|&nbsp;조회:${dto.hit }</c:if>
						<c:if test="${dto.hit<20 }">&nbsp;조회:${dto.hit }&nbsp;</c:if>
		            </p>
		          </a>
		        </span>
		      </div>
		      <c:set var="count" value="${count+1 }"></c:set><!-- count증가 -->
	      </c:forEach>
	    </section>
	  </div>
	</div>
	
	
	
	<div align="center" style="width: 100%; height: 350px;">&nbsp;</div>
	<div align="center" style="width: 100%; height: 100px;">
    <!-- 페이징 -->
    <c:if test="${dataCount != 0 }">${pageIndexList }</c:if>
	<c:if test="${dataCount == 0 }">등록된 게시물이 없습니다</c:if>
    </div>

	
	<br><br><br><br><br><br><br><br><br>
	
	<!-- floating bar jsp -->	
	<jsp:include page="../html/floating.jsp"></jsp:include>
	<!-- footer jsp-->
	<jsp:include page="../html/footer.jsp"></jsp:include>

</body></html>


