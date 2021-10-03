<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String context = request.getContextPath();
    System.out.println("context->"+context);
%>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>


<script type="text/javascript" src="js/httpRequest.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script type="text/javascript">
   
    var contextPath='${pageContext.request.contextPath}';
	var src='${pageContext.request.contextPath}/images/';
	var contextPath='${pageContext.request.contextPath}';
	var i=2;
	var str="";
	var str2="";
	
	function getDeptName(Vdeptno){
		$.ajax({
			url:"<%=context%>/getDeptName",
			data:{deptno : Vdeptno},
			dataType:'text',
			success:function(data){
				$('#deptName').val(data);
				$('#msg').html(data);
			}
		});
	}

	/* RestController TEST */
	function getListDept(){
		str  = "";
		str2 = "";
		
		$.ajax({
			url:"<%=context%>/sendVO03",
			dataType:'json',
			success:function(data){
				var jsondata = JSON.stringify(data);
				$('#Dept_list').append(jsondata);
				str += "<select name = 'dept'>";
				$(data).each(
					function(){
						str2 = "<option value = '"+this.deptno + "'>"+this.dname + "</option>";
						str += str2;
					}		
				);
				str += "</select><p>";
				$('#Dept_list3').append(str);
			}
		});
	}
	
	function getDeptDelete(Vindex){
	//	console.log(Vindex);
		 alert("empDept->"+Vindex); 
		$('#empDept'+Vindex).remove();     /*  Delete Tag */
	}

	
 </script>
</head>
<body>
<h2>회원 정보</h2>
<table>
	<tr><th>번호</th><th>사번</th><th>이름</th><th>업무</th><th>부서</th><th>근무지</th></tr>
<c:forEach var="empDept" items="${listEmp}" varStatus="status">
	<tr id="empDept${status.index}"><td>empDept${status.index}</td><td>${empDept.empno }</td><td>${empDept.ename }</td>
		<td>${empDept.job }</td><td>${empDept.deptno } 
		    <%-- <input type="button" id="btn_idCheck2" value="부서Row Delete" onclick="getDeptDelete(${status.index})"> --%>
		    <input type="button" id="btn_idCheck" value="부서명" onmouseover="getDeptName(${empDept.deptno })">
		</td>
		<td>${empDept.loc }</td>
	</tr>
</c:forEach>
</table>
	 
	deptName:  <input type="text" id="deptName"  readonly="readonly"><p>
    Message :  <span id="msg"></span><p>
	
    <!--  RestController LISTVO3: <input type="text" id="RestDeptList"   readonly="readonly"><p> -->
	RestController LISTVO3: <input type="button" id="btn_Dept3"     value="부서명 LIST"  onclick="getListDept()"><p>
	                        <span id="RestDeptList"></span><p>
	                        			<div id="board_reply">
	Dept_list:	<div id="Dept_list"></div>

	Dept_list3:
	<div id="Dept_list3">

	</div>
</body>
</html>