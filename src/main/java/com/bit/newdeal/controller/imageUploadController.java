package com.bit.newdeal.controller;

import java.io.InputStream;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.UUID;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class imageUploadController {

	// 다중파일업로드
	@RequestMapping(value = "multiplePhotoUpload.do")
	public void multiplePhotoUpload(HttpServletRequest request, HttpServletResponse response) {
		try {
	         //파일정보
	         String sFileInfo = "";
	         //파일명을 받는다 - 일반 원본파일명
	         String filename = request.getHeader("file-name");
	         //파일 확장자
	         String filename_ext = filename.substring(filename.lastIndexOf(".")+1);
	         //확장자를소문자로 변경
	         filename_ext = filename_ext.toLowerCase();
	         //파일 기본경로
	         String dftFilePath = request.getSession().getServletContext().getRealPath("/");
	         
	         //파일 기본경로 _ 상세경로
	         String filePath = dftFilePath + "resources" + File.separator + "img" + File.separator
	                           + "boardContent" + File.separator;
	         
	         File file = new File(filePath);
	         System.out.println("file : " + file);
	         if(!file.exists()) {
	            file.mkdirs();
	         }
	         
	         String realFileNm = "";
	         SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	         String today= formatter.format(new java.util.Date());
	         realFileNm = today+UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
	         String rlFileNm = filePath + realFileNm;
	         
	         System.out.println("rlFileNm : " + rlFileNm);
	         
	         ///////////////// 서버에 파일쓰기 /////////////////
	         InputStream is = request.getInputStream();
	         
	         OutputStream os = new FileOutputStream(rlFileNm);
	         
	         int numRead;
	         byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
	         
	         while((numRead = is.read(b,0,b.length)) != -1){
	            os.write(b,0,numRead);
	         }
	         if(is != null) {
	            is.close();
	         }
	         os.flush();
	         os.close();
	         
	         // 정보 출력/////////////////////////////////////////////////
	         sFileInfo += "&bNewLine=false";
	         // img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함
	         sFileInfo += "&sFileName="+ filename;;
	         sFileInfo += "&sFileURL=" + "http://192.168.0.85:8888/img/boardContent/" + realFileNm;
	         
	         System.out.println("sFileInfo : " + sFileInfo);
	         PrintWriter out = response.getWriter();
	         
	         out.print(sFileInfo);
	         
	         out.flush();
	         out.close();
	      
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
