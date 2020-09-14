package com.util;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBCPConn {
	
	//DBCP(DataBase Connection Pool)
	//데이터베이스와 연결된 커넥션을 미리 만들어서 POOL 영역에 
	//저장해두고있다가 필요할때 가져다 쓰고, 다시 POOL영역에 반환한다.
	
	private static Connection conn= null;
	
	public static Connection getConnection() {
		
		if(conn==null) {
			try {
				
				//이름과 객체를 바인딩 
				//javax.naming의 Context를 import
				Context ctx = new InitialContext();
				
				//web.xml에서 환경 설정을 찾음
				Context evt = (Context)ctx.lookup("java:/comp/env"); //얘는 정해진 문법이므로 바꾸면 안된다.
				//java.sql의 DataSource를 import
				DataSource ds = (DataSource)evt.lookup("jdbc/myOracle"); //web.xml의 res-ref-name과 동일해야함
				
				conn = ds.getConnection();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		
		return conn;
		
	}
	
	
	//close
	public static void close() {
		if(conn!=null) {
			try {
				
				if(!conn.isClosed()) {
					conn.close();
				}
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		
		conn = null;
	}
	
}
