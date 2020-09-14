/**
*	<������ ����>
*	index.do - ���� ������ (index.jsp)
*	adminPages.do - ������ ������ (adminPages.jsp)
*	addNotice.do - ������ ���� ��� (addNotice.jsp)
*	addNotice_ok.do - ������ ���� ���
*	addProduct.do - ������ ��ǰ �߰� (addProduct.jsp)
*	addProduct_ok.do - ������ ��ǰ �߰�
*	addProduct_delete.do - ������ ��ǰ ����
*	article.do - ��ǰ �󼼺��� (article.jsp)
*	article_ok.do - ��ǰ �󼼺���
*	cart.do - ��ٱ��� (cart.jsp)
*	cart_count_update.do - ��ٱ��� ���� ������Ʈ
*	cart_delete.do - ��ٱ��� ����
*	search.do - ��ǰ �˻� (search.jsp)
*	member.do  - ȸ������ (member.jsp)
*	member_ok.do  - ȸ������
*	login.do - �α��� (login.jsp)
*	login_ok.do - �α���
*	logout_ok.do - �α׾ƿ�
*	myPage.do - ���������� (myPage.jsp)
*	update.do - ȸ���������� (update.jsp)
*	update_ok.do - ȸ����������
*	notice.do - ���� ���� (notice.jsp)
*	jjim.do - �� ȭ�� (myPage.jsp)
*	jjim_ok.do - �� �߰�
*	jjim_delete.do - �� ����
*	review.do - ���� ȭ�� (review.jsp)
*	deleteReview_ok.do - �ۼ��� ���� ���� (myPage.jsp)
*	reviewWrite.do - ������ ��ǰ �����ۼ� ȭ�� ��� (reviewWrite.jsp)
*	reviewWrite_ok.do - �����ۼ� �Ϸ�
*	reviewUpdate.do - ���� ���� (reviewWrite.jsp)
*	reviewDelete.do - ���� ����
*	qna.do: qna��� (qna.jsp)
*	qnaWrite.do - Q&A �۾��� �� (qnaWrite.jsp)qnaWrite_ok.do - Q&A �۾��� ������Ʈ
*	idSearch.do - ���̵� ã��(idSearch.jsp)
*	idSearch_ok.do - ���̵� ã��
*	pwSearch.do - ��й�ȣ ã�� (pwSearch.jsp)
*	pwSearch_ok.do - ��й�ȣ ã��	
*	idCheck_ok.do - ���̵� �ߺ�Ȯ��
*	 order.do - �ֹ��ϱ� (order.jsp)
*	order_ok.do - �ֹ��Ϸ� ������
*
**/
package com.phonme;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.util.DBCPConn;
import com.util.FileManager;
import com.util.MyUtil;

public class PhonmeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void forward(HttpServletRequest req, HttpServletResponse resp, String url)
			throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String cp = req.getContextPath();

		Connection conn = DBCPConn.getConnection();
		PhonmeDAO dao = new PhonmeDAO(conn);
		MyUtil myUtil = new MyUtil();

		String uri = req.getRequestURI();
		String url;

		String path = cp + "/WebContent/image";

		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		

//index.do - ����������
		if (uri.indexOf("index.do") != -1) {

			String imagePath = cp + "/image";
			req.setAttribute("imagePath", imagePath);

			int count = 0;
			req.setAttribute("count", count);

			url = "/index.jsp";
			forward(req, resp, url);

		} // end of index.do

		
//adminPages.do - ������ ������
		else if (uri.indexOf("adminPages.do") != -1) {

			url = "/jsp/adminPages.jsp";
			forward(req, resp, url);

		} // end of adminPages.do
		

//addNotice.do - ������ ���� ���
		else if (uri.indexOf("addNotice.do") != -1) {

			url = "/jsp/addNotice.jsp";
			forward(req, resp, url);

		} // end of addNotice.do
		

//addNotice_ok.do - ������ ���� ���
		else if (uri.indexOf("addNotice_ok.do") != -1) {

			PhonmeDTO dto = new PhonmeDTO();

			int maxNum = dao.getNoticeMaxNum();
			String ntitle = req.getParameter("ntitle");
			String ncontent = req.getParameter("ncontent");

			dto.setNum(maxNum + 1);
			dto.setNtitle(ntitle);
			dto.setNcontent(ncontent);

			dao.insertNotice(dto);

			url = "/jsp/addNotice.jsp";
			forward(req, resp, url);

		} // end of addNotice_ok.do
		

// addProduct.do - ������ ��ǰ �߰�
		else if (uri.indexOf("addProduct.do") != -1) {

			url = "/jsp/addProduct.jsp";
			forward(req, resp, url);

		} // end of addProduct.do
		

