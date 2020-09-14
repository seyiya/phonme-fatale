<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();

	//회원정보 수정
	
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
	<link rel="stylesheet" href="<%=cp%>/css/member.css" type="text/css">
	<!-- 폰트 -->
	<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">

<script type="text/javascript">
	function sendIt(){
		
		var f = document.myForm;
		
	
		if(!f.pw.value){
			alert("비밀번호 항목은 필수 항목입니다. ");
			f.pw.focus();
			return;
		}
	
		
		alert("정보수정이 완료되었습니다.");
		f.action = "<%=cp%>/fatale/update_ok.do";
		f.submit();
		
	}
</script>
</head>
<body>
<!-- header jsp-->
<jsp:include page="../html/head.jsp"></jsp:include>


<center>
	<br/><br/><br/>
	
	<form name="myForm" method="post" action="">
		<table align="center" style="font-size: 13pt; color: #888"
		width="800" cellpadding="0" cellspacing="0" border="0">
			 
			<tr valign="center">
				<td colspan="3">
					<font style="font-size: 20pt; color: black; ">&nbsp;정보수정</font>
					<hr></td>
			
				
			<tr><td colspan="3"><font color="#F23D4C" style="float: right">${message }</font><br></td></tr>
			<tr height="30px"><td>&nbsp;</td></tr>
			<tr>
				<td class="bokyung_td_left">가입유형</td>
				<td><input type="radio" checked="checked" >개인회원</td>
			</tr>
			
			
			<!-- 선 --><tr height="1"><td colspan="2" bgcolor="#cccccc"></td>
			<tr>
				<td class="bokyung_td_left">아&nbsp;이&nbsp;디</td>
				<td><input type = "text"  class="bokyung_member_text" name ="mid" value="${dto.mid }" disabled="disabled">
					(영문 소문자/숫자, 4~16자)&nbsp;&nbsp;&nbsp;&nbsp; 
				</td>	
			</tr>
			
			
			<!-- 선 --><tr height="1"><td colspan="2" bgcolor="#cccccc"></td>
			<tr >
				<td class="bokyung_td_left">비밀번호</td>
				<td><input type = "password" class="bokyung_member_text" name = "pw" value="${dto.pw }">
				(영문 대소문자/숫자 4자~16자)</td>
			</tr>
			
			
			<!-- 선 --><tr height="1"><td colspan="2" bgcolor="#cccccc"></td>
			<tr >
				<td class="bokyung_td_left">이름</td>
				<td><input type = "text" class="bokyung_member_text"  name = "name" value="${dto.name }"></td>
			</tr>
			
			
			<!-- 선 --><tr height="1"><td colspan="2" bgcolor="#cccccc"></td>
			<tr >
				<td class="bokyung_td_left" >주소</td>
				<td><input type = "text" class="bokyung_member_text"  name = "addr" value="${dto.addr }"
						style="width : 500px;"></td>
			</tr>
			
			
			
			<!-- 선 --><tr height="1"><td colspan="2" bgcolor="#cccccc"></td>
			
			<tr >
				<td class="bokyung_td_left" >전화번호</td>
				<td><input type="text" name="tel"  value="${dto.tel }" class="bokyung_member_text">
				(***-****-****)</td>
			</tr>
			
			<tr >
				<td class="bokyung_td_left" >가입날짜</td>
				<td><input type = "text" class="bokyung_member_text"  name = "mCreated" value="${dto.mCreated }" disabled="disabled"></td>
			</tr>
			
			
			<tr align="center" height="100px">
			<td colspan="2"><br><br>
			<input type="button" class="bokyung_button_join" value="정보수정" 
			 onclick="javascript:sendIt();"
			style="width: 100px; height: 35px;">
			
			<input type="hidden" name="mid" value="${dto.mid }"/>
			<input type="hidden" name="name" value="${dto.name }"/>
				
		</table>
	</form>
</center>

<div style="height: 200px"></div>

<!-- floating bar jsp -->	
<jsp:include page="../html/floating.jsp"></jsp:include>
<!-- footer jsp-->
<jsp:include page="../html/footer.jsp"></jsp:include>
</body>
</html>