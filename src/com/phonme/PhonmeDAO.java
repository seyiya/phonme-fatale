/**
 * 주석
 * 
 *  ************채종완****************
 * getProductCount(서치)   : 상품 갯수 (검색)
 * searchList            : 검색 (기종+상품+매핑)
 * 
 * 
 *  ************김세이*******************
 * getMainHitList       	: 인기상품 10개 출력
 * getAllNewCount			: 신상 케이스 상품 갯수
 * getAllNewLists			: 신상 케이스 목록 (인기순으로 구현) : hit가 1이상이면 보이게
 * getNoticeMaxNum			
 * 
 *  ************경기민*******************
 * getProductCount()      : 상품 갯수 (전체)
 * 
 * 
 * getReadProduct(pid)      : 상품 1개 출력  (상세보기)
 * getProductLists(서치)   : 상품 전체 출력 (내림차순 최신순)
 * getOptions            : 상품의 검색옵션(기종) 호출
 * 
 * insertProduct         : 상품 등록 (폰케이스)
 * updateProduct         : 상품 수정
 * deleteProduct         : 상품 삭제
 * updateHit            : 조회수 증가
 * 
 * getListGijong         : 기종 전체 리스트
 * 
 * getMappingMaxNum         : 등록된 맵핑의 맥스넘
 * insertMapping         : 새로운 맵핑 등록
 * 
 *  ************서예지*******************
 * 
 */

package com.phonme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhonmeDAO {

   private Connection conn;

   public PhonmeDAO(Connection conn) {
      this.conn = conn;
   }


// 신상 케이스 상품 갯수 - search.jsp : 7일 이내로 등록된 상품
	public int getAllNewCount() {

		int totalDataCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select nvl(count(*),0) from ( ";
			sql += "select rownum rnum, data.* from ( ";
			sql += "select * from product ";
			sql += "where pcreated >= to_char(sysdate-7, 'YYYY-MM-DD') ";
			sql += "order by pcreated desc) data) where rnum>=1 and rnum<=12";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next())
				totalDataCount = rs.getInt(1);

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());

		}
		return totalDataCount;
	}
   
// 신상 케이스 목록 - search.jsp => 7일 이내의 신상품
	public List<PhonmeDTO> getAllNewLists(int start, int end) {
		List<PhonmeDTO> lists = new ArrayList<PhonmeDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {

			sql = "select * from (";
			sql += "select rownum rnum, data.* from (";
			sql += "select pid,pname,img,price,hit,content,color,pcreated ";
			sql += "from product where pcreated >= to_char(sysdate-7, 'YYYY-MM-DD') ";
			sql += "order by pcreated desc) data) ";
			sql += "where rnum>=? and rnum<=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				PhonmeDTO dto = new PhonmeDTO();

				dto.setPid(rs.getString("pid"));
				dto.setPname(rs.getString("pname"));
				dto.setImg(rs.getString("img"));
				dto.setPrice(rs.getInt("price"));
				dto.setHit(rs.getInt("hit"));
				dto.setContent(rs.getString("content"));
				dto.setColor(rs.getString("color"));
				dto.setPcreated(rs.getString("pcreated"));

				lists.add(dto);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lists;
	}
   
// 공지사항 최댓값
	public int getNoticeMaxNum() {
		int maxNum = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			sql = "select nvl(max(rownum),0) from Notice";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxNum = rs.getInt(1);
			} else {
				maxNum = 0;
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return maxNum;
	}

// 공지사항 추가
	public int insertNotice(PhonmeDTO dto) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "insert into notice (num,title,content,ncreated) ";
			sql += "values (?,?,?,sysdate)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, dto.getNtitle());
			pstmt.setString(3, dto.getNcontent());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;

	}
   
// 공지사항 데이터 갯수
	public int getNoticeCount() {
		int dataCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select nvl(count(*),0) from notice";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dataCount = rs.getInt(1);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return dataCount;
	}
   