// addProduct_ok.do - ������ ��ǰ �߰�	
		else if (uri.indexOf("addProduct_ok.do") != -1) {

			String encType = "UTF-8";
			int maxSize = 10 * 1024 * 1024;

			MultipartRequest mr = new MultipartRequest(req, path, maxSize, encType, new DefaultFileRenamePolicy());
			String[] gijong = mr.getParameterValues("gijong");
			if (mr.getFile("upload") != null) {

				PhonmeDTO dto = new PhonmeDTO();

				dto.setPid(mr.getParameter("pid"));
				dto.setPname(mr.getParameter("pname"));
				dto.setImg(mr.getFilesystemName("upload"));
				dto.setPrice(Integer.parseInt(mr.getParameter("price")));
				dto.setContent(mr.getParameter("content"));
				dto.setColor(mr.getParameter("color"));

				dao.insertProduct(dto);
				
				int mappingMaxNum = dao.getMappingMaxNum();
				
				dao.insertMapping(dto, gijong, mappingMaxNum);
			}

			url = cp + "/fatale/search.do";
			resp.sendRedirect(url);

		} // end of addProduct_ok.do

		
// addProduct_delete.do - ������ ��ǰ ����
		else if (uri.indexOf("addProduct_delete.do") != -1) {

			String encType = "UTF-8";
			int maxSize = 10 * 1024 * 1024;
			MultipartRequest mr = new MultipartRequest(req, path, maxSize, encType, new DefaultFileRenamePolicy());

			String pid = mr.getParameter("deletePid");

			PhonmeDTO dto = dao.getReadProduct(pid);

			// �������� ����
			FileManager.doFileDelete(dto.getImg(), path);

			// ���̺����� ����
			dao.deleteProduct(pid);

			url = cp + "/fatale/addProduct.do";
			resp.sendRedirect(url);

		} // end of addProduct_delete.do
		

// article.do - ��ǰ �󼼺���
		else if (uri.indexOf("article.do") != -1) {
			
			// �ش� �Խù��� ���̽� ���̵� ����
			String pid = req.getParameter("pid");
			// ������ �� �Խù��� ������ ��ȣ
			String pageNum = req.getParameter("pageNum");
			// �˻����� ��쿡 ���� ����
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");
			// �˻����� ������ �⺻���� �ʱ�ȭ
			if (searchValue != null) {
				if (req.getMethod().equalsIgnoreCase("GET")) {
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}
			} else {// �˻��� �� ���� ��
				searchKey = "pname";
				searchValue = "";
			}

			// �� ��������
			PhonmeDTO dto = dao.getReadProduct(pid);
			if (dto == null) {

				url = cp + "/fatale/search.do";
				resp.sendRedirect(url);
				return;
			}
			// ��ȸ�� ����
			dao.updateHit(pid);
			// ȣȯ�Ǵ� ���� ��������
			List<PhonmeDTO> gijong = dao.getArticleOptions(pid);
			// ���� �и�
			String[] color = dto.getColor().split("/");
			// ������ �� �������� ���� ���� ����
			String params = "pageNum=" + pageNum;
			if (searchValue != null) {
				params += "&searchKey=" + searchKey;
				params += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}

			// ��ü ������ ���ϱ�(�����ؾ���)
			List<PhonmeDTO> lists = dao.getProductLists(1, 10, "pname", "");

			// �̹������
			String imagePath = cp + "/image";

			// �ʿ��� �� �����
			req.setAttribute("dto", dto);
			req.setAttribute("params", params);
			req.setAttribute("color", color);
			req.setAttribute("lists", lists);
			req.setAttribute("gijong", gijong);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("searchKey", searchKey);
			req.setAttribute("searchValue", searchValue);
			req.setAttribute("imagePath", imagePath);
			// �̵�
			url = "/jsp/article.jsp";
			forward(req, resp, url);

		} // end of article.do
		

