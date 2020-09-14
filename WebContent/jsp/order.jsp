<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
 	request.setCharacterEncoding("UTF-8");
 	String cp = request.getContextPath();
%> 	


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>주문 정보</title>
 
 
 	<!-- 파비콘 -->
	<link rel="shortcut icon" href="<%=cp %>/image/favicon.ico">
	<link rel="icon" href="<%=cp %>/image/favicon.ico">
	<!-- css -->
	<link rel="stylesheet" href="<%=cp%>/css/style.css" type="text/css">
	<link rel="stylesheet" href="<%=cp%>/css/order.css" type="text/css">
	<!-- 폰트 -->
	<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@500&display=swap" rel="stylesheet">
 	

 <script type="text/javascript">
 
 	
 
 	function textCopy(){
		
 		var cvalue;
 		cvalue = document.myForm;
 	 
 		cvalue.name1.value = cvalue.name.value;
 		cvalue.addr1.value = cvalue.addr.value;
		cvalue.tel1.value = cvalue.tel.value;
		
	}
 
 	function emailCheck(){
 		var f = document.myForm;
 		
 		var clen = f.emailaddr.options.length;
 		var cvalue = f.emailaddr.selectedIndex;
 		
 		f.email2.value = "";
 		
 		if(cvalue==(clen-1)){
 			
 			f.email2.readonly = false;
 			
 		}else{
 			
 			f.email2.value = f.emailaddr.options[cvalue].value;
 			f.email2.readOnly = true; 
 		}
 	}
 	function resetInfo(){

 		var cvalue;
 		cvalue = document.myForm;
 		
 		cvalue.name1.value = "";
 		cvalue.addr1.value = "";
 		cvalue.tel1.value = "";
 	
 		 		
 	}	
	
 	function sendIt(){
		
		var f = document.myForm;
		
		if(!f.name.value){
			alert("주문정보 이름 입력!!");
			f.name.focus();
			return;
		}
		
		if(!f.addr.value){
			alert("주문정보 주소 입력!!");
			f.addr.focus();
			return; 
		}
		
		if(!f.tel.value){
			alert("주문정보 전화번호 입력!!");
			f.tel.focus();
			return; 
		}
		
		if(!f.name1.value){
			alert("배송정보 이름 입력!!");
			f.name1.focus();
			return; 
		}
		
		if(!f.addr1.value){
			alert("배송정보 주소 입력!!");
			f.addr1.focus();
			return; 
		}
		
		if(!f.tel1.value){
			alert("배송정보 전화번호 입력!!");
			f.tel1.focus();
			return; 
		}
		
		alert("결제가 완료되었습니다"); 
		
		f.action = "<%=cp%>/fatale/order_ok.do";
	
		f.submit();
		
		
	}
 		
 		
 	
 
 
 
 
 </script>
 
 
 </head>
 <body>
 <!-- header -->
