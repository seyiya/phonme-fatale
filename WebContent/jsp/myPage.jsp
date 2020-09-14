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
   <link rel="stylesheet" href="<%=cp%>/css/myPage.css" type="text/css">
   
   <script type="text/javascript">
   
      function toUpdate(){
         
           f=document.myForm;
         
            var str = f.pwd.value;
            var ck = f.pwdck.value;
            
            str = str.trim();
            
            if(!str){
                alert("\n비밀번호를 입력하세요!");
                f.pwd.focus();
                return;
             }
            
            if(str!=ck){
               alert("\n비밀번호가 일치하지 않습니다.");
               f.pwd.focus();
               return;
            }
             
             f.pwd.value = str;
             
             f.action = "<%=cp%>/fatale/update.do";
             f.submit();
         
      }


   </script>
</head>
<body>
<!-- header jsp-->
<jsp:include page="../html/head.jsp"></jsp:include>

<div id="myPage_wrapper">
   <div id="content">
      <h2>마이 페이지</h2>
      
      <!-- 회원정보 -->
      <div class="containter">
      
         <span class="mp_title">회원정보</span>
         <table class="table01" border="0" cellpadding="10" cellspacing="10">
            <tr>
               <th>아이디</th>
               <td>${dto.mid }</td>
            </tr>
            <tr>
               <th>이름</th>
               <td>${dto.name }</td>
            </tr>
            <tr>
               <th>연락처</th>
               <td>${dto.tel }</td>
            </tr>
            <tr>
               <th>주소</th>
               <td>${dto.addr }</td>
            </tr>
         </table> 
         
         <div class="right_float">
            <form action="" name="myForm">
               <input type="hidden" name="pwdck" value=${dto.pw }>
               비밀번호 입력: &nbsp;
               <input type="password" name="pwd" class="bokyung_mypage_text"/>&nbsp;&nbsp;
               <input type="button" value="회원정보 수정" onclick="javascript:toUpdate()" class="bokyung_mypage_button"/>
            </form>
         </div>
      
      </div>
      
      <br><br>
      <!-- 관심상품 -->
      <div class="container">
         <span class="mp_title">관심상품</span>
         <table class="table01" border="0" cellpadding="10" cellspacing="10">
            <c:forEach var="dto" items="${jjimLists }">
               <tr>
                  <th>상품번호</th><td>${dto.pid }</td>
                  <th>상품명</th><td>${dto.pname }</td>
                  <th>가격</th><td>${dto.price }</td>
                  <td><a href="<%=cp %>/fatale/jjim_delete.do?pid=${dto.pid}" class="bokyung_mypage_link_D">삭제</a></td>
               </tr>
            </c:forEach>
         </table>
         <div class="check">
            <c:if test="${empty jjimLists }">
               찜한 상품이 없습니다.
            </c:if>
         </div>
         
      </div>
      
      
      <br><br>
      <!-- 리뷰 -->
      <div class="container">
         <span class="mp_title">리뷰</span>
         <table class="table01" border="0" cellpadding="10" cellspacing="10">
            <tr align="left">
               <th width="70">리뷰번호</th>
               <th>상품명</th>
               <th width="180">제목</th>
               <th width="170">등록일</th>
               <th>내용</th>
               <th colspan="2" align="left">&nbsp;게시글 관리</th>
            </tr>
            
            <c:forEach var="dto" items="${ reviewLists }">
               <tr class="tr_white">
                  <td align="center">${dto.num }</td>
                  <td>${dto.pname }</td>
                  <td>${dto.title }</td>
                  <td>${dto.rCreated }</td>
                  <td width="350" style="padding-right: 20px">${dto.content }</td>
                  <td><a href="<%=cp %>/fatale/reviewWrite.do?num=${dto.num}" class="bokyung_mypage_link">수정</a></td>
                  <td><a href="<%=cp %>/fatale/reviewDelete.do?num=${dto.num}" class="bokyung_mypage_link_D">삭제</a></td>
               </tr>
            </c:forEach>
         </table>
         <div class="check">
            <c:if test="${empty reviewLists }">
               등록된 리뷰 게시물이 없습니다.
            </c:if>
         </div>
      </div>
      
      
      <br><br>
      <!-- Q&A -->
      <div class="container">
         <span class="mp_title">Q&amp;A</span>
         <table class="table01" border="0" cellpadding="10" cellspacing="10">
            <tr align="left">
               <th align="center">번호</th>
               <th>제목</th>
               <th>내용</th>
               <th>등록일</th>
            </tr>
            
            <c:forEach var="dto" items="${ qnaLists }">
               <tr class="tr_white">
                  <td align="center">${dto.num }</td>
                  <td>${dto.title }</td>
                  <td>${dto.content }</td>
                  <td>${dto.qcreated }</td>
               </tr>
            </c:forEach>
         </table>
         <div class="check">
            <c:if test="${empty qnaLists }">
               등록된 Q&amp;A 게시물이 없습니다.
            </c:if>
         </div>
      </div>
      
      <br><br>
      <!-- 주문내역 -->
      <div class="container">
         <span class="mp_title" id="link_order">주문내역</span>
         <table class="table01" border="0" cellpadding="10" cellspacing="10">

            <c:forEach var="dto" items="${orderdLists }">
               <c:if test="${oCreated!=dto.oCreated}">
               <tr>
                  <th>주문번호</th><td>${dto.oid}</td>
                  <th></th><td width="220" align="center">
                  		<font color="#0F168C">주문일</font>&nbsp;&nbsp;&nbsp;&nbsp;${dto.oCreated}</td>
                  <td colspan="2" align="center" style="padding-left: 25px;" >
                  		<font color="#0F168C">총가격</font>&nbsp;&nbsp;&nbsp;&nbsp;${dto.hap}</td>
                  <th align="center">리뷰 관리</th>
               </tr>
               </c:if>
               <tr class="tr_white">
                  <td style="padding: 5px;"><a href="<%=cp %>/fatale/article.do/?pid=${dto.pid}&pageNum=1&searchKey=gname&searchValue=${dto.gid}"><img alt="그림" src="<%=cp%>/image/${dto.img}"
                  width="100" height="100"></a></td>
                  <td colspan="2">${dto.pname}</td>
                  <td align="center">${dto.count}개</td>
                  <td align="right" style="padding-right: 35px">${dto.price}원</td>
                  <td>${dto.color}</td>
                  <td align="center">
                  <a href="<%=cp %>/fatale/review.do?searchKey=pname&searchValue=${dto.pname}" class="bokyung_mypage_link_see">리뷰 보기</a><br/>
                  <a href="<%=cp %>/fatale/reviewWrite.do?odNum=${dto.num}" class="bokyung_mypage_link">리뷰 쓰기</a>
                  </td>
               </tr>
               <c:set var="oCreated" value="${dto.oCreated}"/>
            </c:forEach>
         </table>
         <div class="check">
            <c:if test="${empty qnaLists }">
               등록된 주문내역이 없습니다.
            </c:if>
         </div>
      </div>
   
   
   
   </div>
</div>

<!-- floating bar jsp -->   
<jsp:include page="../html/floating.jsp"></jsp:include>
<!-- footer jsp-->
<jsp:include page="../html/footer.jsp"></jsp:include>
</body>
</html>