// article_ok.do - ��ǰ �󼼺���
		else if (uri.indexOf("article_ok.do") != -1) {

			HttpSession session = req.getSession();
			PhonmeInfo info = (PhonmeInfo) session.getAttribute("phonmeInfo");

			String mid = info.getMid();
			String pid = req.getParameter("pid");
			String gid = req.getParameter("gid");
			int count = Integer.parseInt(req.getParameter("count"));
			String color = req.getParameter("color");
			int cartMaxNum = dao.getCartMaxNum();

			PhonmeDTO dto = dao.getReadProduct(pid);
			dto.setNum(cartMaxNum + 1);
			dto.setCount(count);
			dto.setColor(color);
			dto.setMid(mid);
			dto.setGid(gid);

			// ��ٱ��� �߰�
			dao.insertCart(dto);

			resp.setContentType("text/html; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println("alert('��ٱ��Ͽ� ��ҽ��ϴ�!');");
			out.println("history.back();");
			out.println("</script>");

			out.close();
			// �̵�����
			return;

		} // end of article_ok.do
		

// cart.do - ��ٱ���
		else if (uri.indexOf("cart.do") != -1) {

			String mid = null;
			HttpSession session = null;
			Object ob = null;

			session = req.getSession();
			ob = session.getAttribute("phonmeInfo");
			PhonmeInfo info = (PhonmeInfo) ob;
			mid = info.getMid();

			// �ش� ������ ��ٱ��� ��� ȣ��
			List<PhonmeDTO> cartLists = dao.getListCart(mid);

			int totPrice = 0;
			for (PhonmeDTO dto : cartLists) {
				totPrice += dto.getHap();
			}

			// �ʿ��� ���� �����
			req.setAttribute("mid", mid);
			req.setAttribute("cartLists", cartLists);
			req.setAttribute("totPrice", totPrice);
			
			// �̵�
			url = "/jsp/cart.jsp";
			forward(req, resp, url);

		} // end of cart.do
		

// cart_count_update.do - ��ٱ��� ���� ������Ʈ
		else if (uri.indexOf("cart_count_update.do") != -1) {
			String Snum = req.getParameter("num");
			int num = Integer.parseInt(req.getParameter("num"));
			int count = Integer.parseInt(req.getParameter("count" + Snum));

			dao.updateCartCount(num, count);

			// �̵�
			url = cp + "/fatale/cart.do";
			resp.sendRedirect(url);

		} // end of cart_count_update.do
		

// cart_delete.do - ��ٱ��� ����
		else if (uri.indexOf("cart_delete.do") != -1) {
			String chkNum = req.getParameter("cartChk");
			String[] chkNums = req.getParameterValues("cartChk");
			String num = req.getParameter("num");
			if (chkNums != null) {
				int[] numI = new int[chkNums.length];
				for (int i = 0; i < chkNums.length; i++) {
					numI[i] = Integer.parseInt(chkNums[i]);
				}
				dao.deleteCart(numI);
			} else if (chkNum != null) {
				int numI = Integer.parseInt(chkNum);
				dao.deleteCart(numI);
			} else if (num != null) {
				int numI = Integer.parseInt(num);
				dao.deleteCart(numI);
			}
			url = cp + "/fatale/cart.do";
			resp.sendRedirect(url);

		} // end of cart_delete.do
		

// search.do - ��ǰ �˻�  
		else if (uri.indexOf("search.do") != -1) {

			String pageNum = req.getParameter("pageNum");

			int currentPage = 1;

			if (pageNum != null) {
				currentPage = Integer.parseInt(pageNum);
			} else {
				pageNum = "1";
			}
			// �˻�----------------------------------
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue").replaceAll(" ", "").toUpperCase();
			if (searchValue != null) {// �˻��� ������
				if (req.getMethod().equalsIgnoreCase("GET")) {
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}
			} else {// �˻��� ��������
				searchKey = "pname";
				searchValue = "";
			}
			// -------------------------------------

			// ��ü������ ����
			int dataCount = 0;
			if (searchKey.equals("pname")) {
				dataCount = dao.getProductCount(searchKey, searchValue);
			} else if (searchKey.equals("gname")) {
				dataCount = dao.getGijongCount(searchKey, searchValue);
			} else if (searchKey.equals("getNew")) {
				// head.jsp�� new�� ���� ������ ����
				dataCount = dao.getAllNewCount();
			} else if (searchKey.equals("headSearch")) {
				// head.jsp�� �˻��� ���� ������ ����
				dataCount = dao.getHeadSearchCount(searchKey,searchValue);
			}

			// ���������� ǥ���� ������ ����
			int numPerPage = 12;

			// ��ü ������ ���� (����)��
			int totalPage = myUtil.getPageCount(numPerPage, dataCount);

			// �����͸� �����ؼ� �������� �پ�����
			if (currentPage > totalPage) {
				currentPage = totalPage;
			}

			// ������ ������ ���۰� ��
			int start = (currentPage - 1) * numPerPage + 1;
			int end = currentPage * numPerPage;

			// ��ü�����ͱ��ϱ�
			List<PhonmeDTO> lists = null;
			if (searchKey.equals("gname")) {
				lists = dao.searchList(start, end, searchKey, searchValue);
			} else if (searchKey.equals("pname")) {
				lists = dao.getProductLists(start, end, searchKey, searchValue);
			} else if (searchKey.equals("getNew")) {
				// head.jsp�� new�� ���� ������ ����
				lists = dao.getAllNewLists(start, end);
			} else if(searchKey.equals("headSearch")) {
				lists = dao.getProductListsHead(start, end, searchKey, searchValue);
			}

			String param = "";
			if (!searchValue.equals("")) { // �˻��� ������

				param = "?searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}

			// ���� ������ �̵��� �ּ� (2����)
			String searchUrl = "search.do" + param; // �ڱ��ڽ�

			// ����¡ ó��
			String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, searchUrl);

			// �̹��� ���
			String imagePath = cp + "/image";

			// ī��Ʈ
			int count = 0;

			// ���� �ʹ� ��� �ڸ���
			for (PhonmeDTO show : lists) {
				if (show.getPname().length() > 17) {
					show.setPname(show.getPname().substring(0, 17));
				}
			}

			// �������� ������
			req.setAttribute("lists", lists);
			req.setAttribute("count", count);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("totalPage", totalPage);
			req.setAttribute("currentPage", currentPage);
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("imagePath", imagePath);
			req.setAttribute("searchUrl", searchUrl);
			req.setAttribute("searchKey", searchKey);
			req.setAttribute("searchValue", searchValue);

			url = "/jsp/search.jsp"; // �����ּҴ� �̰�
			forward(req, resp, url); // ������� �����ּ�

		} // end of search.do
		

// member.do  - ȸ������
		else if (uri.indexOf("member.do") != -1) {

			url = "/jsp/member.jsp";
			forward(req, resp, url);

		} // end of member.do
		

// member_ok.do  - ȸ������
		else if (uri.indexOf("member_ok.do") != -1) {

			PhonmeDTO dto = new PhonmeDTO();

			dto.setMid(req.getParameter("mid"));
			dto.setPw(req.getParameter("pw"));
			dto.setName(req.getParameter("name"));
			dto.setAddr(req.getParameter("addr"));
			dto.setTel(req.getParameter("tel"));

			int result = dao.insertMember(dto);
			req.setAttribute("dto", dto);

			if (result != 0) {
				resp.sendRedirect(cp + "/fatale/login.do");
				return;
			}

		} // end of member_ok.do

		
// login.do - �α���
		else if (uri.indexOf("login.do") != -1) {

			url = "/jsp/login.jsp";
			forward(req, resp, url);

		}

// login_ok.do - �α���
		else if (uri.indexOf("login_ok.do") != -1) {

			String mid = req.getParameter("mid");
			String pw = req.getParameter("pw");
			String param = "mid=" + mid;
			PhonmeDTO dto = dao.getReadMember(mid);

			if (dto == null || !dto.getPw().equals(pw)) {
				req.setAttribute("message", "���̵� �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�.");

				url = "/jsp/login.jsp";

				forward(req, resp, url);
				return;

			}

			HttpSession session = req.getSession();

			PhonmeInfo info = new PhonmeInfo();

			info.setMid(dto.getMid());
			info.setUserName(dto.getName());

			// �α��ο� �����ϸ� ���ǰ��� ���� ����ִ´�.
			session.setAttribute("phonmeInfo", info);
			session.setAttribute("param", param);

			url = cp + "/fatale/index.do";
			resp.sendRedirect(url);

		} // end of login_ok.do
		

// logout_ok.do - �α׾ƿ�
		else if (uri.indexOf("logout_ok.do") != -1) {

			// ������ ������ ��������
			HttpSession session = req.getSession();

			session.removeAttribute("customInfo");
			session.invalidate();

			url = cp + "/fatale/index.do";
			resp.sendRedirect(url);

		} // end of logout_ok.do

	
// myPage.do - ����������
		else if (uri.indexOf("myPage.do") != -1) {

			HttpSession session = req.getSession();

			PhonmeInfo info = (PhonmeInfo) session.getAttribute("phonmeInfo");
			String mid = info.getMid();

			PhonmeDTO dto = dao.getReadMember(mid);

			String searchKey = "mid";
			String searchValue = mid;

			List<PhonmeDTO> jjimLists = dao.getListJjim(mid);
			List<PhonmeDTO> reviewLists = dao.getListReview(searchKey, searchValue);
			List<PhonmeDTO> qnaLists = dao.getMyQna(mid);
			List<PhonmeDTO> orderdLists = dao.getListOrderd(mid);

			req.setAttribute("dto", dto);
			req.setAttribute("jjimLists", jjimLists);
			req.setAttribute("reviewLists", reviewLists);
			req.setAttribute("qnaLists", qnaLists);
			req.setAttribute("orderdLists", orderdLists);

			url = "/jsp/myPage.jsp";
			forward(req, resp, url);

		} // end of myPage.do
		

// update.do - ȸ����������
		else if (uri.indexOf("update.do") != -1) {

			HttpSession session = req.getSession();
			PhonmeInfo info = (PhonmeInfo) session.getAttribute("phonmeInfo");
			String mid = info.getMid();

			PhonmeDTO dto = dao.getReadMember(mid);

			req.setAttribute("dto", dto);

			url = "/jsp/update.jsp";
			forward(req, resp, url);

		} // end of update.do

		
// update_ok.do - ȸ����������
		else if (uri.indexOf("update_ok.do") != -1) {

			PhonmeDTO dto = new PhonmeDTO();

			dto.setMid(req.getParameter("mid"));
			dto.setPw(req.getParameter("pw"));
			dto.setName(req.getParameter("name"));
			dto.setAddr(req.getParameter("addr"));
			dto.setTel(req.getParameter("tel"));

			dao.updateMember(dto);
			url = cp + "/fatale/myPage.do";
			resp.sendRedirect(url);

		} // end of update_ok.do
		

// notice.do - ���� ����	
		else if (uri.indexOf("notice.do") != -1) {

			String pageNum = req.getParameter("pageNum"); // �ٸ� �����͸� ������ ���� ���� ��������.
			int currentPage = 1;

			if (pageNum != null) { // ó�� ����� null
				currentPage = Integer.parseInt(pageNum);
			} else {
				pageNum = "1";
			}

			// ��ü ������ ���ϱ�
			int dataCount = dao.getNoticeCount();

			// ���������� ǥ���� ������ ����
			int numPerPage = 10;

			// ��ü �������� ����
			int totalPage = myUtil.getPageCount(numPerPage, dataCount);

			// �����͸� �����ؼ� �������� �پ��� ���� ó��
			if (currentPage > totalPage) {
				currentPage = totalPage;
			}

			// ������ ������ ���۰� ��
			int start = (currentPage - 1) * numPerPage + 1;
			int end = currentPage * numPerPage;

			// ������ �����ͼ� lists�� ����
			List<PhonmeDTO> lists = dao.getNoticeLists(start, end);

			for (PhonmeDTO dto : lists) {
				// �� ������ ���͸� br�� ��ȯ
				dto.setNcontent(dto.getNcontent().replaceAll("\n", "<br>"));
			}

			// ����¡ ó��
			String listUrl = "notice.do";
			String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);

			// �������� ������
			req.setAttribute("lists", lists);
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("dataCount", dataCount); // ��ü �������� ����

			url = "/jsp/notice.jsp";
			forward(req, resp, url);

		} // end of notice.do

