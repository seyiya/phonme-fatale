package com.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//File �ٿ�ε� & ����

public class FileManager {
	
	//���� �ٿ�ε�
	//saveFileName : ������ ����� ���ϸ�
	//originalFileNaem : Ŭ�� �ø� ���ϸ�
	//path : �����Ŀ� ����� ���� ��ġ

	//Ŭ�󿡰� �����ؼ� ���� �Ѱ��ָ� �ǹǷ�, response�� ������ �ȴ�
	
	public static boolean doFileDownload
		(HttpServletResponse response, String saveFileName, String originalFileName, String path) {
		
		try {
			//��� + ���ϸ�
			String filePath = path + File.separator + saveFileName;
			
			
			if(originalFileName==null || originalFileName.equals("")) {
				originalFileName = saveFileName;
			}
			
			//������ �ٿ� �޾� �����Ҷ�, �����̸� ���� ��, �ѱ۱��� ����
			//���ε� : ISO-8859-1 -> euc-kr
			//�ٿ�ε� : euc-kr -> ISO-8859-1
			originalFileName = new String(originalFileName.getBytes("euc-kr"), "ISO-8859-1");
			
			File f= new File(filePath);
			
			if(!f.exists()) {
				return false;
			}
			
			
			//���������
			//(��������/����Ȯ����) - �𸣰����� unknown�̶� ���
			response.setContentType("application/octet-stream"); 
			response.setHeader("Content-disposition", "attachment;fileName=" + originalFileName);
			
			
			//�����ͺ�����
			//(�����Ŀ��� �а�(fis), Ŭ�󿡰� ����(out))
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
			OutputStream out = response.getOutputStream();
			
			int n; 
			byte[] bytes = new byte[4096]; //4096����Ʈ ¥�� ����
			
			while((n=bis.read(bytes,0,4096))!=-1) {//������ �б�
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
	
	//���� ����
	public static void doFileDelete(String fileName, String path) {
		
		try {
			
			String filePath = path + File.separator + fileName;
			
			File f = new File(filePath);
			
			if(f.exists())
				f.delete(); //����
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
	}

}

































