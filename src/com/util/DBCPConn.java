package com.util;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBCPConn {
	
	//DBCP(DataBase Connection Pool)
	//�����ͺ��̽��� ����� Ŀ�ؼ��� �̸� ���� POOL ������ 
	//�����صΰ��ִٰ� �ʿ��Ҷ� ������ ����, �ٽ� POOL������ ��ȯ�Ѵ�.
	
	private static Connection conn= null;
	
	public static Connection getConnection() {
		
		if(conn==null) {
			try {
				
				//�̸��� ��ü�� ���ε� 
				//javax.naming�� Context�� import
				Context ctx = new InitialContext();
				
				//web.xml���� ȯ�� ������ ã��
				Context evt = (Context)ctx.lookup("java:/comp/env"); //��� ������ �����̹Ƿ� �ٲٸ� �ȵȴ�.
				//java.sql�� DataSource�� import
				DataSource ds = (DataSource)evt.lookup("jdbc/myOracle"); //web.xml�� res-ref-name�� �����ؾ���
				
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