// jjim.do - �� ȭ��
		else if (uri.indexOf("jjim.do") != -1) {

			String mid = null;
			HttpSession session = req.getSession();
			Object ob = session.getAttribute("phonmeInfo");
			if (ob != null) {
				PhonmeInfo info = (PhonmeInfo) ob;
				mid = info.getMid();
			}

			List<PhonmeDTO> lists = dao.getListJjim(mid);

			req.setAttribute("lists", lists);

			url = "/jsp/jjim.jsp";
			forward(req, resp, url);

		} // end of jjim.do

		
// jjim_ok.do - �� �߰�
		else if (uri.indexOf("jjim_ok.do") != -1) {
			String mid = null;
			HttpSession session = req.getSession();
			Object ob = session.getAttribute("phonmeInfo");
	
			// ����Ȯ��
			if (ob != null) {
				PhonmeInfo info = (PhonmeInfo) ob;
				mid = info.getMid();
			}
	
			PhonmeDTO dto = new PhonmeDTO();
	
			int num = dao.getJjimCount();
	
			dto.setNum(num + 1);
			dto.setMid(mid);
			dto.setPid(req.getParameter("pid"));
	
			int result = dao.insertJjim(dto);
	
			if (result != 0) {
				resp.setContentType("text/html; charset=utf-8");
				PrintWriter out = resp.getWriter();
				out.println("<script>");
				out.println("alert('��ǰ�� ���߽��ϴ�!');");
				out.println("history.back();");
				out.println("</script>");
	
				out.close();
				return;
			} else {
				PrintWriter out = resp.getWriter();
				out.println("<script>");
				out.println("alert('�����߻�!');");
				out.println("history.back();");
				out.println("</script>");
	
				out.close();
				return;
			}
	
		} // end of jjim_ok.do
		

