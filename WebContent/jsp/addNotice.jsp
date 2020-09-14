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
<title>공지사항 등록</title>
<script type="text/javascript">

	function sendIt(){
		
		f = document.myForm;

		/* 1 */
		str = f.ntitle.value;
		str = str.trim();

		if(!str){
			alert("\n제목을 입력하세요.");
			f.ntitle.focus();
			return;
		}
		f.ntitle.value = str;

		/* 2 */
		str = f.ncontent.value;
		str = str.trim();
		
		if(!str){
			alert("\n내용을 입력하세요.");
			f.ncontent.focus();
			return;
		}
		f.ncontent.value= str;

		/* 가상의 주소 */
		f.action = "<%=cp%>/fatale/addNotice_ok.do"; 
		f.submit();
		
	}

</script>
</head>
<body>

<h2>공지사항을 등록해주세요</h2>

<br><br>

<form action="addNotice.do" name="myForm">

<table>
	<tr>
		<td>공지제목</td>
		<td><input type="text" name=ntitle size="74" maxlength="100" /></td>
	</tr>
	
	<tr>
		<td>내용</td>
		<td><textarea name="ncontent" rows="12" cols="63" ></textarea></td>
	</tr>
<!-- 	
	<tr>
		<td>패스워드</td>
		<td><input type="password" name=subject size="74" maxlength="100" /></td>
	</tr> -->


</table>

<input type="button" value="등록하기" onclick="sendIt();"/>
<input type="reset" value="다시입력"  
				onclick="document.myForm.ntitle.focus();"/>
<br/><br/>
<input type="button" value="공지 목록" onclick="javascript:location.href='<%=cp %>/fatale/notice.do'" >

</form>

</body>
</html>