// 공지사항 데이터 리스트 가져오기
	public List<PhonmeDTO> getNoticeLists(int start, int end) {

		List<PhonmeDTO> lists = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select * from (";
			sql += "select rownum rnum,data.* from(";
			sql += "select num,title,content,";
			sql += "to_char(ncreated,'YYYY-MM-DD HH:MI:SS') ncreated ";
			sql += "from notice order by num desc) data) ";
			sql += "where rnum>=? and rnum<=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				PhonmeDTO dto = new PhonmeDTO();

				dto.setNum(rs.getInt("num"));
				dto.setNtitle(rs.getString("title"));
				dto.setNcontent(rs.getString("content"));
				dto.setmCreated(rs.getString("ncreated"));

				lists.add(dto);

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}
   
// 등록된 케이스 개수의 최댓값 
	public int getProductMaxNum() {
		int maxNum = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			sql = "select nvl(max(rownum),0) from product";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxNum = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return maxNum;
	}
	
// 등록된 맵핑 갯수의 최대값
	public int getMappingMaxNum() {
		int maxNum = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			sql = "select nvl(max(num),0) from mapping";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxNum = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return maxNum;
	}
   
// 새로운 케이스 등록
	public int insertProduct(PhonmeDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		try {
			sql = "insert into product (pid,pname,img,price,hit,content,color) ";
			sql += "values (?,?,?,?,0,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPid());
			pstmt.setString(2, dto.getPname());
			pstmt.setString(3, dto.getImg());
			pstmt.setInt(4, dto.getPrice());
			pstmt.setString(5, dto.getContent());
			pstmt.setString(6, dto.getColor());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

// 새로운 맵핑 등록
	public int insertMapping(PhonmeDTO dto, String[] gijong, int mappingMaxNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		try {
			for (String str : gijong) {
				mappingMaxNum++;
				sql = "insert into Mapping (num,pid,gid) ";
				sql += "values (?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mappingMaxNum);
				pstmt.setString(2, dto.getPid());
				pstmt.setString(3, str);
				result = pstmt.executeUpdate();
			}
			;
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

// 상품의 옵션 호출
	public List<PhonmeDTO> getArticleOptions(String pid) {
		List<PhonmeDTO> lists = new ArrayList<PhonmeDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			sql = "select gid, gname from find where pid=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, pid);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				PhonmeDTO dto = new PhonmeDTO();
				dto.setGid(rs.getString("gid"));
				dto.setGname(rs.getString("gname"));
				lists.add(dto);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lists;
	}

// 케이스 내용 출력(내림차순)
	public List<PhonmeDTO> getProductLists(int start, int end, String searchkey, String searchValue) {
		List<PhonmeDTO> lists = new ArrayList<PhonmeDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			searchValue = "%" + searchValue + "%";

			sql = "select * from (";
			sql += "select rownum rnum, data.* from (";
			sql += "select * ";
			sql += "from product where " + searchkey + " like ? ";
			sql += ") data order by rnum desc) ";
			sql += "where rnum>=? and rnum<=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchValue);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				PhonmeDTO dto = new PhonmeDTO();

				dto.setPid(rs.getString("pid"));
				dto.setPname(rs.getString("pname"));
				dto.setImg(rs.getString("img"));
				dto.setPrice(rs.getInt("price"));
				dto.setHit(rs.getInt("hit"));
				dto.setContent(rs.getString("content"));
				dto.setColor(rs.getString("color"));

				lists.add(dto);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lists;
	}
	   
// head.jsp부분 케이스 검색 (pname이나 gname으로)
	public List<PhonmeDTO> getProductListsHead(int start, int end, String searchkey, String searchValue) {
		List<PhonmeDTO> lists = new ArrayList<PhonmeDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			searchValue = "%" + searchValue + "%";

			sql = "select * from (";
			sql += "select rownum rnum, data.* from (";
			sql += "select distinct pid,pname,img,price,hit,content,color   ";
			sql += "from find where pname like ? or gname like ?";
			sql += ") data order by rnum desc) ";
			sql += "where rnum>=? and rnum<=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchValue);
			pstmt.setString(2, searchValue);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				PhonmeDTO dto = new PhonmeDTO();

				dto.setPid(rs.getString("pid"));
				dto.setPname(rs.getString("pname"));
				dto.setImg(rs.getString("img"));
				dto.setPrice(rs.getInt("price"));
				dto.setHit(rs.getInt("hit"));
				dto.setContent(rs.getString("content"));
				dto.setColor(rs.getString("color"));

				lists.add(dto);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lists;
	}
   
// 조회수 증가
	public int updateHit(String pid) {
		int result = 0;

		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "update product set hit = hit + 1 where pid=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, pid);

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}
   
// 케이스상품 정보 수정
	public int updateProduct(PhonmeDTO dto) {

		int result = 0;

		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "update product set pname=?, img=?,price=?,hit=?, ";
			sql += "content=?, color=? where pid=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getPname());
			pstmt.setString(2, dto.getImg());
			pstmt.setInt(3, dto.getPrice());
			pstmt.setInt(4, dto.getHit());
			pstmt.setString(5, dto.getContent());
			pstmt.setString(6, dto.getColor());
			pstmt.setString(7, dto.getPid());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
	}

// 상품 상세보기
	public PhonmeDTO getReadProduct(String pid) {

		PhonmeDTO dto = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select pid,pname,img,price,hit,content,color ";
			sql += "from product where pid=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, pid);// 매개변수로 받은 num.where조건에 넣어줌

			rs = pstmt.executeQuery();

			if (rs.next()) {

				dto = new PhonmeDTO();

				dto.setPid(rs.getString("pid"));
				dto.setPname(rs.getString("pname"));
				dto.setImg(rs.getString("img"));
				dto.setPrice(rs.getInt("price"));
				dto.setHit(rs.getInt("hit"));
				dto.setContent(rs.getString("content"));
				dto.setColor(rs.getString("color"));

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return dto;
	}

// 기종 전체리스트
	public List<PhonmeDTO> getListGijong() {

		List<PhonmeDTO> lists = new ArrayList<PhonmeDTO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select * from GIJONG";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				PhonmeDTO dto = new PhonmeDTO();

				dto.setGid(rs.getString("gid"));
				dto.setGname(rs.getString("gname"));

				lists.add(dto);

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;

	}

// 상품 삭제
	public int deleteProduct(String pid) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "delete product where pid=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, pid);

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

// 케이스 상품 개수
	public int getProductCount(String SearchKey, String SearchValue) {

		int totalDataCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			SearchValue = "%" + SearchValue + "%";

			sql = "select nvl(count(*),0) from(";
			sql += "select * from product ";
			sql += "where " + SearchKey + " Like ?) ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, SearchValue);
			rs = pstmt.executeQuery();
			if (rs.next())
				totalDataCount = rs.getInt(1);

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());

		}
		return totalDataCount;
	}
   
// 케이스 상품 개수
	public int getGijongCount(String searchKey, String searchValue) {

		int totalDataCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			searchValue = "%" + searchValue + "%";

			sql = "select nvl(count(*),0) from(";
			sql += "select distinct pname from find ";
			sql += "where " + searchKey + " Like ?) ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchValue);
			rs = pstmt.executeQuery();
			if (rs.next())
				totalDataCount = rs.getInt(1);

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());

		}
		return totalDataCount;
	}

// head.jsp 검색을 위한 데이터 개수
	public int getHeadSearchCount(String SearchKey, String SearchValue) {

		int totalDataCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			SearchValue = "%" + SearchValue + "%";

			sql = "select nvl(count(*),0) from(";
			sql += "select distinct pname from find ";
			sql += "where pname Like ? or gname like ?) ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, SearchValue);
			pstmt.setString(2, SearchValue);
			rs = pstmt.executeQuery();
			if (rs.next())
				totalDataCount = rs.getInt(1);

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());

		}
		return totalDataCount;
	}	