// jjim_delete.do - �� ����
		else if (uri.indexOf("jjim_delete.do") != -1) {
			HttpSession session = req.getSession();
			Object ob = session.getAttribute("phonmeInfo");
			PhonmeInfo info = (PhonmeInfo) ob;
			String mid = info.getMid();
			String pid = req.getParameter("pid");

			dao.deleteJjim(pid, mid);

			url = cp + "/fatale/myPage.do";
			resp.sendRedirect(url);

		} // end of jjim_delete.do

		
// review.do - ���� ȭ��
		else if (uri.indexOf("review.do") != -1) {
			String mid = null;
			HttpSession session = req.getSession();
			Object ob = session.getAttribute("phonmeInfo");
			if (ob != null) {
				PhonmeInfo info = (PhonmeInfo) ob;
				mid = info.getMid();
			}

			// �Ѿ�� ������ ��ȣ(myUtil,article)
			String pageNum = req.getParameter("pageNum");

			int currentPage = 1;

			if (pageNum != null) {
				currentPage = Integer.parseInt(pageNum);
			} else {
				pageNum = "1";
			}
			// �˻�----------------------------------
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");
			if (searchValue != null) {// �˻��� ������
				if (req.getMethod().equalsIgnoreCase("GET")) {
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}
			} else {// �˻��� ��������
				searchKey = "pname";
				searchValue = "";
			}
			// -------------------------------------

			// ��ü������ ����
			int dataCount = 0;
			dataCount = dao.getReviewCount(searchKey, searchValue);

			// ���������� ǥ���� ������ ����
			int numPerPage = 5;

			// ��ü ������ ���� (����)��
			int totalPage = myUtil.getPageCount(numPerPage, dataCount);

			// �����͸� �����ؼ� �������� �پ�����
			if (currentPage > totalPage) {
				currentPage = totalPage;
			}

			// ������ ������ ���۰� ��
			int start = (currentPage - 1) * numPerPage + 1;
			int end = currentPage * numPerPage;

			// ��ü�����ͱ��ϱ�
			List<PhonmeDTO> lists = null;
			lists = dao.getListReview(start, end, searchKey, searchValue);

			String param = "";
			if (!searchValue.equals("")) { // �˻��� ������
				param = "?searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}

			// ���� ������ �̵��� �ּ� (2����)
			String searchUrl = cp + "/fatale/review.do" + param; // �ڱ��ڽ�

			// ����¡ ó��
			String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, searchUrl);

			// �������� ������
			req.setAttribute("lists", lists);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("totalPage", totalPage);
			req.setAttribute("currentPage", currentPage);
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("searchUrl", searchUrl);
			req.setAttribute("searchKey", searchKey);
			req.setAttribute("searchValue", searchValue);
			req.setAttribute("mid", mid);

			req.setAttribute("lists", lists);

			url = "/jsp/review.jsp";
			forward(req, resp, url);
		} // end of review.do
		
		
