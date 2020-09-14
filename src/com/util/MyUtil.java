package com.util;

public class MyUtil {

	
	//��ü ������ �� ���ϱ�
	//numPerPage: �� ȭ�鿡 ǥ���� ������ ���� : 3��
	//dataCount: ��ü�������� ���� :34��
	
	public int getPageCount(int numPerPage, int dataCount) { //(3,34)
		int pageCount = 0;
		pageCount = dataCount/numPerPage; //34/3 = 11
		
		if(dataCount%numPerPage!=0) { //11%3 != 0
			pageCount++; //11+1 = 12
		}
		
		return pageCount; //�� 12������
	}
	
	
	//����¡ ó�� �޼ҵ�
	//currentPage: ���� ǥ���� ������ :9������
	//totalPage: ��ü ������ �� :12������
	//listUrl: ��ũ�� ������ url :list.jsp
	public String pageIndexList(int currentPage, int totalPage, String listUrl) {
		
		int numPerBlock = 3; //�� ȭ�鿡 ǥ���� ������ ���� : 5��
		int currentPageSetup; //ǥ���� ù ������(6) - 1 ���� ��: 5 (5,10,15,20)
		int page; //������ index ����(... 6 7 8 9 10 ...)
		
		StringBuffer sb = new StringBuffer();
		
		if(currentPage==0 || totalPage==0) { //������ ���� ���
			return "";
		}
		
		//list.jsp
		//list.jsp?searchKey="name"&searchVAlue="�����"
		if(listUrl.indexOf("?")!=-1) { //�ּҿ� ? �� �ִٸ� &�� ���δ�
			//���� ������ == ������
			listUrl = listUrl + "&";
			
		} else {
			listUrl = listUrl + "?";
		}
		
		//current ������ ���� ���ϴ� ����
			//current_page�� 9��, (9/5)*5=5
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
		
		//���� current_page�� 5,10,15,20...�̸� (10/5)*5 = 10���� ������ ���� ���ڰ��ȴ�.
		
		if(currentPage % numPerBlock == 0) { //10%5 = 0
			currentPageSetup = currentPageSetup - numPerBlock;
		}
		
		//������
		if(totalPage>numPerBlock && currentPageSetup>0) {
			
			sb.append("<a href=\""+listUrl + "pageNum="
					 + currentPageSetup + "\">������</a>&nbsp;");
			//<a href="list.jsp?pageNum=5">������</a>&nbsp
			
		}
		
		//�ٷΰ��� ������
		page = currentPageSetup + 1; //���۰�
		
		while(page<=totalPage && page<= currentPageSetup+numPerBlock) {
			
			//������������ ��ũ ���� �ʰ� ���� �ٲ��ش�.
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
		
		//������
		if(totalPage - currentPageSetup > numPerBlock) {
			
			sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">������</a>&nbsp;");
			//<a href="list.jsp?pageNum=11">������</a>&nbsp;
		}
		
		return sb.toString();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
