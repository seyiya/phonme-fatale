/**
*	<페이지 정리>
*	index.do - 메인 페이지 (index.jsp)
*	adminPages.do - 관리자 페이지 (adminPages.jsp)
*	addNotice.do - 관리자 공지 등록 (addNotice.jsp)
*	addNotice_ok.do - 관리자 공지 등록
*	addProduct.do - 관리자 상품 추가 (addProduct.jsp)
*	addProduct_ok.do - 관리자 상품 추가
*	addProduct_delete.do - 관리자 상품 삭제
*	article.do - 상품 상세보기 (article.jsp)
*	article_ok.do - 상품 상세보기
*	cart.do - 장바구니 (cart.jsp)
*	cart_count_update.do - 장바구니 갯수 업데이트
*	cart_delete.do - 장바구니 삭제
*	search.do - 상품 검색 (search.jsp)
*	member.do  - 회원가입 (member.jsp)
*	member_ok.do  - 회원가입
*	login.do - 로그인 (login.jsp)
*	login_ok.do - 로그인
*	logout_ok.do - 로그아웃
*	myPage.do - 마이페이지 (myPage.jsp)
*	update.do - 회원정보수정 (update.jsp)
*	update_ok.do - 회원정보수정
*	notice.do - 공지 사항 (notice.jsp)
*	jjim.do - 찜 화면 (myPage.jsp)
*	jjim_ok.do - 찜 추가
*	jjim_delete.do - 찜 삭제
*	review.do - 리뷰 화면 (review.jsp)
*	deleteReview_ok.do - 작성한 리뷰 삭제 (myPage.jsp)
*	reviewWrite.do - 구매한 상품 리뷰작성 화면 출력 (reviewWrite.jsp)
*	reviewWrite_ok.do - 리뷰작성 완료
*	reviewUpdate.do - 리뷰 수정 (reviewWrite.jsp)
*	reviewDelete.do - 리뷰 삭제
*	qna.do: qna목록 (qna.jsp)
*	qnaWrite.do - Q&A 글쓰는 곳 (qnaWrite.jsp)qnaWrite_ok.do - Q&A 글쓴거 업데이트
*	idSearch.do - 아이디 찾기(idSearch.jsp)
*	idSearch_ok.do - 아이디 찾기
*	pwSearch.do - 비밀번호 찾기 (pwSearch.jsp)
*	pwSearch_ok.do - 비밀번호 찾기	
*	idCheck_ok.do - 아이디 중복확인
*	 order.do - 주문하기 (order.jsp)
*	order_ok.do - 주문완료 페이지
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
		

//index.do - 메인페이지
		if (uri.indexOf("index.do") != -1) {

			String imagePath = cp + "/image";
			req.setAttribute("imagePath", imagePath);

			int count = 0;
			req.setAttribute("count", count);

			url = "/index.jsp";
			forward(req, resp, url);

		} // end of index.do

		
//adminPages.do - 관리자 페이지
		else if (uri.indexOf("adminPages.do") != -1) {

			url = "/jsp/adminPages.jsp";
			forward(req, resp, url);

		} // end of adminPages.do
		

//addNotice.do - 관리자 공지 등록
		else if (uri.indexOf("addNotice.do") != -1) {

			url = "/jsp/addNotice.jsp";
			forward(req, resp, url);

		} // end of addNotice.do
		

//addNotice_ok.do - 관리자 공지 등록
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
		

// addProduct.do - 관리자 상품 추가
		else if (uri.indexOf("addProduct.do") != -1) {

			url = "/jsp/addProduct.jsp";
			forward(req, resp, url);

		} // end of addProduct.do
		

// addProduct_ok.do - 관리자 상품 추가	
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

		
// addProduct_delete.do - 관리자 상품 삭제
		else if (uri.indexOf("addProduct_delete.do") != -1) {

			String encType = "UTF-8";
			int maxSize = 10 * 1024 * 1024;
			MultipartRequest mr = new MultipartRequest(req, path, maxSize, encType, new DefaultFileRenamePolicy());

			String pid = mr.getParameter("deletePid");

			PhonmeDTO dto = dao.getReadProduct(pid);

			// 실제파일 삭제
			FileManager.doFileDelete(dto.getImg(), path);

			// 테이블정보 삭제
			dao.deleteProduct(pid);

			url = cp + "/fatale/addProduct.do";
			resp.sendRedirect(url);

		} // end of addProduct_delete.do
		

// article.do - 상품 상세보기
		else if (uri.indexOf("article.do") != -1) {
			
			// 해당 게시물의 케이스 아이디 받음
			String pid = req.getParameter("pid");
			// 들어오기 전 게시물의 페이지 번호
			String pageNum = req.getParameter("pageNum");
			// 검색했을 경우에 관련 정보
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");
			// 검색값이 없으면 기본으로 초기화
			if (searchValue != null) {
				if (req.getMethod().equalsIgnoreCase("GET")) {
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}
			} else {// 검색을 안 했을 때
				searchKey = "pname";
				searchValue = "";
			}

			// 글 가져오기
			PhonmeDTO dto = dao.getReadProduct(pid);
			if (dto == null) {

				url = cp + "/fatale/search.do";
				resp.sendRedirect(url);
				return;
			}
			// 조회수 증가
			dao.updateHit(pid);
			// 호환되는 기종 가져오기
			List<PhonmeDTO> gijong = dao.getArticleOptions(pid);
			// 색상 분리
			String[] color = dto.getColor().split("/");
			// 들어오기 전 페이지로 가기 위한 정보
			String params = "pageNum=" + pageNum;
			if (searchValue != null) {
				params += "&searchKey=" + searchKey;
				params += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}

			// 전체 데이터 구하기(수정해야함)
			List<PhonmeDTO> lists = dao.getProductLists(1, 10, "pname", "");

			// 이미지경로
			String imagePath = cp + "/image";

			// 필요한 값 담아줌
			req.setAttribute("dto", dto);
			req.setAttribute("params", params);
			req.setAttribute("color", color);
			req.setAttribute("lists", lists);
			req.setAttribute("gijong", gijong);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("searchKey", searchKey);
			req.setAttribute("searchValue", searchValue);
			req.setAttribute("imagePath", imagePath);
			// 이동
			url = "/jsp/article.jsp";
			forward(req, resp, url);

		} // end of article.do
		

// article_ok.do - 상품 상세보기
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

			// 장바구니 추가
			dao.insertCart(dto);

			resp.setContentType("text/html; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println("alert('장바구니에 담았습니다!');");
			out.println("history.back();");
			out.println("</script>");

			out.close();
			// 이동없음
			return;

		} // end of article_ok.do
		

// cart.do - 장바구니
		else if (uri.indexOf("cart.do") != -1) {

			String mid = null;
			HttpSession session = null;
			Object ob = null;

			session = req.getSession();
			ob = session.getAttribute("phonmeInfo");
			PhonmeInfo info = (PhonmeInfo) ob;
			mid = info.getMid();

			// 해당 계정의 장바구니 목록 호출
			List<PhonmeDTO> cartLists = dao.getListCart(mid);

			int totPrice = 0;
			for (PhonmeDTO dto : cartLists) {
				totPrice += dto.getHap();
			}

			// 필요한 정보 담아줌
			req.setAttribute("mid", mid);
			req.setAttribute("cartLists", cartLists);
			req.setAttribute("totPrice", totPrice);
			
			// 이동
			url = "/jsp/cart.jsp";
			forward(req, resp, url);

		} // end of cart.do
		

// cart_count_update.do - 장바구니 갯수 업데이트
		else if (uri.indexOf("cart_count_update.do") != -1) {
			String Snum = req.getParameter("num");
			int num = Integer.parseInt(req.getParameter("num"));
			int count = Integer.parseInt(req.getParameter("count" + Snum));

			dao.updateCartCount(num, count);

			// 이동
			url = cp + "/fatale/cart.do";
			resp.sendRedirect(url);

		} // end of cart_count_update.do
		

// cart_delete.do - 장바구니 삭제
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
		

// search.do - 상품 검색  
		else if (uri.indexOf("search.do") != -1) {

			String pageNum = req.getParameter("pageNum");

			int currentPage = 1;

			if (pageNum != null) {
				currentPage = Integer.parseInt(pageNum);
			} else {
				pageNum = "1";
			}
			// 검색----------------------------------
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue").replaceAll(" ", "").toUpperCase();
			if (searchValue != null) {// 검색을 했을때
				if (req.getMethod().equalsIgnoreCase("GET")) {
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}
			} else {// 검색을 안했을때
				searchKey = "pname";
				searchValue = "";
			}
			// -------------------------------------

			// 전체데이터 갯수
			int dataCount = 0;
			if (searchKey.equals("pname")) {
				dataCount = dao.getProductCount(searchKey, searchValue);
			} else if (searchKey.equals("gname")) {
				dataCount = dao.getGijongCount(searchKey, searchValue);
			} else if (searchKey.equals("getNew")) {
				// head.jsp의 new를 위한 데이터 갯수
				dataCount = dao.getAllNewCount();
			} else if (searchKey.equals("headSearch")) {
				// head.jsp의 검색을 위한 데이터 갯수
				dataCount = dao.getHeadSearchCount(searchKey,searchValue);
			}

			// 한페이지에 표시할 데이터 갯수
			int numPerPage = 12;

			// 전체 페이지 갯수 (순서)★
			int totalPage = myUtil.getPageCount(numPerPage, dataCount);

			// 데이터를 삭제해서 페이지가 줄었을때
			if (currentPage > totalPage) {
				currentPage = totalPage;
			}

			// 가져올 데이터 시작과 끝
			int start = (currentPage - 1) * numPerPage + 1;
			int end = currentPage * numPerPage;

			// 전체데이터구하기
			List<PhonmeDTO> lists = null;
			if (searchKey.equals("gname")) {
				lists = dao.searchList(start, end, searchKey, searchValue);
			} else if (searchKey.equals("pname")) {
				lists = dao.getProductLists(start, end, searchKey, searchValue);
			} else if (searchKey.equals("getNew")) {
				// head.jsp의 new를 위한 데이터 갯수
				lists = dao.getAllNewLists(start, end);
			} else if(searchKey.equals("headSearch")) {
				lists = dao.getProductListsHead(start, end, searchKey, searchValue);
			}

			String param = "";
			if (!searchValue.equals("")) { // 검색을 했으면

				param = "?searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}

			// 값을 가지고 이동할 주소 (2가지)
			String searchUrl = "search.do" + param; // 자기자신

			// 페이징 처리
			String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, searchUrl);

			// 이미지 경로
			String imagePath = cp + "/image";

			// 카운트
			int count = 0;

			// 제목 너무 길면 자르기
			for (PhonmeDTO show : lists) {
				if (show.getPname().length() > 17) {
					show.setPname(show.getPname().substring(0, 17));
				}
			}

			// 포워딩할 데이터
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

			url = "/jsp/search.jsp"; // 실제주소는 이거
			forward(req, resp, url); // 포워드는 실제주소

		} // end of search.do
		

// member.do  - 회원가입
		else if (uri.indexOf("member.do") != -1) {

			url = "/jsp/member.jsp";
			forward(req, resp, url);

		} // end of member.do
		

// member_ok.do  - 회원가입
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

		
// login.do - 로그인
		else if (uri.indexOf("login.do") != -1) {

			url = "/jsp/login.jsp";
			forward(req, resp, url);

		}

// login_ok.do - 로그인
		else if (uri.indexOf("login_ok.do") != -1) {

			String mid = req.getParameter("mid");
			String pw = req.getParameter("pw");
			String param = "mid=" + mid;
			PhonmeDTO dto = dao.getReadMember(mid);

			if (dto == null || !dto.getPw().equals(pw)) {
				req.setAttribute("message", "아이디 또는 비밀번호가 틀렸습니다.");

				url = "/jsp/login.jsp";

				forward(req, resp, url);
				return;

			}

			HttpSession session = req.getSession();

			PhonmeInfo info = new PhonmeInfo();

			info.setMid(dto.getMid());
			info.setUserName(dto.getName());

			// 로그인에 성공하면 세션값에 값을 집어넣는다.
			session.setAttribute("phonmeInfo", info);
			session.setAttribute("param", param);

			url = cp + "/fatale/index.do";
			resp.sendRedirect(url);

		} // end of login_ok.do
		

// logout_ok.do - 로그아웃
		else if (uri.indexOf("logout_ok.do") != -1) {

			// 세션의 정보를 지워야함
			HttpSession session = req.getSession();

			session.removeAttribute("customInfo");
			session.invalidate();

			url = cp + "/fatale/index.do";
			resp.sendRedirect(url);

		} // end of logout_ok.do

	
// myPage.do - 마이페이지
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
		

// update.do - 회원정보수정
		else if (uri.indexOf("update.do") != -1) {

			HttpSession session = req.getSession();
			PhonmeInfo info = (PhonmeInfo) session.getAttribute("phonmeInfo");
			String mid = info.getMid();

			PhonmeDTO dto = dao.getReadMember(mid);

			req.setAttribute("dto", dto);

			url = "/jsp/update.jsp";
			forward(req, resp, url);

		} // end of update.do

		
// update_ok.do - 회원정보수정
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
		

// notice.do - 공지 사항	
		else if (uri.indexOf("notice.do") != -1) {

			String pageNum = req.getParameter("pageNum"); // 다른 데이터를 가지고 들어갈때 같이 데려간다.
			int currentPage = 1;

			if (pageNum != null) { // 처음 실행시 null
				currentPage = Integer.parseInt(pageNum);
			} else {
				pageNum = "1";
			}

			// 전체 데이터 구하기
			int dataCount = dao.getNoticeCount();

			// 한페이지에 표시할 데이터 갯수
			int numPerPage = 10;

			// 전체 페이지의 갯수
			int totalPage = myUtil.getPageCount(numPerPage, dataCount);

			// 데이터를 삭제해서 페이지가 줄었을 때의 처리
			if (currentPage > totalPage) {
				currentPage = totalPage;
			}

			// 가져올 데이터 시작과 끝
			int start = (currentPage - 1) * numPerPage + 1;
			int end = currentPage * numPerPage;

			// 데이터 가져와서 lists에 저장
			List<PhonmeDTO> lists = dao.getNoticeLists(start, end);

			for (PhonmeDTO dto : lists) {
				// 글 내용의 엔터를 br로 변환
				dto.setNcontent(dto.getNcontent().replaceAll("\n", "<br>"));
			}

			// 페이징 처리
			String listUrl = "notice.do";
			String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);

			// 포워딩할 데이터
			req.setAttribute("lists", lists);
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("dataCount", dataCount); // 전체 데이터의 갯수

			url = "/jsp/notice.jsp";
			forward(req, resp, url);

		} // end of notice.do

// jjim.do - 찜 화면
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

		
// jjim_ok.do - 찜 추가
		else if (uri.indexOf("jjim_ok.do") != -1) {
			String mid = null;
			HttpSession session = req.getSession();
			Object ob = session.getAttribute("phonmeInfo");
	
			// 세션확인
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
				out.println("alert('상품을 찜했습니다!');");
				out.println("history.back();");
				out.println("</script>");
	
				out.close();
				return;
			} else {
				PrintWriter out = resp.getWriter();
				out.println("<script>");
				out.println("alert('에러발생!');");
				out.println("history.back();");
				out.println("</script>");
	
				out.close();
				return;
			}
	
		} // end of jjim_ok.do
		

// jjim_delete.do - 찜 삭제
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

		
// review.do - 리뷰 화면
		else if (uri.indexOf("review.do") != -1) {
			String mid = null;
			HttpSession session = req.getSession();
			Object ob = session.getAttribute("phonmeInfo");
			if (ob != null) {
				PhonmeInfo info = (PhonmeInfo) ob;
				mid = info.getMid();
			}

			// 넘어온 페이지 번호(myUtil,article)
			String pageNum = req.getParameter("pageNum");

			int currentPage = 1;

			if (pageNum != null) {
				currentPage = Integer.parseInt(pageNum);
			} else {
				pageNum = "1";
			}
			// 검색----------------------------------
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");
			if (searchValue != null) {// 검색을 했을때
				if (req.getMethod().equalsIgnoreCase("GET")) {
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}
			} else {// 검색을 안했을때
				searchKey = "pname";
				searchValue = "";
			}
			// -------------------------------------

			// 전체데이터 갯수
			int dataCount = 0;
			dataCount = dao.getReviewCount(searchKey, searchValue);

			// 한페이지에 표시할 데이터 갯수
			int numPerPage = 5;

			// 전체 페이지 갯수 (순서)★
			int totalPage = myUtil.getPageCount(numPerPage, dataCount);

			// 데이터를 삭제해서 페이지가 줄었을때
			if (currentPage > totalPage) {
				currentPage = totalPage;
			}

			// 가져올 데이터 시작과 끝
			int start = (currentPage - 1) * numPerPage + 1;
			int end = currentPage * numPerPage;

			// 전체데이터구하기
			List<PhonmeDTO> lists = null;
			lists = dao.getListReview(start, end, searchKey, searchValue);

			String param = "";
			if (!searchValue.equals("")) { // 검색을 했으면
				param = "?searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}

			// 값을 가지고 이동할 주소 (2가지)
			String searchUrl = cp + "/fatale/review.do" + param; // 자기자신

			// 페이징 처리
			String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, searchUrl);

			// 포워딩할 데이터
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
		
		
// deleteReview_ok.do - 작성한 리뷰 삭제
		else if (uri.indexOf("deleteReview_ok.do") != -1) {
			HttpSession session = req.getSession();
			Object ob = session.getAttribute("phonmeInfo");
			PhonmeInfo info = (PhonmeInfo) ob;
			int num = Integer.parseInt(req.getParameter("num"));

			dao.deleteReview(num);
			
			url = cp + "/fatale/myPage.do";
			resp.sendRedirect(url);
		}
		
// reviewWrite.do - 구매한 상품 리뷰작성 화면 출력
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
		
// reviewWrite_ok.do - 리뷰작성 완료
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
		
		
// reviewUpdate.do - 리뷰 수정
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
		
		
// reviewDelete.do - 리뷰 삭제
		else if (uri.indexOf("reviewDelete.do") != -1) {
			dao.deleteReview(Integer.parseInt(req.getParameter("num")));

			url = cp + "/fatale/myPage.do";
			resp.sendRedirect(url);

		} // end of reviewDelete.do
		
		
// qna.do - qna목록 
		else if (uri.indexOf("qna.do") != -1) {

			// 넘어온 페이지 번호
			String pageNum = req.getParameter("pageNum"); // 다른 데이터를 가지고 들어갈때 같이 데려간다.

			int currentPage = 1;

			if (pageNum != null) { // 처음 실행시 null
				currentPage = Integer.parseInt(pageNum);
			} else {
				pageNum = "1";
			}

			// 전체 데이터 구하기
			int dataCount = dao.getQnaDataCount();

			// 한페이지에 표시할 데이터 갯수
			int numPerPage = 10;

			// 전체 페이지의 갯수
			int totalPage = myUtil.getPageCount(numPerPage, dataCount);

			// 데이터를 삭제해서 페이지가 줄었을 때의 처리
			if (currentPage > totalPage) {
				currentPage = totalPage;
			}

			// 가져올 데이터 시작과 끝
			int start = (currentPage - 1) * numPerPage + 1;
			int end = currentPage * numPerPage;

			List<PhonmeDTO> lists = dao.getAllQna(start, end);

			String listUrl = "qna.do";
			String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);

			// 포워딩할 데이터
			req.setAttribute("lists", lists);
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("dataCount", dataCount); // 전체 데이터의 갯수

			url = "/jsp/qna.jsp";
			forward(req, resp, url);

			
		} // end of qna.do
		
		
// qnaWrite.do - Q&A 글쓰는 곳		
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
		
		
// qnaWrite_ok.do - Q&A 글쓴거 업데이트		
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
				out.println("<script>alert('큐엔에이 입력 실패');");
				out.println("history.back();</script>");
			}
			url = cp + "/fatale/qna.do";
			resp.sendRedirect(url);

		} // end of qnaWrite_ok.do

		
// idSearch.do - 아이디 찾기
		else if (uri.indexOf("idSearch.do") != -1) {

			url = "/jsp/idSearch.jsp";
			forward(req, resp, url);

		} // end of idSearch.do
		
		
//idSearch_ok.do - 아이디 찾기
		else if (uri.indexOf("idSearch_ok.do") != -1) {

			// 데이터 받기
			String name = req.getParameter("name");
			String tel = req.getParameter("tel");

			// 확인할 dto 받아오기
			PhonmeDTO dto = dao.getReadMemberId(name,tel);

			if (dto == null || !dto.getTel().equals(tel)) {
				req.setAttribute("message", "회원정보가 존재하지 않습니다.");

				url = "/jsp/login.jsp";
				forward(req, resp, url);
				return;

			}

			req.setAttribute("message", "아이디는" + dto.getMid() + "입니다.");

			url = "/jsp/login.jsp";
			forward(req, resp, url);
			return;

		} // end of idSearch_ok.do
		

//pwSearch.do - 비밀번호 찾기		
		else if (uri.indexOf("pwSearch.do") != -1) {

			url = "/jsp/pwSearch.jsp";
			forward(req, resp, url);

		} // end of pwSearch.do

		
//pwSearch_ok.do - 비밀번호 찾기	
		else if (uri.indexOf("pwSearch_ok.do") != -1) {

			// 데이터 받기
			String mid = req.getParameter("mid");
			String name = req.getParameter("name");
			String tel = req.getParameter("tel");

			// 확인할 dto 받아오기
			PhonmeDTO dto = dao.getReadMember(mid);

			if (dto == null || !dto.getTel().equals(tel) || !dto.getName().equals(name)) {
				req.setAttribute("dto", dto);
				req.setAttribute("message", "회원정보가 존재하지 않습니다.");

				// 로그인 버튼으로
				url = "/jsp/login.jsp";
				forward(req, resp, url);
				return;

			}

			req.setAttribute("message", "비밀번호는" + dto.getPw() + "입니다.");

			url = "/jsp/login.jsp";
			forward(req, resp, url);
			return;

		} // end of pwSearch_ok.do
		
		
//idCheck_ok.do - 아이디 중복확인
		else if (uri.indexOf("idCheck_ok.do") != -1) {

			String mid = req.getParameter("mid");

			PhonmeDTO dto = dao.checkIdMember(mid);

			if (dto != null && dto.getMid().equals(mid)) {
				req.setAttribute("dto", dto);
				req.setAttribute("message", "이미 존재하는 아이디입니다.");

				url = "/jsp/member.jsp";
				forward(req, resp, url);
				return;

			} else {
				req.setAttribute("message", "사용가능한 아이디입니다.");
				req.setAttribute("mid", mid);

				url = "/jsp/member.jsp";
				forward(req, resp, url);
				return;

			}

		} // end of idCheck_ok.do	
		
		
//order.do - 주문하기 페이지
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
				
//order_ok.do - 주문완료 페이지
else if (uri.indexOf("order_ok.do") != -1) {
			
			// dao에 필요한 sql기능 만들기(order/odetail)
			// 서블릿에서 insert에 필요한 데이터 받기
			// 데이터는 order.jps에서 submit()으로 값 넘겼음
			// 받으려면 String ??? = req.getParameter("jsp에 있는 네임?") 하면 스트링으로 나옴
			// DTO 만들어서 받은 파라메타를 set시키고 dao.insert(dto)
			HttpSession session = req.getSession();
			Object ob = session.getAttribute("phonmeInfo");
			PhonmeInfo info = (PhonmeInfo) ob;
			String mid = info.getMid();

			int[] numI = null;
			int[] nums = null;
			PhonmeDTO dto = new PhonmeDTO();
			
			int omaxnum = dao.getOrderdMaxNum();
			int oprice = Integer.parseInt(req.getParameter("totprice"));// 폼에서 히든으로 받아와
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
			String[] ogid = req.getParameterValues("gid"); // 파라미터values는 name이여러개일대
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