// deleteReview_ok.do - �ۼ��� ���� ����
		else if (uri.indexOf("deleteReview_ok.do") != -1) {
			HttpSession session = req.getSession();
			Object ob = session.getAttribute("phonmeInfo");
			PhonmeInfo info = (PhonmeInfo) ob;
			int num = Integer.parseInt(req.getParameter("num"));

			dao.deleteReview(num);
			
			url = cp + "/fatale/myPage.do";
			resp.sendRedirect(url);
		}
		
// reviewWrite.do - ������ ��ǰ �����ۼ� ȭ�� ���
		else if (uri.indexOf("reviewWrite.do") != -1) {
			HttpSession session = req.getSession();
			Object ob = session.getAttribute("phonmeInfo");
			PhonmeInfo info = (PhonmeInfo) ob;
			String mid = info.getMid();

			if (req.getParameter("num") != null) {
				String num = req.getParameter("num");
				PhonmeDTO review = dao.getReadReview(Integer.parseInt(num));
				req.setAttribute("review", review);
			}
			if (req.getParameter("odNum") != null) {
				int odNum = Integer.parseInt(req.getParameter("odNum"));
				PhonmeDTO orderd = dao.getOrderdOne(odNum);
				req.setAttribute("orderd", orderd);
			}
			url = "/jsp/reviewWrite.jsp";
			forward(req, resp, url);
		} // end of reviewWrite.do
		
// reviewWrite_ok.do - �����ۼ� �Ϸ�
		else if (uri.indexOf("reviewWrite_ok.do") != -1) {
			HttpSession session = req.getSession();
			Object ob = session.getAttribute("phonmeInfo");
			PhonmeInfo info = (PhonmeInfo) ob;
			String mid = info.getMid();
			
			PhonmeDTO dto = new PhonmeDTO();
			
			int maxNum = dao.getReviewMaxNum();
			dto.setNum(maxNum+1);
			dto.setMid(mid);
			dto.setPid(req.getParameter("pid"));
			dto.setTitle(req.getParameter("title"));
			dto.setContent(req.getParameter("content"));
			
			int result = dao.insertReview(dto);
			
			url = cp + "/fatale/myPage.do";
			resp.sendRedirect(url);

		} // end of reviewWrite_ok.do	
		
		
// reviewUpdate.do - ���� ����
		else if (uri.indexOf("reviewUpdate.do") != -1) {
			HttpSession session = req.getSession();
			Object ob = session.getAttribute("phonmeInfo");
			PhonmeInfo info = (PhonmeInfo) ob;
			String mid = info.getMid();
			int num = Integer.parseInt(req.getParameter("num"));
			
			PhonmeDTO dto = new PhonmeDTO();
			
			dto.setNum(num);
			dto.setMid(mid);
			dto.setPid(req.getParameter("pid"));
			dto.setTitle(req.getParameter("title"));
			dto.setContent(req.getParameter("content"));
			
			int result = dao.updateReview(dto);
			
			url = cp + "/fatale/myPage.do";
			resp.sendRedirect(url);

		} // end of reviewUpdate.do
		
		
