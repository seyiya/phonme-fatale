package com.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//File 다운로드 & 삭제

public class FileManager {
	
	//파일 다운로드
	//saveFileName : 서버에 저장된 파일명
	//originalFileNaem : 클라가 올린 파일명
	//path : 서버컴에 저장된 로컬 위치

	//클라에게 응답해서 파일 넘겨주면 되므로, response만 있으면 된다
	
	public static boolean doFileDownload
		(HttpServletResponse response, String saveFileName, String originalFileName, String path) {
		
		try {
			//경로 + 파일명
			String filePath = path + File.separator + saveFileName;
			
			
			if(originalFileName==null || originalFileName.equals("")) {
				originalFileName = saveFileName;
			}
			
			//파일을 다운 받아 저장할때, 파일이름 생성 시, 한글깨짐 방지
			//업로드 : ISO-8859-1 -> euc-kr
			//다운로드 : euc-kr -> ISO-8859-1
			originalFileName = new String(originalFileName.getBytes("euc-kr"), "ISO-8859-1");
			
			File f= new File(filePath);
			
			if(!f.exists()) {
				return false;
			}
			
			
			//헤더보내기
			//(파일종류/파일확장자) - 모르겠으면 unknown이라 명시
			response.setContentType("application/octet-stream"); 
			response.setHeader("Content-disposition", "attachment;fileName=" + originalFileName);
			
			
			//데이터보내기
			//(서버컴에서 읽고(fis), 클라에게 쓴다(out))
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
			OutputStream out = response.getOutputStream();
			
			int n; 
			byte[] bytes = new byte[4096]; //4096바이트 짜리 버퍼
			
			while((n=bis.read(bytes,0,4096))!=-1) {//끝까지 읽기
				out.write(bytes ,0 ,n);
			}
			
			out.flush();
			out.close();
			bis.close();
			
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		return true;
		
	}
	
	//파일 삭제
	public static void doFileDelete(String fileName, String path) {
		
		try {
			
			String filePath = path + File.separator + fileName;
			
			File f = new File(filePath);
			
			if(f.exists())
				f.delete(); //삭제
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
	}

}

































