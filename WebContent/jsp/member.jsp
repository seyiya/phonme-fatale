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
	<link rel="stylesheet" href="<%=cp%>/css/member.css" type="text/css">
	<!-- 폰트 -->
	<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<script type="text/javascript">
	function checkIt(){
		
		f = document.myForm;
		
		if(!f.mid.value){
			alert("아이디를 입력해주세요.");
			f.mid.focus();
			return;
		}
		
		
		f.action = "<%=cp%>/fatale/idCheck_ok.do";
		f.submit();
	}

	function sendIt() {
		f = document.myForm;
		
		var cc1 = /^[a-z0-9]{4,16}$/;
		var cc2 = /^[A-Za-z0-9]{4,16}$/;
	 	var cc3 = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-[0-9]{3,4}-[0-9]{4}$/;
		
	 	if(!cc1.test(f.mid.value)) {
	
			alert('아이디 영문소문자/숫자 4~16자 이내로 입력하세요.');
	
			f.mid.focus();
			
			return false;
	 	}
	 	
	 	if(!cc2.test(f.pw.value)) {
	
			alert('패스워드 영문 대소문자/숫자 4~16자 이내로 입력하세요.');
	
			f.pw.focus();
			
			return false;
	 	}
	 	
	 	if(!f.pw2.value){
	 		alert("비밀번호를 한번 더 입력하세요.");
	 		f.pw2.value.focus();
	 	}
	 	

	 	if(f.pw.value != f.pw2.value){
	 		alert('비밀번호가 일치하지 않습니다.');
	 		
	 		f.pw.value="";
	 		f.pw2.value="";
	 		f.pw.value.focus();
	 	}
	 	
	 	if(!f.name.value){
			alert("이름을 입력하세요.");
			f.userPwd.focus();
			return; 
		}
	 	
	 	if(!f.addr.value){
			alert("주소를 입력하세요.");
			f.userPwd.focus();
			return; 
		}
	 	
	 	if(!cc3.test(f.tel.value)) {
	
			alert('전화번호를 바르게 입력하세요');
	
			f.tel.focus();
			
			return false;
	 	}
	 	
	 	
	 	
	 	alert("회원가입이 성공적으로 완료되었습니다.");
		f.action="<%=cp%>/fatale/member_ok.do";
		f.submit();

	}

	//회원가입시 아이디 input조건  영문소문자/숫자 4~16자 이내 	
	function id_check(mid) {

		var regId = /^[a-z0-9]{4,16}$/;

		if (!regId.test(mid.value)) {

			alert('아이디 영문소문자/숫자 4~16자 이내로 입력하세요.');

			mid.focus();

			return false;
		}

		return true;
	}
	//회원가입시 패스워드 input조건  영문대소문자/숫자 4~16자 이내 	
	function pw_check(pw) {

		var regId = /^[A-Za-z0-9]{4,16}$/;

		if (!regId.test(pw.value)) {

			alert('패스워드 영문 대소문자/숫자 4~16자 이내로 입력하세요.');

			pw.focus();

			return false;

		}

		return true;

	}
	
	//회원가입시 전화번호 input 조건 : XXX-XXXX-XXXX 이런식으로
	function tel_check(tel) {

		var regTel = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-[0-9]{3,4}-[0-9]{4}$/;

		if (!regTel.test(tel.value)) {

			alert('올바른 전화번호를 입력하세요. ex)xxx-xxxx-xxxx');

			tel.focus();

			return false;

		}

		return true;

	}
</script>
</head>

<body>
<!-- header jsp-->
<jsp:include page="../html/head.jsp"></jsp:include>

<center>
	<br/><br/><br/>
	
	<form name="myForm">
		<table align="center" style="font-size: 13pt; color: #888"
		width="800" cellpadding="0" cellspacing="0" border="0">
			 
			<tr valign="center">
				<td colspan="3">
					<font style="font-size: 20pt; color: black; ">&nbsp;회원가입</font>
					<hr></td>
			
				
			<tr>
				<td colspan="3"><font color="#F23D4C" style="float: right">${message }</font><br>
				<input type="hidden" name="msg" value=${message }>
				</td>
			</tr>
			<tr height="30px"><td>&nbsp;</td></tr>
			<tr>
				<td class="bokyung_td_left">가입유형</td>
				<td><input type="radio" checked="checked" >개인회원</td>
			</tr>
			
			
			<!-- 선 --><tr height="1"><td colspan="2" bgcolor="#cccccc"></td>
			<tr>
				<td class="bokyung_td_left">아&nbsp;이&nbsp;디</td>
				<td><input type = "text"  class="bokyung_member_text" name ="mid" onchange="id_check(mid);" value=${mid }>
					(영문 소문자/숫자, 4~16자)&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" class="bokyung_button_check" value="아이디 중복확인"  
					onclick="checkIt();">
					<input type="hidden" name="dup_chk" value="0">
				</td>	
			</tr>
			
			
			<!-- 선 --><tr height="1"><td colspan="2" bgcolor="#cccccc"></td>
			<tr >
				<td class="bokyung_td_left">비밀번호</td>
				<td><input type = "password" class="bokyung_member_text" name = "pw" onchange = "pw_check(pw);">
				(영문 대소문자/숫자 4자~16자)</td>
			</tr>
			
			<!-- 선 --><tr height="1"><td colspan="2" bgcolor="#cccccc"></td>
			<tr >
				<td class="bokyung_td_left">비밀번호 확인</td>
				<td><input type = "password" class="bokyung_member_text" name = "pw2" onchange = "pw_check(pw);">
				(영문 대소문자/숫자 4자~16자)</td>
			</tr>
			
			
			<!-- 선 --><tr height="1"><td colspan="2" bgcolor="#cccccc"></td>
			<tr >
				<td class="bokyung_td_left">이름</td>
				<td><input type = "text" class="bokyung_member_text"  name = "name"></td>
			</tr>
			
			
			<!-- 선 --><tr height="1"><td colspan="2" bgcolor="#cccccc"></td>
			<tr >
				<td class="bokyung_td_left" >주소</td>
				<td><input type = "text" class="bokyung_member_text"  name = "addr"
						style="width : 500px;"></td>
			</tr>
			
			
			
			<!-- 선 --><tr height="1"><td colspan="2" bgcolor="#cccccc"></td>
			
			<tr >
				<td class="bokyung_td_left" >전화번호</td>
				<td><input type="text" name="tel" class="bokyung_member_text" onchange = "tel_check(tel);">
				(***-****-****)</td>
			</tr>
			
			<tr align="center" height="100px">
			<td colspan="2"><br><br>
			<input type="button" class="bokyung_button_join" value="가입하기" 
			 onclick="javascript:sendIt();"
			style="width: 100px; height: 35px;">
			
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