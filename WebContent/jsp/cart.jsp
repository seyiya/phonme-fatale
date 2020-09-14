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
	<link rel="stylesheet" href="<%=cp%>/css/cart.css" type="text/css">
	<!-- 폰트 -->
	<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@500&display=swap" rel="stylesheet">



<script type="text/javascript">

function fn_AllCheck(parantsObj){
	var obj = document.getElementsByName("cartChk");
	var obj2 = document.getElementsByName("cartNum");
	if(parantsObj.checked){
       for(var i=0; i< obj.length; i++){
	       if(obj[i].checked == false){
	    	   obj[i].checked = true;
	    	   window.price("cartChk"+obj2[i].value,"hap"+obj2[i].value);
	       }
       }
	}else{
       for(var i=0; i< obj.length; i++){
    	   if(obj[i].checked == true){
	    	   obj[i].checked = false;
	    	   window.price("cartChk"+obj2[i].value,"hap"+obj2[i].value);
	       }
       }
	}
}

function SelectDelete(checkboxName){
	var obj = document.getElementsByName(checkboxName);
	var j = 0;
	for(var i=0; i< obj.length; i++){
	       if(obj[i].checked == false){
		   j++;	    	   
	       }
	}
	if(j==obj.length){
		alert("상품을 선택해주세요.");
		return;
	}
	var f = document.myForm;
	f.action = "<%=cp%>/fatale/cart_delete.do";
	f.submit();
	
	}
	
function SelectOrder(checkboxName) {
	var obj = document.getElementsByName(checkboxName);
	var j = 0;
	for(var i=0; i< obj.length; i++){
	       if(obj[i].checked == false){
		   j++;	    	   
	       }
	}
	if(j==obj.length){
		alert("상품을 선택해주세요.");
		return;
	}
	var f = document.myForm;
	f.action = "<%=cp%>/fatale/order.do";
	f.submit();
	}
	
function AllOrder(checkboxName) {
	var obj = document.getElementsByName(checkboxName);
	for(var i=0; i< obj.length; i++){
	    obj[i].checked = true;
	}
	var f = document.myForm;
	f.action = "<%=cp%>/fatale/order.do";
	f.submit();

}

function countUpdate(countBox,cartNum) {
	var obj = document.getElementsByName(countBox);
	
	for(var i=0; i< obj.length; i++){
	       if(obj[i].value==null||obj[i].value==""){
	    	   alert("\n갯수를 입력하세요.");
	    	   obj[i].focus();
	    	   return;
	       }
	}
	var f = document.myForm;
	f.action = "<%=cp%>/fatale/cart_count_update.do?num=" + cartNum;
	f.submit();

}

function OneOrder(checkboxName,cartChk) {
	var obj = document.getElementsByName(checkboxName);
	var obj2 = document.getElementById(cartChk);
	for(var i=0; i< obj.length; i++){
		if(obj[i].value==null||obj[i].value==""){
	    	   alert("\n갯수를 입력하세요.");
	    	   obj[i].focus();
	    	   return;
	     }
		obj2.checked = true;	    	   
	    
	}
	var f = document.myForm;
	f.action = "<%=cp%>/fatale/order.do";
	f.submit();
}

function price(checkboxName,hap) {
	var ckObj = document.getElementById(checkboxName);
	var hapObj = document.getElementById(hap);
	var totPrice = document.getElementById("totPrice");
	
	var v3;
	    if(ckObj.checked == true){
	    	var v1 = Number (totPrice.value);
	    	var v2 = Number (hapObj.value);
	    	v3 = v1 + v2;
	    	totPrice.value = v3;
	    }
	    if(ckObj.checked == false){
	    	var v1 = Number (totPrice.value)
	    	var v2 = Number (hapObj.value);
	    	v3 = v1 - v2;
	    	totPrice.value = v3;
	    }
}
</script>
</head>
<body>
<!-- header -->	
<div><jsp:include page="../html/head.jsp"></jsp:include></div>


