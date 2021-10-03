<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${name != '' }">
		${name}
	</c:if>
	<c:if test="${id != '' }">
		${id}
	</c:if>
	<% String conPath = request.getContextPath(); %>
	<form action="student/create">
		이름 : <input type="text" name="name" value="${student.name }"><p>
		아이디 : <input type="text" name="id" value="${student.id }"><p>
		<input type="submit" value="전송">
	</form>
	
</body>
</html>