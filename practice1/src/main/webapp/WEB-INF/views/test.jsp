<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
th{
	background-color: yellow;
}
a {
	text-decoration: none;
	color: black;
}
</style>
</head>
<body>
	<table border="1" style="text-align: center; margin: 0 auto;">
		<tr><th>넘버</th><th>이름</th><th>나이</th><th>번호</th><th>다운</th></tr>
		<c:forEach var="testList" items="${testList }">
			<tr>
				<td>${testList.test_num }</td>
				<td>${testList.test_name }</td>
				<td>${testList.test_age }</td>
				<td>${testList.test_phone }</td>
				<td>
					<c:if test="${not empty testList.test_file }">
						<a href="download?fileName=${testList.test_file }"><button>다운</button></a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div style="text-align: center;">
		<a href="excelDownload"><button>Excel</button></a>
	</div>
</body>
</html>