// 장바구니 번호 최댓값 호출
	public int getCartMaxNum() {
		int maxNum = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			sql = "select nvl(max(num),0) from Cart";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxNum = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return maxNum;
	}
   
// 장바구니 추가
	public int insertCart(PhonmeDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		try {
			sql = "insert into CART (num,pid,gid,mid,count,color) ";
			sql += "values (?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, dto.getPid());
			pstmt.setString(3, dto.getGid());
			pstmt.setString(4, dto.getMid());
			pstmt.setInt(5, dto.getCount());
			pstmt.setString(6, dto.getColor());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
   
// 장바구니 전체리스트
	public List<PhonmeDTO> getListCart(String mid) {

		List<PhonmeDTO> lists = new ArrayList<PhonmeDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			sql = "select p.pid,c.num,pname,img,price,gname,c.color,count ";
			sql += "from cart c, gijong g, product p ";
			sql += "where c.mid=? and c.gid=g.gid and c.pid=p.pid";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PhonmeDTO dto = new PhonmeDTO();
				dto.setPid(rs.getString("pid"));
				dto.setNum(rs.getInt("num"));
				dto.setPname(rs.getString("pname"));
				dto.setImg(rs.getString("img"));
				dto.setPrice(rs.getInt("price"));
				dto.setHap(rs.getInt("price") * rs.getInt("count"));
				dto.setGname(rs.getString("gname"));
				dto.setColor(rs.getString("color"));
				dto.setCount(rs.getInt("count"));
				lists.add(dto);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;

	}
   
// 제조사 검색
	public List<PhonmeDTO> searchList(int start, int end, String searchkey, String searchValue) {

		List<PhonmeDTO> lists = new ArrayList<PhonmeDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			searchValue = "%" + searchValue + "%";
			sql = "select * from(";
			sql += "select rownum rnum, data.* from(";
			sql += "select * from product where pname in(";
			sql += "select distinct pname from find where " + searchkey + " like ?) order by hit desc) data) ";
			sql += "where rnum>=? and rnum<=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchValue);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				PhonmeDTO dto = new PhonmeDTO();

				dto.setPid(rs.getString("pid"));
				dto.setPname(rs.getString("pname"));
				dto.setImg(rs.getString("img"));
				dto.setPrice(rs.getInt("price"));
				dto.setHit(rs.getInt("hit"));
				dto.setContent(rs.getString("content"));
				dto.setColor(rs.getString("color"));

				lists.add(dto);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lists;
	}
   
// 찜 테이블 데이터 갯수 출력
	public int getJjimCount() {

		int totalDataCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select nvl(max(num),0) from jjim";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next())
				totalDataCount = rs.getInt(1);

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());

		}
		return totalDataCount;
	}
   
