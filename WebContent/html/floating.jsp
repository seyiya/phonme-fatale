<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
	<!-- Floating file : 모든 jsp파일에 include시켜야함. -->	
		
		<div class="section_float" style="float:right; color: rgba(0, 0, 0, 0.5);">
		  <nav class="nav fill">  <!-- <nav class="stroke"> -->
		    <ul style="padding-left: 0px;" >
		      <li><a href="#">▲TOP</a><br></li>
		      <li><a href="<%=cp %>/fatale/qna.do">Q&amp;A</a><br></li>
		      <li><a href="<%=cp %>/fatale/notice.do">공지사항</a><br></li>
		      <li><a href="<%=cp %>/fatale/myPage.do">마이페이지</a><br></li>
		    </ul>
		  </nav><!-- nav -->
		</div><!-- section -->
	
