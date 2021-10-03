package com.oracle.oBootMybatis03.controller;

import java.util.HashMap;
import java.util.List;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.DeptVO;
import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.model.EmpDept;
import com.oracle.oBootMybatis03.model.Member1;
import com.oracle.oBootMybatis03.service.EmpService;
import com.oracle.oBootMybatis03.service.Paging;

@Controller
public class EmpController {
	@Autowired
	private EmpService es;
	
	
	@RequestMapping(value = "list")
	public String list(Emp emp, String currentPage, Model model) {
		int  total = es.total();
		
		Paging pg = new Paging(total, currentPage);
		emp.setStart(pg.getStart());  //시작시 1
		emp.setEnd(pg.getEnd());      //시작시 10
		
		List<Emp> listEmp = es.listEmp(emp);
		
		model.addAttribute("total",total);
		model.addAttribute("listEmp",listEmp);
		model.addAttribute("pg",pg);
		return "list";
	}
	
	@GetMapping(value = "detail")
	public String detail(int empno, Model model) {
		Emp emp = es.detail(empno);
		model.addAttribute("emp", emp);
		return "detail";
	}
	
	@GetMapping(value = "updateForm")
	public String updateForm(int empno, Model model) {
		Emp emp = es.detail(empno);
		model.addAttribute("emp", emp);
		return "updateForm";
	}
	
	@PostMapping(value = "update")
	public String update(Emp emp, Model model) {
		int k = es.update(emp);
		model.addAttribute("kkk", k);
		model.addAttribute("kk3", "forward Test");
//		return "redirect:list";
		return "forward:list"; //Controller 간 Data 전달
	}
	
	@RequestMapping(value = "writeForm")
	public String writeForm(Model model) {
		List<Emp> list = es.listManager();
		model.addAttribute("empMngList",list);
		List<Dept> deptList = es.deptSelect();
		model.addAttribute("deptList",deptList);
		return "writeForm";
	}
	@PostMapping(value = "write")
	public String write(Emp emp, Model model) {
		int result = es.insert(emp);
		if(result > 0) return "redirect:list";
		else {
			model.addAttribute("msg", "입력 실패 확인해 보세요");
			return "forward:writeForm";
		}
	}
	
	@GetMapping(value = "confirm")
	public String confirm(int empno, Model model) {
		Emp emp = es.detail(empno);
		model.addAttribute("empno", empno);
		if (emp != null) {
			model.addAttribute("msg", "중복된 사번입니다");
			return "forward:writeForm";
		} else {
			model.addAttribute("msg", "사용 가능한 사번입니다");
			return "forward:writeForm";
		}
		
	}
	
	@RequestMapping(value = "delete")
	public String delete(int empno, Model model) {
		int k = es.delete(empno);
		return "redirect:list";
	}
	
	@GetMapping(value = "listEmpDept")
	public String listEmpDept(Model model) {
		EmpDept empDept = null;
		List<EmpDept> listEmpDept = es.listEmpDept();
		model.addAttribute("listEmpDept", listEmpDept);
		return "listEmpDept";
	}
	
	///////매일 전송
	@Autowired
	private JavaMailSender mailSender;
	
	
	@RequestMapping(value = "mailTransport")
	public String mailTransport(HttpServletRequest request, Model model) {
		String tomail  = "aaa@naver.com";    // 받는 사람 이메일
		String setfrom = "aaaa@gmail.com";      // 보내는 사람 이메일
		String title   = "메일 Test 입니다";      // 제목
		
		try {
			//Mime 전자우편 Internet 표준 Format
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,true,"UTF-8");
			messageHelper.setFrom(setfrom);  // 보내는사람 생략하거나 하면 정삭작동을 안함
			messageHelper.setTo(tomail);	 // 받는사람 이메일
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다.
			String tempPassword = (int) (Math.random() * 999999) + 1 + "";
			messageHelper.setText("임시 비밀번호입니다 : " + tempPassword); // 메일 내용
			System.out.println("임시 비밀번호입니다 : " + tempPassword);
			DataSource dataSource = new FileDataSource("c:\\log\\jung1.jpg");
			messageHelper.addAttachment(MimeUtility.encodeText("airport.png", "UTF-8", "B"), dataSource);
			mailSender.send(message);
			model.addAttribute("check", 1); // 정상 전달
//			s.tempPw(u_id, tempPassword); // db에 비밀번호를 임시비밀번호로 업데이트
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("check", 2); // 메일 전달 실패
		}
		
		return "mailResult";
	}
	
	@GetMapping(value = "writeDeptIn")
	public String writeDeptIn(Model model) {
		return "writeDept3";
	}
	
	@PostMapping(value = "writeDept")
	public String writeDept(DeptVO deptVO, Model model) {
		// DeptVO rDeptVO = es.insertDept(deptVO); // 일반 Service
		
		es.insertDept(deptVO); // Procedure Call
		
		if(deptVO == null) {
			System.out.println("deptVO NULL");
		}else {
			model.addAttribute("msg", "정상 입력 되었습니다");
			model.addAttribute("dept", deptVO);
		}
		return "writeDept3";
	}
	
	@GetMapping("writeDeptCursor")
	public String writeDeptCursor (Model model) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sDeptno",10);
		map.put("eDeptno",55);
		es.selListDept(map);
		List<Dept> deptList = (List<Dept>) map.get("dept");
		model.addAttribute("deptList",deptList);
		return "writeDeptCursor";
	}
	// interCepter 시작 화면 
	@GetMapping(value = "interCepterForm")
	public String interCepterForm(Model model) {
		return "interCepterForm";
	}
	// interCepter 진행 Test  2
	@GetMapping(value = "interCepter")
	public String interCepter(String id, Model model) {
		int memCnt = es.memCount(id);
		model.addAttribute("id", id);
		model.addAttribute("memCnt", memCnt);
		
		return "interCepter"; // User 존재하면 User 이용 조회 Page
		
	}
	 // interCepter 진행 Test
	@GetMapping(value = "doMemberList")
	public String doMemberList(Model model, HttpServletRequest request) {
		String ID = (String) request.getSession().getAttribute("ID");
		Member1 member1 = null;
		// Member1 List Get Service
		List<Member1> listMem = es.listMem(member1);
		model.addAttribute("ID", ID);
		model.addAttribute("listMem", listMem);
		return "doMemberList"; // User 존재하면 User 이용 조회 Page
	}
	 // SampleInterceptor 내용을 받아 처리 
	@GetMapping(value = "doMemberWrite")
	public String doMemberWrite(Model model, HttpServletRequest request) {
		String ID = (String) request.getSession().getAttribute("ID");
		model.addAttribute("id", ID);
		return "doMemberWrite";
	}
	
	//Ajax
	@RequestMapping(value = "getDeptName", produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String getDeptName(int deptno, Model model) {
		return es.deptName(deptno);
	}
	
	@RequestMapping(value = "listEmpAjax")
	public String listEmpAjax(Model model) {
		EmpDept empDept = null;
		List<EmpDept> listEmp = es.listEmp();
		model.addAttribute("result", "kkk");
		model.addAttribute("listEmp", listEmp);
		return "listEmpAjax";
	}
	
	@RequestMapping(value = "listEmpAjax2")
	public String listEmpAjax2(Model model) {
		EmpDept empDept = null;
		List<EmpDept> listEmp = es.listEmp();
		model.addAttribute("listEmp", listEmp);
		return "listEmpAjax2";
	}
}
