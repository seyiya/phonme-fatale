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
<link rel="stylesheet" href="<%=cp%>/css/style.css" type="text/css">
<script type="text/javascript">
function sendIt() {

	f = document.myForm;

	//상품코드 빈칸 체크
	str = f.pid.value;
	str = str.trim();
	if (!str) {
		alert("\n상품코드를 입력하세요.");
		f.pid.focus();
		return;
	}
	f.pid.value = str;
	
	//상품이름 빈칸 체크
	str = f.pname.value;
	str = str.trim();
	if (!str) {
		alert("\n상품이름을 입력하세요.");
		f.pname.focus();
		return;
	}
	f.pname.value = str;
	
	//이미지 빈칸 체크
	str = f.upload.value;
	str = str.trim();
	if (!str) {
		alert("\n이미지를 넣어주세요.");
		f.upload.focus();
		return;
	}
	
	//가격 빈칸 체크
	str = f.price.value;
	str = str.trim();
	if (!str) {
		alert("\n가격을 입력하세요.");
		f.price.focus();
		return;
	}
	f.price.value = str;
	
	//제품설명 빈칸 체크
	str = f.content.value;
	str = str.trim();
	if (!str) {
		alert("\n제품설명을 입력하세요.");
		f.content.focus();
		return;
	}
	f.content.value = str;
	
	//색상 빈칸 체크
	str = f.color.value;
	str = str.trim();
	if (!str) {
		alert("\n색상을 입력하세요.");
		f.color.focus();
		return;
	}
	f.color.value = str;
	
	f.action = "<%=cp%>/fatale/addProduct_ok.do";
	f.submit();

}

function deleteProduct(){
	
	f = document.myForm;
	
	f.action = "<%=cp%>/fatale/addProduct_delete.do"
	f.submit();
	
}
</script>

</head>
<body>
	
<div>
<jsp:include page="../html/head.jsp"></jsp:include>
</div>
	
		<form action="" name="myForm" method="post" enctype="multipart/form-data">
		상품코드:<input type="text" name="pid" maxlength="4" /> ex)A002 <br />
		상품이름:<input type="text" name="pname" /> <br /> 
		이미지:<input type="file" name="upload"> ex)10Mb이하 파일만 <br /> 
		가격:<input type="text"	name="price"> ex)50000<br /> 내용:
		<textarea name="content" cols="50" rows="10"></textarea> ex)안녕하세요! <br /> 
		색상:<input type="text" name="color"> ex)레드/그린/블루 <br /><br />
		
		갤럭시<br /> 
		<input type="checkbox" id="r1" name="gijong" value="GS10"><label for="r1">S10</label><br />
		<input type="checkbox" id="r2" name="gijong" value="GS9"><label for="r2">S9</label><br />
		<input type="checkbox" id="r3" name="gijong" value="GS8"><label for="r3">S8</label><br />
		<br /> 
		
		아이폰<br />
		<input type="checkbox" id="r4" name="gijong" value="IXS"><label for="r4">XS</label><br />
		<input type="checkbox" id="r5" name="gijong" value="ISE"><label for="r5">SE</label><br />
		<input type="checkbox" id="r6" name="gijong" value="I8"><label for="r6">8</label><br />
		<br /> 
		
		LG<br />
		<input type="checkbox" id="r7" name="gijong" value="LG8"><label	for="r7">G8</label><br />
		<input type="checkbox" id="r8" name="gijong" value="LG7"><label for="r8">G7</label><br />
		<input type="checkbox" id="r9" name="gijong" value="LV50"><label for="r9">V50</label><br />
		<br />
		<input type="button" value="등록하기" onclick="sendIt();">
		<input type="reset" value="리셋" onclick="document.myForm.pid.focus();">
		
		<br><br><br>
		삭제할 pid :  
		<input type="text" name="deletePid"> 
		<input type="button" value="삭제하기" onclick="deleteProduct();">
		</form>	
			
	
			
	
<jsp:include page="../html/floating.jsp"></jsp:include>
<jsp:include page="../html/footer.jsp"></jsp:include>
</body>
</html>