<div id="wrapper_cart">
	<form method="post" name="myForm"><br><br><br>
		<table style="font-size: 15pt; color: #888" border="0" width="1250" cellspacing="3" cellpadding="3" >
			<tr valign="top">
				<td colspan="10" align="left">
					<font style="font-size: 20pt; color: black;">&nbsp;&nbsp;장바구니</font>
					<hr><br></td>
			
			<c:if test="${empty cartLists}">
			<tr><td><br><font color="#F23D4C">담은 물건이 없습니다!</font></td>
			</c:if>
			
			<c:if test="${!empty cartLists}">
				
				<tr>
					<td>&nbsp;&nbsp;&nbsp;<input type="checkbox" id="chkAll" class="selectAllCarts" onclick="fn_AllCheck(this);"></td>
					<td colspan="8"><label for="chkAll">전체선택</label>
					<input type="hidden" name="totPrice" value="${totPrice }">
					</td>
 					<td><input align="right" type="button" value="선택삭제" onclick="SelectDelete('cartChk');" class="bokyung_button_cart1"
 								style="background-color: #555"></td></tr>
					<!-- 선 --><tr height="1"><td colspan="10" bgcolor="#cccccc"></td>
					
				<c:forEach var="cart" items="${cartLists}">
				<tr>
					<td><input type="hidden" name="cartNum" value="${cart.num}">
						<input type="hidden" value="${cart.hap}" id="hap${cart.num}"/>
					&nbsp;&nbsp;	
						<input type="checkbox" id="cartChk${cart.num}" name="cartChk" value="${cart.num}" onchange="price('cartChk${cart.num}','hap${cart.num}')"/>
					<!-- pid -->
					<td><label for="cartChk${cart.num}">${cart.pid}</label></td>
					<!-- 이미지 -->
					<td align="center"><img alt="그림" src="<%=cp%>/image/${cart.img}" width="100px" height="100px"></td>
					<!-- 상품명 -->
					<td>${cart.pname}</td>
					<!-- 수량> -->
					<td width="60"><input type="text" name="count${cart.num}" value="${cart.count}" 
							style="font-size:16pt; color: #666; vertical-align: middle;
									width: 50px; height: 45px; padding-top: 2px; text-align: center;
									color:#F23D4C; font-size: 20pt; font-family: 'Do Hyeon', sans-serif;
									vertical-align: middle;"/></td>
					<!-- 변경버튼 -->							
					<td width="70"><input type="button" value="변경" onclick="countUpdate('count${cart.num}','${cart.num}');"
								class="bokyung_button_cart_change"/></td>
					<!-- 가격 -->
					<td align="right">${cart.price}원&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<!-- 옵션 -->
					<td>${cart.gname }</td>
					<td>${cart.color }</td>
					<td width="230px">
						<input type="button" value="구매하기" class="bokyung_button_cart1"
							onclick="OneOrder('count${cart.num}','cartChk${cart.num}');" >
							
						<input type="button" value="삭제하기"  class="bokyung_button_cart1"
							onclick="location.href='<%=cp%>/fatale/cart_delete.do?num=${cart.num}'"></td></tr>
				
				<!-- 선 --><tr height="1"><td colspan="10" bgcolor="#cccccc"></td></tr>
				
					</c:forEach>
				</tr>	
				<tr valign="bottom" height="150" >	
					<td colspan="8" align="right" style="padding-bottom: 15px;">
						<font color="black">선택한 상품금액 :
						<input type="text" readonly="readonly" value="0" id="totPrice" name="totPrice" 
						style="border: 0; background-color: #DFDFEB; padding-top : 2px; padding-right: 15px;
							   width: 150px;  height: 35px;  font-size: 15pt;  font-family: 'Do Hyeon'; text-align: right;">&nbsp;&nbsp;원
						</font></td>
					<td colspan="2" align="right">
					<input type="button" value="전체주문" onclick="AllOrder('cartChk');" class="bokyung_button_cart2">
					<input type="button" value="선택주문" onclick="SelectOrder('cartChk');" class="bokyung_button_cart2">
					</td></tr>
				
			</c:if>
		</table>
	</form>
	</div>


	<br><br><br><br><br>
	<jsp:include page="../html/floating.jsp"></jsp:include>
	<jsp:include page="../html/footer.jsp"></jsp:include>
</body>
</html>