// reviewDelete.do - ���� ����
		else if (uri.indexOf("reviewDelete.do") != -1) {
			dao.deleteReview(Integer.parseInt(req.getParameter("num")));

			url = cp + "/fatale/myPage.do";
			resp.sendRedirect(url);

		} // end of reviewDelete.do
		
		
// qna.do - qna��� 
		else if (uri.indexOf("qna.do") != -1) {

			// �Ѿ�� ������ ��ȣ
			String pageNum = req.getParameter("pageNum"); // �ٸ� �����͸� ������ ���� ���� ��������.

			int currentPage = 1;

			if (pageNum != null) { // ó�� ����� null
				currentPage = Integer.parseInt(pageNum);
			} else {
				pageNum = "1";
			}

			// ��ü ������ ���ϱ�
			int dataCount = dao.getQnaDataCount();

			// ���������� ǥ���� ������ ����
			int numPerPage = 10;

			// ��ü �������� ����
			int totalPage = myUtil.getPageCount(numPerPage, dataCount);

			// �����͸� �����ؼ� �������� �پ��� ���� ó��
			if (currentPage > totalPage) {
				currentPage = totalPage;
			}

			// ������ ������ ���۰� ��
			int start = (currentPage - 1) * numPerPage + 1;
			int end = currentPage * numPerPage;

			List<PhonmeDTO> lists = dao.getAllQna(start, end);

			String listUrl = "qna.do";
			String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);

			// �������� ������
			req.setAttribute("lists", lists);
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("dataCount", dataCount); // ��ü �������� ����

			url = "/jsp/qna.jsp";
			forward(req, resp, url);

			
		} // end of qna.do
		
		
// qnaWrite.do - Q&A �۾��� ��		
		else if (uri.indexOf("qnaWrite.do") != -1) {

			HttpSession session = req.getSession();
			PhonmeInfo info = (PhonmeInfo)session.getAttribute("phonmeInfo");
			if (info.getMid() == null || info.getMid().equals("")) {
				url = cp + "/fatale/login.do";
				resp.sendRedirect(url);
				return;
			}

			int maxNum = dao.getQnaMaxNum() + 1;
			req.setAttribute("mid", info.getMid());
			req.setAttribute("maxNum", maxNum);

			url = "/jsp/qnaWrite.jsp";
			forward(req, resp, url);

			
		} // end of qnaWrite.do
		
		
// qnaWrite_ok.do - Q&A �۾��� ������Ʈ		
		else if (uri.indexOf("qnaWrite_ok.do") != -1) {
			
			PhonmeDTO dto = new PhonmeDTO();

			dto.setNum(Integer.parseInt(req.getParameter("num")));
			dto.setMid(req.getParameter("mid"));
			dto.setTitle(req.getParameter("title"));
			dto.setContent(req.getParameter("content"));
			dto.setQcreated(req.getParameter("qcreated"));

			int result = dao.insertQna(dto);

			if (result == 0) {
				PrintWriter out = resp.getWriter();
				out.println("<script>alert('ť������ �Է� ����');");
				out.println("history.back();</script>");
			}
			url = cp + "/fatale/qna.do";
			resp.sendRedirect(url);

		} // end of qnaWrite_ok.do

		
// idSearch.do - ���̵� ã��
		else if (uri.indexOf("idSearch.do") != -1) {

			url = "/jsp/idSearch.jsp";
			forward(req, resp, url);

		} // end of idSearch.do
		
		
//idSearch_ok.do - ���̵� ã��
		else if (uri.indexOf("idSearch_ok.do") != -1) {

			// ������ �ޱ�
			String name = req.getParameter("name");
			String tel = req.getParameter("tel");

			// Ȯ���� dto �޾ƿ���
			PhonmeDTO dto = dao.getReadMemberId(name,tel);

			if (dto == null || !dto.getTel().equals(tel)) {
				req.setAttribute("message", "ȸ�������� �������� �ʽ��ϴ�.");

				url = "/jsp/login.jsp";
				forward(req, resp, url);
				return;

			}

			req.setAttribute("message", "���̵��" + dto.getMid() + "�Դϴ�.");

			url = "/jsp/login.jsp";
			forward(req, resp, url);
			return;

		} // end of idSearch_ok.do
		

//pwSearch.do - ��й�ȣ ã��		
		else if (uri.indexOf("pwSearch.do") != -1) {

			url = "/jsp/pwSearch.jsp";
			forward(req, resp, url);

		} // end of pwSearch.do

		
