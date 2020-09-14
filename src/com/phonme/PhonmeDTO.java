/**
 * 주석 공간
 * 해당 파일의 목적과 메소드 목록 등을 적어주세요. 
 * PhonmeDTO파일에 멤버와 상품 테이블의 변수만 넣어놨어요. 
 * 필요에 따라 변수랑 getter/setter 업데이트하고 올려주세요. (팀룸에 알려주기)
 */

package com.phonme;

public class PhonmeDTO {
	
	//member
	private String mid;
	private String pw;
	private String name;
	private String addr;
	private String tel;
	private String mCreated;
	
	//product
	private String pid;
	private String pname;
	private String img;
	private int price;
	private int hit;
	private String content;
	private String color;
	private String pcreated;
	
	//gijong
	private String gid;
	private String gname;
	private int count;
	private int num;
	
	//order
	private int oid;
	private int hap;
	private String oCreated;
	
	//notice
	private String ntitle;
	private String ncontent;
	
	//review
	private String title;
	private String rCreated;
	private int rnum; 
	
	//QNA
	private String qcreated;
	
	//getter & setter
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getmCreated() {
		return mCreated;
	}
	public void setmCreated(String mCreated) {
		this.mCreated = mCreated;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPcreated() {
		return pcreated;
	}
	public void setPcreated(String pcreated) {
		this.pcreated = pcreated;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getHap() {
		return hap;
	}
	public void setHap(int hap) {
		this.hap = hap;
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getrCreated() {
		return rCreated;
	}
	public void setrCreated(String rCreated) {
		this.rCreated = rCreated;
	}
	public String getQcreated() {
		return qcreated;
	}
	public void setQcreated(String qcreated) {
		this.qcreated = qcreated;
	}
	public String getoCreated() {
		return oCreated;
	}
	public void setoCreated(String oCreated) {
		this.oCreated = oCreated;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	


}
