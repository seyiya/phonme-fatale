package com.util;

public class MyUtil {

	
	//전체 페이지 수 구하기
	//numPerPage: 한 화면에 표시할 데이터 개수 : 3개
	//dataCount: 전체데이터의 갯수 :34개
	
	public int getPageCount(int numPerPage, int dataCount) { //(3,34)
		int pageCount = 0;
		pageCount = dataCount/numPerPage; //34/3 = 11
		
		if(dataCount%numPerPage!=0) { //11%3 != 0
			pageCount++; //11+1 = 12
		}
		
		return pageCount; //총 12페이지
	}
	
	
	//페이징 처리 메소드
	//currentPage: 현재 표시할 페이지 :9페이지
	//totalPage: 전체 페이지 수 :12페이지
	//listUrl: 링크를 설정할 url :list.jsp
	public String pageIndexList(int currentPage, int totalPage, String listUrl) {
		
		int numPerBlock = 3; //한 화면에 표시할 데이터 개수 : 5개
		int currentPageSetup; //표시할 첫 페이지(6) - 1 해준 값: 5 (5,10,15,20)
		int page; //페이지 index 숫자(... 6 7 8 9 10 ...)
		
		StringBuffer sb = new StringBuffer();
		
		if(currentPage==0 || totalPage==0) { //데이터 없을 경우
			return "";
		}
		
		//list.jsp
		//list.jsp?searchKey="name"&searchVAlue="배수지"
		if(listUrl.indexOf("?")!=-1) { //주소에 ? 가 있다면 &을 붙인다
			//없지 않으면 == 있으면
			listUrl = listUrl + "&";
			
		} else {
			listUrl = listUrl + "?";
		}
		
		//current 페이지 블럭을 구하는 공식
			//current_page가 9면, (9/5)*5=5
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
		
		//만약 current_page가 5,10,15,20...이면 (10/5)*5 = 10으로 원래와 같은 숫자가된다.
		
		if(currentPage % numPerBlock == 0) { //10%5 = 0
			currentPageSetup = currentPageSetup - numPerBlock;
		}
		
		//◀이전
		if(totalPage>numPerBlock && currentPageSetup>0) {
			
			sb.append("<a href=\""+listUrl + "pageNum="
					 + currentPageSetup + "\">◀이전</a>&nbsp;");
			//<a href="list.jsp?pageNum=5">◀이전</a>&nbsp
			
		}
		
		//바로가기 페이지
		page = currentPageSetup + 1; //시작값
		
		while(page<=totalPage && page<= currentPageSetup+numPerBlock) {
			
			//현재페이지엔 링크 걸지 않고 색만 바꿔준다.
			if(page==currentPage) {
				sb.append("<font color=\"Fuchsia\">" + page + "</font>&nbsp;");
				//<font color="Fuchsia">6</font>&nbsp;
			} else {
				sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">"
						+ page + "</a>&nbsp;");
				//<a href="list.jsp?pageNum=7">7</a>&nbsp;
				
			}
			page++;
		}
		
		//다음▶
		if(totalPage - currentPageSetup > numPerBlock) {
			
			sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">다음▶</a>&nbsp;");
			//<a href="list.jsp?pageNum=11">다음▶</a>&nbsp;
		}
		
		return sb.toString();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