//pwSearch_ok.do - ��й�ȣ ã��	
		else if (uri.indexOf("pwSearch_ok.do") != -1) {

			// ������ �ޱ�
			String mid = req.getParameter("mid");
			String name = req.getParameter("name");
			String tel = req.getParameter("tel");

			// Ȯ���� dto �޾ƿ���
			PhonmeDTO dto = dao.getReadMember(mid);

			if (dto == null || !dto.getTel().equals(tel) || !dto.getName().equals(name)) {
				req.setAttribute("dto", dto);
				req.setAttribute("message", "ȸ�������� �������� �ʽ��ϴ�.");

				// �α��� ��ư����
				url = "/jsp/login.jsp";
				forward(req, resp, url);
				return;

			}

			req.setAttribute("message", "��й�ȣ��" + dto.getPw() + "�Դϴ�.");

			url = "/jsp/login.jsp";
			forward(req, resp, url);
			return;

		} // end of pwSearch_ok.do
		
		
//idCheck_ok.do - ���̵� �ߺ�Ȯ��
		else if (uri.indexOf("idCheck_ok.do") != -1) {

			String mid = req.getParameter("mid");

			PhonmeDTO dto = dao.checkIdMember(mid);

			if (dto != null && dto.getMid().equals(mid)) {
				req.setAttribute("dto", dto);
				req.setAttribute("message", "�̹� �����ϴ� ���̵��Դϴ�.");

				url = "/jsp/member.jsp";
				forward(req, resp, url);
				return;

			} else {
				req.setAttribute("message", "��밡���� ���̵��Դϴ�.");
				req.setAttribute("mid", mid);

				url = "/jsp/member.jsp";
				forward(req, resp, url);
				return;

			}

		} // end of idCheck_ok.do	
		
		
//order.do - �ֹ��ϱ� ������
		else if (uri.indexOf("order.do") != -1) {

			HttpSession session = req.getSession();
			Object ob = session.getAttribute("phonmeInfo");
			PhonmeInfo info = (PhonmeInfo) ob;
			String mid = info.getMid();
			
			int[] numI = null;

			PhonmeDTO dto = dao.getReadMember(mid);
			req.setAttribute("dto", dto);

			String totPrice = req.getParameter("totPrice");
			String[] chkNums = req.getParameterValues("cartChk");

			if (chkNums != null) {
				numI = new int[chkNums.length];
				for (int i = 0; i < chkNums.length; i++) {
					numI[i] = Integer.parseInt(chkNums[i]);
				}
			}

			List<PhonmeDTO> lists = dao.getOrderList(numI, mid);

			req.setAttribute("lists", lists);
			req.setAttribute("totPrice", totPrice);

			url = "/jsp/order.jsp";
			forward(req, resp, url);

		} // end of order.do
				
//order_ok.do - �ֹ��Ϸ� ������
else if (uri.indexOf("order_ok.do") != -1) {
			
			// dao�� �ʿ��� sql��� �����(order/odetail)
			// �������� insert�� �ʿ��� ������ �ޱ�
			// �����ʹ� order.jps���� submit()���� �� �Ѱ���
			// �������� String ??? = req.getParameter("jsp�� �ִ� ����?") �ϸ� ��Ʈ������ ����
			// DTO ���� ���� �Ķ��Ÿ�� set��Ű�� dao.insert(dto)
			HttpSession session = req.getSession();
			Object ob = session.getAttribute("phonmeInfo");
			PhonmeInfo info = (PhonmeInfo) ob;
			String mid = info.getMid();

			int[] numI = null;
			int[] nums = null;
			PhonmeDTO dto = new PhonmeDTO();
			
			int omaxnum = dao.getOrderdMaxNum();
			int oprice = Integer.parseInt(req.getParameter("totprice"));// ������ �������� �޾ƿ�
			String oaddr = req.getParameter("addr1");
			String otel = req.getParameter("tel1");
			
			dto.setOid(omaxnum + 1);
			dto.setMid(mid);
			dto.setPrice(oprice);
			dto.setAddr(oaddr);
			dto.setTel(otel);
			dao.insertOrderd(dto);
			
			String[] num = req.getParameterValues("num");
			String[] opid = req.getParameterValues("pid");
			String[] color = req.getParameterValues("color");
			String[] ogid = req.getParameterValues("gid"); // �Ķ����values�� name�̿������ϴ�
			String[] count = req.getParameterValues("count");

			if (count != null) {
				numI = new int[count.length];
				nums = new int[num.length];
				for (int i = 0; i < count.length; i++) {
					numI[i] = Integer.parseInt(count[i]);
					nums[i] = Integer.parseInt(num[i]);
				}

				for (int i = 0; i < opid.length; i++) {

					int odmaxnum = dao.getODetailMaxNum();
					dto = new PhonmeDTO();

					dto.setPid(opid[i]);
					dto.setGid(ogid[i]);
					dto.setCount(numI[i]);
					dto.setNum(odmaxnum + 1);
					dto.setColor(color[i]);
					dto.setMid(mid);
					dto.setOid(omaxnum + 1);
					dao.deleteCart(numI);
					dao.insertOdetail(dto);
					dao.deleteCart(nums);
				}

				url = "/jsp/order_ok.jsp";
				forward(req, resp, url);

			}

		}

	}

}