// 찜 전체 리스트 출력(첫화면)
	public List<PhonmeDTO> getListJjim(String mid) {
		List<PhonmeDTO> lists = new ArrayList<PhonmeDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "select j.pid,pname,img,price,hit ";
			sql += "from jjim j, product p ";
			sql += "where j.mid=? and j.pid=p.pid";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PhonmeDTO dto = new PhonmeDTO();
				dto.setPid(rs.getString("pid"));
				dto.setPname(rs.getString("pname"));
				dto.setImg(rs.getString("img"));
				dto.setPrice(rs.getInt("price"));
				dto.setHit(rs.getInt("hit"));
				lists.add(dto);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}
   
// 찜 추가
	public int insertJjim(PhonmeDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		try {
			sql = "insert into jjim (num,pid,mid) ";
			sql += "values (?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, dto.getPid());
			pstmt.setString(3, dto.getMid());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
   
// 찜 삭제
	public int deleteJjim(String pid, String mid) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "delete from jjim where pid=? and mid=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, pid);
			pstmt.setString(2, mid);

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}
   
// 장바구니 다중 삭제
	public int deleteCart(int[] numI) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "delete from cart where num=?";
			pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < numI.length; i++) {
				pstmt.setInt(1, numI[i]);
				result = pstmt.executeUpdate();
			}
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
   
// 장바구니 단일 삭제
	public int deleteCart(int num) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "delete from cart where num=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}
   
