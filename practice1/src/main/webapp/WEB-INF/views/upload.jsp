<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
	<form action="upload" method="post" enctype="multipart/form-data">
		이름 : <input type="text" name="test_name"><p>
		나이 : <input type="text" name="test_age"><p>
		번호 : <input type="text" name="test_phone"><p>
		파일 : <input type="file" name="file1" id="uploadBtn"  accept=".pdf" onchange="setThumbnail(event);"><p>
		<div id="pdf"></div>
		<input type="submit" value="제출">
	</form>
<script type="text/javascript">
function setThumbnail(event) { 
	$('#pdf *').remove();
	var agent = navigator.userAgent.toLowerCase();
    // 파일 업로드 확장자 체크
    if( $("#uploadBtn").val() != "" ){
      	var ext = $('#uploadBtn').val().split('.').pop().toLowerCase();
 	    if($.inArray(ext, ['pdf']) == -1) {
 	     	alert('pdf파일만 업로드 해주세요.');
 			//파일초기화
 			if ( (navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) || (agent.indexOf("msie") != -1) ) {
 				$("#uploadBtn").replaceWith($("#uploadBtn").clone(true));
 			}else{
 		    	$("#uploadBtn").val("");
 			}
 	     	return;
	  	}
     }
	
	var reader = new FileReader(); 
	reader.onload = function(event) { 
		var iframe = document.createElement("iframe");
		iframe.setAttribute("width", "40%");
		iframe.setAttribute("height", "500");
		iframe.setAttribute("src", event.target.result); 
		document.querySelector('#pdf').appendChild(iframe); 
	}; 
	reader.readAsDataURL(event.target.files[0]); 
	
	
	
/* 	var pdfName = '${rcvDetail.apv_pl_nm}';
	if(pdfName){
		var pdfPath = '<iframe width="80%" height="550" src="../upload/${rcvDetail.apv_pl_nm}" style="margin-top: 10px;" />';
		$('#pdf').append(pdfPath);	
	} */
}
</script>
</body>
</html>