<div><jsp:include page="../html/head.jsp"></jsp:include></div>
   
   
   
   <div id="wrapper_cart">
	<form method="post" name="myForm"><br><br><br>
	
	
		<!-- 주문한 상품 테이블 -->
		<table style="font-size: 15pt; color: #888" border="0" width="1250" cellspacing="3" cellpadding="3" >
			<tr valign="top">
				<td colspan="10" align="left">
					<font style="font-size: 20pt; color: black;">&nbsp;&nbsp;주문하기</font>
					<hr></td>
				
				<c:forEach var="cart" items="${lists}">
				<tr>
					<td>&nbsp;&nbsp;&nbsp;
						<input type="hidden" name="num" value="${cart.num}" />
						<input type="hidden" name="color" value="${cart.color}" /></td>
					<!-- pid -->
					<td>${cart.pid}</td>
					<!-- 이미지 -->
					<td align="center"><img alt="그림" src="<%=cp%>/image/${cart.img}" width="100px" height="100px"></td>
					<!-- 상품명 -->
					<td>${cart.pname}</td>
					<!-- 수량> -->
					<td width="60"><input type="text" name="count${cart.num}" value="${cart.count}" disabled="disabled" 
							style="font-size:16pt; color: #666; vertical-align: middle;
									width: 50px; height: 45px; padding-top: 2px; text-align: center;
									color:#F23D4C; font-size: 20pt; font-family: 'Do Hyeon', sans-serif;
									vertical-align: middle;"/></td>
					<td width="200" align="right">개당 ${cart.price}원&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<!-- 가격 -->
					<td align="right">${cart.count }개 :  ${cart.hap}원&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<!-- 옵션 -->
					<td>${cart.gname }</td>
					<td>${cart.color } 
						<input type="hidden" name="gid" value="${cart.gid}"> 
						<input type="hidden" name="pid" value="${cart.pid}"> 
						<input type="hidden" name="count" value="${cart.count}">
					</td>
				</tr>
				
				<!-- 선 --><tr height="1"><td colspan="10" bgcolor="#cccccc"></td></tr>
				
					</c:forEach>
				</tr>	
				<%-- <tr valign="bottom" height="150" width="600">	
					<td colspan="8" align="right"><font color="black">총금액 : ${totPrice}원&nbsp;&nbsp;&nbsp;</font></td>
					<td colspan="2" align="right">
					<input type="button" value="전체주문" onclick="AllOrder('cartChk');" class="bokyung_button_cart2">
					<input type="button" value="선택주문" onclick="SelectOrder('cartChk');" class="bokyung_button_cart2">
					</td></tr> --%>
		</table><br><br><br><br>
	
		<!-- 주문정보 테이블 -->
		<table style="font-size: 15pt; color: #888" border="0" width="1250" cellspacing="3" cellpadding="3">   
      
		     
		     <tr>
		     	<td class="bokyung_order_table_left" colspan="2"><font style="font-size: 20pt; color: black;">주문 정보</font></td>
		     	<td align ="right" width="87%"><font color="#F23D4C">*</font>&nbsp;필수입력사항&nbsp;&nbsp;</td>
		     
		     <!-- 선 --><tr height="1"><td colspan="3" bgcolor="#888"></td></tr>     
		     
		      
		      <tr>
		         <td class="bokyung_order_table_left" >주문하시는 분</td>
		         <td><font color="#F23D4C">*</font></td>
		         <td>
		         	<input type="text" name="name" value="${dto.name }" class="bokyung_order_text"></td>
		      </tr>
		       <tr>
		         <td class="bokyung_order_table_left">주소</td>
		         <td><font color="#F23D4C">*</font></td>
		         <td>
		         	<input type="text" name= "addr" size = "60" value="${dto.addr }" class="bokyung_order_text"></td>
		       </tr>
		       <tr>
		         <td class="bokyung_order_table_left">전화번호</td>
		         <td><font color="#F23D4C">*</font></td>
		         <td>
		         	<input type="text" name="tel" size = "20" value="${dto.tel }" class="bokyung_order_text">
		          </td>
		       </tr>
		        <tr>
		          <td class="bokyung_order_table_left" colspan="2">이메일</td>
		          <td>
		            <input type="text" name="email1" class="bokyung_order_text">@
		            <input type="text" name="email2" readonly="readonly" class="bokyung_order_text">
		              <select name="emailaddr" onchange="emailCheck();" class="bokyung_order_text" style="width: 150px">
		                 <option value="">선택</option>
		                 <option value="daum.net">daum.net</option>
		                 <option value="empal.com">empal.com</option>
		                 <option value="gmail.com">gmail.com</option>
		                 <option value="hanmail.net">hanmail.net</option>
		                 <option value="msn.com">msn.com</option>
		                 <option value="naver.com">naver.com</option>
		                 <option value="nate.com">nate.com</option>
		              </select>
		            </td>
		         </tr>
		</table><br><br><br>
		
		
		
		<!-- 배송정보 테이블 -->
		<table style="font-size: 15pt; color: #888" border="0" width="1250" cellspacing="3" cellpadding="3">   
		     <tr>
		     	<td class="bokyung_order_table_left" colspan="2"><font style="font-size: 20pt; color: black;">배송 정보</font></td>
		     	<td align ="right" width="87%"><font color="#F23D4C">*</font>&nbsp;필수입력사항&nbsp;&nbsp;</td>
		     <!-- 선 --><tr height="1"><td colspan="3" bgcolor="#888"></td></tr>     


		      <tr>
		         <td class="bokyung_order_table_left" >배송지 선택</td>
		         <td><font color="#F23D4C">*</font></td>
		         <td>
			         <input id="r1" type="radio" name="raddr" onclick="textCopy();">
			         <label for="r1">주문자 정보와 동일</label>
			         <input id="r2" type="radio" name="raddr" onclick="resetInfo();">
			         <label for="r2">새로운 배송지</label></td>
		      </tr>
		       <tr>
		         <td class="bokyung_order_table_left">주문하시는 분</td>
		         <td><font color="#F23D4C">*</font></td>
		         <td>
		         	<input type="text" name="name1" class="bokyung_order_text"></td>
		       </tr>
		       <tr>
		         <td class="bokyung_order_table_left">주소</td>
		         <td><font color="#F23D4C">*</font></td>
		         <td>
		         	<input type="text" name= "addr1" size = "60" class="bokyung_order_text">     
         			<input type="hidden" name= "totprice" value="${totPrice}">
		          </td>
		       </tr>
		       <tr>
		         <td class="bokyung_order_table_left">전화번호</td>
		         <td><font color="#F23D4C">*</font></td>
		         <td>
		         	<input type="text" name="tel1" size = "20" class="bokyung_order_text">
		          </td>
		       </tr>
		       <tr><td colspan="3" height="7px"></td></tr>
		        <tr valign="top">
		          <td class="bokyung_order_table_left" colspan="2">배송메세지</td>
		          <td>
		          	<textarea class="bokyung_order_text" style="width: 500px; height: 100px;"></textarea> 
		          </td>
		         </tr>
		</table><br><br><br>  
	
			
		
		<!-- 결제 예정 테이블 -->
		<table style="font-size: 15pt; color: #888" border="0" width="1250" cellspacing="3" cellpadding="3">   
		     <tr>
		     	<td class="bokyung_order_table_left" colspan="2"><font style="font-size: 20pt; color: black;">결제예정 금액</font></td>
		     	<td align ="right" width="87%"></td>
		     </tr>
		</table>
		
		<table style="font-size: 15pt; color: #888;" border="0" width="1250" cellspacing="3" cellpadding="3">
		      <tr align="center" style="background-color: #0F168C; color: white; height: 70px">
		         <td width="">총 주문 금액</td>
		         <td width="33.3%">총 할인 + 부가결제 금액</td>
		         <td width="33.3%">총 결제예정 금액</td>
		      </tr>
		      <tr align="center" style=" background-color:#eeeeee; color:#F23D4C; height: 70px; font-size: 22pt">
		      	<td>${totPrice }원</td>
		      	<td>0원</td>
		      	<td>${totPrice }원</td>
		      </tr>
		      <tr height="100px" valign="bottom">
		      	<td colspan="3" align="center"><input type="button" onclick="sendIt();"
		      		class="bokyung_button_join" value="결제하기"></td>
		      </tr>
		</table><br><br><br><br><br>   
	
	
	</form>
	</div>
   

          
 <jsp:include page="../html/floating.jsp"></jsp:include>
<jsp:include page="../html/footer.jsp"></jsp:include>
</body>
</html>