// 리뷰 전체 리스트 출력(첫화면)
	public List<PhonmeDTO> getListReview(int start, int end, String searchKey, String searchValue) {

		List<PhonmeDTO> lists = new ArrayList<PhonmeDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			searchValue = "%" + searchValue + "%";

			sql = "select * from(";
			sql += "select rownum rnum, data.* from (select num,r.pid,mid,title,r.content,r.rcreated,pname ";
			sql += "from review r, product p where " + searchKey + " like ? and r.pid=p.pid order by num desc) data) ";
			sql += "where rnum>=? and rnum<=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchValue);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PhonmeDTO dto = new PhonmeDTO();
				dto.setRnum(rs.getInt("rnum"));
				dto.setNum(rs.getInt("num"));
				dto.setPid(rs.getString("pid"));
				dto.setMid(rs.getString("mid"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setrCreated(rs.getString("rcreated"));
				dto.setPname(rs.getString("pname"));
				lists.add(dto);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;

	}
   
// 리뷰 특정 리스트
	public List<PhonmeDTO> getListReview(String searchKey, String searchValue) {

		List<PhonmeDTO> lists = new ArrayList<PhonmeDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			searchValue = "%" + searchValue + "%";

			sql = "select num,r.pid,mid,title,r.content,r.rcreated,pname ";
			sql += "from review r, product p where " + searchKey + " like ? and r.pid=p.pid order by num desc";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchValue);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PhonmeDTO dto = new PhonmeDTO();
				dto.setNum(rs.getInt("num"));
				dto.setPid(rs.getString("pid"));
				dto.setMid(rs.getString("mid"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setrCreated(rs.getString("rcreated"));
				dto.setPname(rs.getString("pname"));
				lists.add(dto);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;

	}
   
// 리뷰 추가
	public int insertReview(PhonmeDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		try {
			sql = "insert into review (num,pid,mid,title,content,rcreated) ";
			sql += "values (?,?,?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, dto.getPid());
			pstmt.setString(3, dto.getMid());
			pstmt.setString(4, dto.getTitle());
			pstmt.setString(5, dto.getContent());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
// 리뷰 삭제
   public int deleteReview(int num) {

	      int result = 0;
	      PreparedStatement pstmt = null;
	      String sql;
	      try {

	         sql = "delete from review where num=?";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, num);

	         result = pstmt.executeUpdate();
	         pstmt.close();
	      } catch (Exception e) {
	         System.out.println(e.toString());
	      }
	      return result;
	}

// 리뷰 수정
	public int updateReview(PhonmeDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		try {
			sql = "update review set title=?, content=?, rcreated=sysdate where num=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNum());
			
			result = pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

// 리뷰 테이블 데이터 갯수 출력
	public int getReviewCount(String searchKey, String searchValue) {

		int totalDataCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			searchValue = "%" + searchValue + "%";

			sql = "select nvl(count(*),0) from(";
			sql += "select r.pid, r.title, r.content, p.pname from Review r, product p ";
			sql += "where " + searchKey + " Like ? and r.pid=p.pid) ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchValue);
			rs = pstmt.executeQuery();
			if (rs.next())
				totalDataCount = rs.getInt(1);

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());

		}
		return totalDataCount;
	}
	
//리뷰글 한개 출력
	public PhonmeDTO getReadReview(int num) {

		PhonmeDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select r.num,r.pid,r.mid,r.title,r.content,p.pname,p.img ";
			sql += "from review r, product p ";
			sql += "where num=? and r.pid=p.pid";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto = new PhonmeDTO();
				dto.setNum(rs.getInt("num"));
				dto.setPid(rs.getString("pid"));
				dto.setMid(rs.getString("mid"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPname(rs.getString("pname"));
				dto.setImg(rs.getString("img"));
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return dto;

	}
   
// 등록된 케이스 갯수의 최대값 호출
   public int getReviewMaxNum() {
      int maxNum = 0;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql;
      try {
         sql = "select nvl(max(num),0) from review";
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            maxNum = rs.getInt(1);
         }
         rs.close();
         pstmt.close();
      } catch (Exception e) {
         System.out.println(e.toString());
      }
      return maxNum;
   }
   
// 구매내역 리스트
	public List<PhonmeDTO> getListOrderd(String mid) {
		List<PhonmeDTO> lists = new ArrayList<PhonmeDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "select to_char(o.ocreated,'yyyy-mm-dd hh24:mi') ocreated,p.pname,p.img,od.pid,od.count,od.color,od.num,";
			sql += "p.price,o.price hap,o.oid,od.gid ";
			sql += "from product p, odetail od, orderd o ";
			sql += "where o.oid=od.oid and od.pid=p.pid and o.mid=? order by ocreated desc";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PhonmeDTO dto = new PhonmeDTO();
				dto.setoCreated(rs.getString("ocreated"));
				dto.setPname(rs.getString("pname"));
				dto.setImg(rs.getString("img"));
				dto.setPid(rs.getString("pid"));
				dto.setCount(rs.getInt("count"));
				dto.setColor(rs.getString("color"));
				dto.setNum(rs.getInt("num"));
				dto.setOid(rs.getInt("oid"));
				dto.setPrice(rs.getInt("price"));
				dto.setHap(rs.getInt("hap"));
				dto.setGid(rs.getString("gid"));
				lists.add(dto);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;

	}

//주문내역 하나만 가져오기
	public PhonmeDTO getOrderdOne(int odNum) {

		PhonmeDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select od.num,od.pid,gid,mid,oid,od.color,od.count,pname ";
			sql += "from odetail od,product p ";
			sql += "where num=? and od.pid=p.pid";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, odNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto = new PhonmeDTO();
				dto.setNum(rs.getInt("num"));
				dto.setPid(rs.getString("pid"));
				dto.setGid(rs.getString("gid"));
				dto.setMid(rs.getString("mid"));
				dto.setOid(rs.getInt("oid"));
				dto.setColor(rs.getString("color"));
				dto.setCount(rs.getInt("count"));
				dto.setPname(rs.getString("pname"));
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;

	}
   
//장바구니 구매수량 변경
	public int updateCartCount(int num, int count) {

		int result = 0;

		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "update cart set count=? ";
			sql += "where num=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, count);
			pstmt.setInt(2, num);
			result = pstmt.executeUpdate();

			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;

	}
   
//장바구니 주문내역
	public List<PhonmeDTO> getOrderList(int[] cartNums, String mid) {

		List<PhonmeDTO> lists = new ArrayList<PhonmeDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select c.pid,c.gid,c.num,pname,img,price,gname,c.color,count ";
			sql += "from cart c, gijong g, product p where ";
			sql += "c.mid=? and c.gid=g.gid and c.pid=p.pid and c.num=?";
			pstmt = conn.prepareStatement(sql);

			for (int i : cartNums) {
				pstmt.setString(1, mid);
				pstmt.setInt(2, i);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					PhonmeDTO dto = new PhonmeDTO();
					dto.setNum(rs.getInt("num"));
					dto.setPname(rs.getString("pname"));
					dto.setImg(rs.getString("img"));
					dto.setPrice(rs.getInt("price"));
					dto.setHap(rs.getInt("price") * rs.getInt("count"));
					dto.setGname(rs.getString("gname"));
					dto.setColor(rs.getString("color"));
					dto.setCount(rs.getInt("count"));
					dto.setPid(rs.getString("pid"));
					dto.setGid(rs.getString("gid"));
					lists.add(dto);
				}
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;

	}

//입력 member.jsp -> member_ok.jsp
	public int insertMember(PhonmeDTO dto) {

		int result = 0;

		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "insert into member (mid,pw,name,addr,tel,mCreated) ";
			sql += " values (?,?,?,?,?,sysdate)";

			pstmt = conn.prepareStatement(sql);// 검사

			pstmt.setString(1, dto.getMid());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getAddr());
			pstmt.setString(5, dto.getTel());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());

		}

		return result;
	}

//전체데이터(list.jsp)
	public List<PhonmeDTO> getMemberList() {

		List<PhonmeDTO> lists = new ArrayList<PhonmeDTO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select mid,pw,name,addr,tel,mCreated from member ";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				PhonmeDTO dto = new PhonmeDTO();

				dto.setMid(rs.getString("mid"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
				dto.setTel(rs.getString("tel"));
				dto.setmCreated(rs.getString("mCreated"));

				lists.add(dto);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lists;
	}
   
//로그인 성공여부
	public boolean loginSuccess(PhonmeDTO dto) {
		// 한개 이상 (pk가 있어서 true/false로 체크가능)

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		boolean flag = false;

		try {

			sql = "select mid from member where mid=? and pw=? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMid());
			pstmt.setString(2, dto.getPw());
			rs = pstmt.executeQuery();
			flag = rs.next();// true면 로그인 성공, 없으면(false) 로그인 실패

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		return flag;
	}
   
// id중복확인 - true 중복(아이디 존재 확인)
	public PhonmeDTO checkIdMember(String mid) {

		PhonmeDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select mid from member where mid = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new PhonmeDTO();

				dto.setMid(rs.getString("mid"));
			}
			pstmt.close();
			rs.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;

	}

//회원정보 1개 불러오기
	public PhonmeDTO getReadMember(String mid) {

		PhonmeDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;

		try {
			sql = "select mid,pw,name,addr,tel,mcreated";
			sql += " from member where mid=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new PhonmeDTO();

				dto.setMid(rs.getString("mid"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
				dto.setTel(rs.getString("tel"));
				dto.setmCreated(rs.getString("mcreated"));

			}
			pstmt.close();
			rs.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}
   
//회원정보 수정
//수정(updated.jsp --> updated_ok.jsp)
	public int updateMember(PhonmeDTO dto) {

		int result = 0;

		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "update member set pw=?,name=?,addr=?,tel=?";
			sql += " where mid=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getAddr());
			pstmt.setString(4, dto.getTel());
			pstmt.setString(5, dto.getMid());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;

	}

//자신이 쓴 Q&A 불러오기
	public PhonmeDTO getReadQna(String mid) {

		PhonmeDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;

		try {
			sql = "select num,mid,title,content,qcreated ";
			sql += " from qna where mid=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new PhonmeDTO();

				dto.setNum(rs.getInt("num"));
				dto.setMid(rs.getString("mid"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setQcreated(rs.getString("qcreated"));

			}
			pstmt.close();
			rs.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}
   
// Q&A 삽입하기 qnaWrite.jsp -> qnaWrite_ok.jsp
	public int insertQna(PhonmeDTO dto) {

		int result = 0;

		PreparedStatement pstmt = null;
		String sql;
		

		try {
			sql = "insert into qna (num,mid,title,content,qcreated) ";
			sql += " values (?,?,?,?,sysdate)";

			pstmt = conn.prepareStatement(sql);// 검사

			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, dto.getMid());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
   
// Q&A 최댓값 불러오기
	public int getQnaMaxNum() {

		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		int maxNum = 0;

		try {

			sql = "select nvl(max(num),0) from QNA";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next())
				maxNum = rs.getInt(1);

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return maxNum;
	}
      
// 전체 Q&A 불러오기
	public List<PhonmeDTO> getAllQna(int start, int end) {

		List<PhonmeDTO> lists = new ArrayList<PhonmeDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;

		try {
			sql = "select * from " + "(select rownum rnum, data.* from "
					+ "(select * from QNA ORDER by num desc) data) " + "where rnum>=? and rnum<=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				PhonmeDTO dto = new PhonmeDTO();
				dto.setNum(rs.getInt("num"));
				dto.setMid(rs.getString("mid"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setQcreated(rs.getString("qcreated"));

				lists.add(dto);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}

// Q&A 갯수 불러오기 (datacount)
	public int getQnaDataCount() {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		int dataCount = 0;

		try {

			sql = "select nvl(count(*),0) from qna";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next())
				dataCount = rs.getInt(1);

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dataCount;
	}

// 마이페이지 Q&A 불러오기
	public List<PhonmeDTO> getMyQna(String mid) {

		List<PhonmeDTO> lists = new ArrayList<PhonmeDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;

		try {
			sql = "select num, mid, title, content, qcreated from QNA where mid = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				PhonmeDTO dto = new PhonmeDTO();
				dto.setNum(rs.getInt("num"));
				dto.setMid(rs.getString("mid"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setQcreated(rs.getString("qcreated"));

				lists.add(dto);
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}
	
// idSearch: 아이디 찾기
	public PhonmeDTO getReadMemberId(String name,String tel) {

		PhonmeDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;

		try {
			sql = "select mid,name,tel from member where tel=? and name=? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tel);
			pstmt.setString(2, name);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new PhonmeDTO();

				dto.setMid(rs.getString("mid"));
				dto.setName(rs.getString("name"));
				dto.setTel(rs.getString("tel"));

			}
			pstmt.close();
			rs.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		return dto;

	}

// 주문 테이블에 정보 넣기
	public int insertOrderd(PhonmeDTO dto) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		try {
			sql = "insert into Orderd (oid,mid,Ocreated,price,addr,tel) ";
			sql += "values (?,?,sysdate,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getOid());
			pstmt.setString(2, dto.getMid());
			pstmt.setInt(3, dto.getPrice());
			pstmt.setString(4, dto.getAddr());
			pstmt.setString(5, dto.getTel());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

// 주문상세 테이블에 정보넣기
	public int insertOdetail(PhonmeDTO dto) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		try {
			sql = "insert into Odetail (num,pid,gid,mid,oid,count,color) ";
			sql += "values (?,?,?,?,?,?,?) ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, dto.getPid());
			pstmt.setString(3, dto.getGid());
			pstmt.setString(4, dto.getMid());
			pstmt.setInt(5, dto.getOid());
			pstmt.setInt(6, dto.getCount());
			pstmt.setString(7, dto.getColor());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
// 등록된 주문 넘버의 최댓값
	public int getOrderdMaxNum() {
		int maxNum = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			sql = "select nvl(max(oid),0) from orderd";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxNum = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return maxNum;
	}
	
// 등록된 주문상세(odetail) 넘버의 최댓값
	public int getODetailMaxNum() {
		int maxNum = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			sql = "select nvl(max(num),0) from odetail ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxNum = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return maxNum;
	}

}