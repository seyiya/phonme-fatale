<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!-- Header file : 모든 jsp파일에 include시켜야함. -->

<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">

<div class="navbar">
   <div class="left-sided">
     <a href="<%=cp %>/fatale/index.do">
     	<img alt="로고" src="<%=cp %>/image/logo.png" >
     </a>
     <a href="<%=cp%>/fatale/search.do?searchKey=getNew&searchValue=true"><font>New<br><br></font></a>
     
     <!-- 갤럭시 -->
     <div class="dropdown">
        <button class="dropbtn">
         갤럭시 
       </button>
       <div class="dropdown-content">
         <div class="row">
            <div><h3>GALAXY</h3></div>
          <div class="column">
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
           </div>
          <div class="column">
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=S10">갤럭시 S10</a></p>
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=S9">갤럭시 S9</a></p>
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=8">갤럭시 S8</a></p>
           </div>
           <!-- dummy data 이므로, 첫 컬럼의 검색 결과를 그대로 가져옵니다.  -->
           <div class="column">
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=S10" >갤럭시 노트 10</a></p>
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=S9">갤럭시 노트 9</a></p>
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=S8">갤럭시 노트 8</a></p>
           </div>
           <div class="column">
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=S10">갤럭시 알파</a></p>
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=S9">갤럭시 메가</a></p>
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=S8">갤럭시 라운드</a></p>
           </div>
           <div class="column">
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=S10">갤럭시 A10</a></p>
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=S9">갤럭시 A20</a></p>
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=S8">갤럭시 A30</a></p>
           </div>
           <div class="column">
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
           </div>
           <div class="column">
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
           </div>
           <div class="column">
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
           </div>
         </div>
       </div>
     </div> 
     
     <!-- 아이폰 -->
     <div class="dropdown">
       <button class="dropbtn">
         아이폰
       </button>
       <div class="dropdown-content">
         <div class="row">
            <div><h3>IPHONE</h3></div>
          <div class="column">
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
           </div>
          <div class="column">
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=XS">아이폰 XS</a></p>
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=SE">아이폰 SE</a></p>
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=8">아이폰 8</a></p>
           </div>
           <!-- dummy data 이므로, 첫 컬럼의 검색 결과를 그대로 가져옵니다.  -->
           <div class="column">
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=XS">아이폰 8 플러스</a></p>
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=SE">아이폰 7 플러스</a></p>
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=8">아이폰 6 플러스</a></p>
           </div>
           <div class="column">
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=XS">아이폰 SE</a></p>
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=SE">아이폰 SE2</a></p>
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=8">아이폰 11 맥스</a></p>
           </div>
           <div class="column">
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=XS">아이폰 3s</a></p>
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=SE">아이폰 4s</a></p>
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=8">아이폰 5s</a></p>
           </div>
           <div class="column">
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
           </div>
           <div class="column">
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
           </div>
           <div class="column">
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
           </div>
         </div>
       </div>
     </div>
     
      <!-- 엘지 -->
     <div class="dropdown">
       <button class="dropbtn">
         LG
       </button>
       <div class="dropdown-content">
         <div class="row">
            <div><h3>LG</h3></div>
            <div class="column">
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
           </div>
          <div class="column">
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=G8">엘지 G8</a></p>
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=G7">엘지 G7</a></p>
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=V50">엘지 V50</a></p>
           </div>
           <!-- dummy data 이므로, 첫 컬럼의 검색 결과를 그대로 가져옵니다.  -->
           <div class="column">
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=G8">엘지 V40</a></p>
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=G7">엘지 V30</a></p>
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=V50">엘지 V20</a></p>
           </div>
           <div class="column">
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=G8">엘지 옵티머스 LTE</a></p>
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=G7">엘지 옵티머스 LTE2</a></p>
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=V50">엘지 옵티머스 G</a></p>
           </div>
           <div class="column">
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=G8">엘지 Q10</a></p>
             <p><a href="<%=cp%>/fatale/search.do?searchKey=gname&searchValue=G7">엘지 Q20</a></p>
             <p><a class="dummy">&nbsp;</a></p>
           </div>
           <div class="column">
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
           </div>
           <div class="column">
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
           </div>
           <div class="column">
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
             <p><a class="dummy">&nbsp;</a></p>
           </div>
         </div>
       </div>
     </div>
     <a href="<%=cp %>/fatale/review.do" ><font>전체 리뷰 보기</font></a>
   </div>
   
   <div class="right-sided">
   
   	<!-- 로그인 여부에 따른 상단 화면 차이 -->     
    <c:choose> 
		<c:when test="${empty sessionScope.phonmeInfo.mid }">
			<!-- 로그인 안했을때 -->
			<a href="<%=cp %>/fatale/cart.do" ><font>장바구니</font></a>
			<!-- request.getServletPath() -->
       		<a href="<%=cp %>/fatale/login.do"><font>LOGIN</font></a>
      		<a href="<%=cp %>/fatale/member.do"  ><font>회원가입</font></a>
		</c:when>
		
		<c:otherwise>
		<!-- 로그인 했을때 -->
			<a class="dummy"><font style="color: #7A7EBf;">${sessionScope.phonmeInfo.userName }님 안녕하세요</font></a>
			<a href="<%=cp %>/fatale/cart.do" ><font>장바구니</font></a>
       		<a href="<%=cp %>/fatale/logout_ok.do" ><font>LOGOUT</font></a>
      		<a href="<%=cp %>/fatale/myPage.do" ><font>마이페이지</font></a>
		</c:otherwise>
	</c:choose>	
   
       <div class="inline search-group" >
         <form action="<%=cp %>/fatale/search.do" method="get">
         	<input type="hidden" name="searchKey" value="headSearch">
            <input type="text" name="searchValue" id="search-box"size="40" >
            <input type="submit" value="검색" id="search-button">
         </form>
        </div>   
   </div>
  
  
</div>
