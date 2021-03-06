package com.oracle.practice1.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.practice1.model.Test1;
import com.oracle.practice1.service.Test1Service;


@Controller
public class PracticeController {
	
	@Autowired
	private Test1Service ts;
	
	
	@GetMapping(value = "smart")
	public String smart(Model model) {
		return "smart";
	}
	@PostMapping(value = "testEditor")
	public String testEditor(String title,String content,Model model) {
		System.out.println(title);
		System.out.println(content);
		model.addAttribute("title", title);
		model.addAttribute("content", content);
		
		return "smartResult";
	}
	
	
	
	@GetMapping(value = "test")
	public String start(Model model) {
		List<Test1> testList = ts.testList();
		model.addAttribute("testList", testList);
		return "test";
	}
	@GetMapping(value = "excelDownload")
  	public void excelDown(HttpServletResponse response) throws Exception {
  	    // ????????????
  		List<Test1> testList = ts.testList();
  	    // ????????? ??????
  	    Workbook wb = new HSSFWorkbook();
  	    Sheet sheet = wb.createSheet("????????? ??????");
  	    Row row = null;
  	    Cell cell = null;
  	    int rowNo = 2;
	  	// ??? ?????? ??????
	  	  for (int i=2; i<=5; i++){
	  	     sheet.autoSizeColumn(i);
	  	     sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+(short)1024);
	  	  }
  	    // ????????? ????????? ?????????
  	    CellStyle headStyle = wb.createCellStyle();
  	    // ?????? ???????????? ????????????.
  	    headStyle.setBorderTop(BorderStyle.THIN);
  	    headStyle.setBorderBottom(BorderStyle.THIN);
  	    headStyle.setBorderLeft(BorderStyle.THIN);
  	    headStyle.setBorderRight(BorderStyle.THIN);
  	    // ???????????? ??????????????????.
  	    headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
  	    headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
  	    // ???????????? ????????? ???????????????.
  	    headStyle.setAlignment(HorizontalAlignment.CENTER);
  	    headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
  	    // ???????????? ?????? ????????? ???????????? ??????
  	    CellStyle bodyStyle = wb.createCellStyle();
  	    bodyStyle.setBorderTop(BorderStyle.THIN);
  	    bodyStyle.setBorderBottom(BorderStyle.THIN);
  	    bodyStyle.setBorderLeft(BorderStyle.THIN);
  	    bodyStyle.setBorderRight(BorderStyle.THIN);
  	    bodyStyle.setAlignment(HorizontalAlignment.CENTER);
  	    bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);

  	    //?????? ??????
  	    Font headerFont = wb.createFont();
  	    //headerFont.setColor(IndexedColors.WHITE.getIndex());
  	    headerFont.setBold(true);
  	    headStyle.setFont(headerFont); // ?????? ????????????
  	    
  	    // ?????? ??????
  	    row = sheet.createRow(rowNo++);
  	    row.setHeight((short)470);
  	    cell = row.createCell(2);
  	    cell.setCellStyle(headStyle);
  	    cell.setCellValue("??????");
  	    cell = row.createCell(3);
  	    cell.setCellStyle(headStyle);
  	    cell.setCellValue("??????");
  	    cell = row.createCell(4);
  	    cell.setCellStyle(headStyle);
  	    cell.setCellValue("??????");
  	    cell = row.createCell(5);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("??????");
  	    // ????????? ?????? ??????
  	    for(Test1 test1 : testList) {
  	        row = sheet.createRow(rowNo++);
  	        row.setHeight((short)320);
  	        cell = row.createCell(2);
  	        cell.setCellStyle(bodyStyle);
  	        cell.setCellValue(test1.getTest_num());
  	        cell = row.createCell(3);
  	        cell.setCellStyle(bodyStyle);
  	        cell.setCellValue(test1.getTest_name());
  	        cell = row.createCell(4);
  	        cell.setCellStyle(bodyStyle);
  	        cell.setCellValue(test1.getTest_age());
  	        cell = row.createCell(5);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(test1.getTest_phone());
  	    }

  	    // ????????? ????????? ????????? ??????
  	    response.setContentType("ms-vnd/excel");
  	    response.setHeader("Content-Disposition", "attachment;filename=testList.xls");

  	    // ?????? ??????
  	    wb.write(response.getOutputStream());
  	    wb.close();

  	}
	
	@GetMapping(value = "upload")
	public String a() {
		return "upload";
	}
	@PostMapping(value = "upload")
	public String upload(HttpServletRequest request, MultipartFile file1,Test1 test1) throws Exception {
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		String saveName = uploadFile(file1.getOriginalFilename(), file1.getBytes(), uploadPath);
		test1.setTest_file(saveName);
		ts.testInsert(test1);
		return "redirect:test";
	}
	private String uploadFile(String orginalName, byte[] fileData, String uploadPath) throws Exception{
		UUID uid = UUID.randomUUID();
		// Directory ??????
		File fileDirectory = new File(uploadPath);
		if(!fileDirectory.exists()) {
			fileDirectory.mkdirs();
		}
		String savedName = uid.toString() + "_" + orginalName;
		File target = new File(uploadPath,savedName);
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}
	
	 @GetMapping(value = "download")
	  public void fileDownload(HttpServletRequest request, HttpServletResponse response,String fileName)
				throws ServletException, IOException {
			try {
				  request.setCharacterEncoding("utf-8");
				  String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
				  // ???????????? ????????? ?????? ????????? filePath??? ??????
				  String filePath = uploadPath + fileName;
				  // ???????????? ????????? ?????????
				  File file = new File(filePath);
				  byte b[] = new byte[4096];
				  // page??? ContentType?????? ???????????? ????????? ?????? ???????????????
				  response.reset();
				  response.setContentType("application/octet-stream");
				  // ?????? ?????????
				  String Encoding = new String(fileName.getBytes("UTF-8"), "8859_1");
				  // ?????? ????????? ???????????? ??? ???????????? ?????? ????????? ???????????? ???????????? ??????
				  response.setHeader("Content-Disposition", "attachment; filename = " + Encoding);
				  // ????????? ?????? ????????? ???????????? ????????? ??????
				  FileInputStream in = new FileInputStream(filePath);
				  // ???????????? ????????? ?????? ????????? ???????????? ????????? ????????? ????????? ??????
				  ServletOutputStream out2 = response.getOutputStream();
				  int numRead;
				  // ????????? ?????? b??? 0??? ?????? numRead??? ?????? ????????? ?????? (??????)
				  while((numRead = in.read(b, 0, b.length)) != -1){
				  out2.write(b, 0, numRead);
				  }  
				  out2.flush();
				  out2.close();
				  in.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} 
			
		}
		
}
