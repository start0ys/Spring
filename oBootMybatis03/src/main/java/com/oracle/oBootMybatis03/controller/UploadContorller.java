package com.oracle.oBootMybatis03.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadContorller {
	private static final Logger logger = LoggerFactory.getLogger(UploadContorller.class);
	
	// upLoadForm 시작 화면
	@RequestMapping(value = "upLoadFormStart")
	public String upLoadFormStart(Model model) {
		return "upLoadFormStart";
	}
	
	
	@PostMapping(value = "uploadForm")
	public String uploadForm(HttpServletRequest request, MultipartFile file1, Model model) throws Exception {
		
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		logger.info("originalName : " + file1.getOriginalFilename());
		//logger.info("title : " + title);
		logger.info("Size : " + file1.getSize());
		logger.info("ContentType : " + file1.getContentType());
		logger.info("UploadPath : " + uploadPath);
		String saveName = uploadFile(file1.getOriginalFilename(), file1.getBytes(), uploadPath);
		logger.info("SaveName : " + saveName);
		model.addAttribute("saveName", saveName);
		return "uploadResult";
	}
	
	private String uploadFile(String orginalName, byte[] fileData, String uploadPath) throws Exception{
		UUID uid = UUID.randomUUID();
		//requestPath = requestPath + "/resources/image";
		// Directory 생성
		File fileDirectory = new File(uploadPath);
		if(!fileDirectory.exists()) {
			fileDirectory.mkdirs();
			System.out.println("업로드용 폴더 생성 : " + uploadPath);
		}
		String savedName = uid.toString() + "_" + orginalName;
		logger.info("SavedName : " + savedName);
		File target = new File(uploadPath,savedName);
		//File target = new File(requestPath,savedName);
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}
	
	
	@GetMapping(value = "uploadFileDelete")
	public String uploadFileDelete(HttpServletRequest request, Model model) throws Exception{
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		String deleteFile = uploadPath + "b07d5a94-d946-4777-bc5b-5ef2bd7e0bd1_시작.png";
		int delResult = upFileDelete(deleteFile);
		model.addAttribute("deleteFile", deleteFile);
		model.addAttribute("delResult", delResult);
		return "uploadResult";
	}
	
	  private int upFileDelete(String deleteFileName)   throws Exception {
		  int   result = 0;
		  File file = new File(deleteFileName); 
		  if( file.exists() ){ 
		  	if(file.delete()){ 
		  		System.out.println("파일삭제 성공"); 
		  		result = 1;
		  	}
		    else{ 
		    	System.out.println("파일삭제 실패"); 
		    	result = 0;
		   	} 
		  }
		  else{ 
			  System.out.println("파일이 존재하지 않습니다."); 
			  result = -1;
		  }
		  return result;
	  }
}
