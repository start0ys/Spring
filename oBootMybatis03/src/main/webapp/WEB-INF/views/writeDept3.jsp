<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ include file="header.jsp" %>
<!DOCTYPE html><html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

 <h2>부서정보 입력</h2>
 <c:if test="${msg!=null}">${msg}</c:if>
	<form action="writeDept" method="post" name="frm">
		<table>
			<tr><th>부서번호</th><td><input type="number" name="deptno" 
				required="required" maxlength="2" ></td></tr>
			<tr><th>부서이름</th><td><input type="text" name="dname" 
				required="required"> </td></tr>
			<tr><th>부서위치</th><td><input type="text" name="loc" 
				required="required"></td></tr>
		
			<tr><td colspan="2">
			<input type="submit" value="확인"></td></tr>
			
		</table>
	    <%-- ${deptVO.Oloc} --%>
		입력된 부서번호 :<c:if test="${dept.odeptno!=null}">${dept.odeptno}</c:if><p>
	         입력된 부서명   :<c:if test="${dept.odname!=null}">${dept.odname}</c:if><p> 
	         입력된 부서위치 :<c:if test="${dept.oloc!=null}">${dept.oloc}</c:if><p> 
	        
	 
	        
	</form>   
	
</body>
</html>