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
<title>Insert title here</title>
</head>
<body>

<h2>Admin Links</h2>

<ul>
	<li><a href="<%=cp %>/fatale/addProduct.do">상품 등록하기</a></li>
	<li><a href="<%=cp %>/fatale/addNotice.do">공지 등록하기</a></li>
</ul>

</body>
</html>