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
  	    // 목록조회
  		List<Test1> testList = ts.testList();
  	    // 워크북 생성
  	    Workbook wb = new HSSFWorkbook();
  	    Sheet sheet = wb.createSheet("테스트 목록");
  	    Row row = null;
  	    Cell cell = null;
  	    int rowNo = 2;
	  	// 셀 너비 설정
	  	  for (int i=2; i<=5; i++){
	  	     sheet.autoSizeColumn(i);
	  	     sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+(short)1024);
	  	  }
  	    // 테이블 헤더용 스타일
  	    CellStyle headStyle = wb.createCellStyle();
  	    // 가는 경계선을 가집니다.
  	    headStyle.setBorderTop(BorderStyle.THIN);
  	    headStyle.setBorderBottom(BorderStyle.THIN);
  	    headStyle.setBorderLeft(BorderStyle.THIN);
  	    headStyle.setBorderRight(BorderStyle.THIN);
  	    // 배경색은 노란색입니다.
  	    headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
  	    headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
  	    // 데이터는 가운데 정렬합니다.
  	    headStyle.setAlignment(HorizontalAlignment.CENTER);
  	    headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
  	    // 데이터용 경계 스타일 테두리만 지정
  	    CellStyle bodyStyle = wb.createCellStyle();
  	    bodyStyle.setBorderTop(BorderStyle.THIN);
  	    bodyStyle.setBorderBottom(BorderStyle.THIN);
  	    bodyStyle.setBorderLeft(BorderStyle.THIN);
  	    bodyStyle.setBorderRight(BorderStyle.THIN);
  	    bodyStyle.setAlignment(HorizontalAlignment.CENTER);
  	    bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);

  	    //폰트 생성
  	    Font headerFont = wb.createFont();
  	    //headerFont.setColor(IndexedColors.WHITE.getIndex());
  	    headerFont.setBold(true);
  	    headStyle.setFont(headerFont); // 헤더 폰트적용
  	    
  	    // 헤더 생성
  	    row = sheet.createRow(rowNo++);
  	    row.setHeight((short)470);
  	    cell = row.createCell(2);
  	    cell.setCellStyle(headStyle);
  	    cell.setCellValue("넘버");
  	    cell = row.createCell(3);
  	    cell.setCellStyle(headStyle);
  	    cell.setCellValue("이름");
  	    cell = row.createCell(4);
  	    cell.setCellStyle(headStyle);
  	    cell.setCellValue("나이");
  	    cell = row.createCell(5);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("번호");
  	    // 데이터 부분 생성
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

  	    // 컨텐츠 타입과 파일명 지정
  	    response.setContentType("ms-vnd/excel");
  	    response.setHeader("Content-Disposition", "attachment;filename=testList.xls");

  	    // 엑셀 출력
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
		// Directory 생성
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
				  // 다운받을 파일의 전체 경로를 filePath에 저장
				  String filePath = uploadPath + fileName;
				  // 다운받을 파일을 불러옴
				  File file = new File(filePath);
				  byte b[] = new byte[4096];
				  // page의 ContentType등을 동적으로 바꾸기 위해 초기화시킴
				  response.reset();
				  response.setContentType("application/octet-stream");
				  // 한글 인코딩
				  String Encoding = new String(fileName.getBytes("UTF-8"), "8859_1");
				  // 파일 링크를 클릭했을 때 다운로드 저장 화면이 출력되게 처리하는 부분
				  response.setHeader("Content-Disposition", "attachment; filename = " + Encoding);
				  // 파일의 세부 정보를 읽어오기 위해서 선언
				  FileInputStream in = new FileInputStream(filePath);
				  // 파일에서 읽어온 세부 정보를 저장하는 파일에 써주기 위해서 선언
				  ServletOutputStream out2 = response.getOutputStream();
				  int numRead;
				  // 바이트 배열 b의 0번 부터 numRead번 까지 파일에 써줌 (출력)
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
