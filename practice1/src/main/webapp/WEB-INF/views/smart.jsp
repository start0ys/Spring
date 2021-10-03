<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/smarteditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<!-- 2.10.0 버전엔 js 파일 일부분이 없어 오류 발생 ! -->
</head>
<body>
	<form action="testEditor" method="post" name="frm" id="frm" style="text-align: center;">
		<input type="text" name="title" style="width: 80%; height: 25px; margin: 50px 0px 18px 0px;" placeholder="제목을 입력해주세요">
		<textarea id="testContent"  name="content" style="width: 100%"></textarea>
		<div><input type="button" onclick="save();" value="작성"></div>
	</form>
<!-- textarea 밑에 script 작성하기 -->
<script type="text/javascript"> 
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors,
	    elPlaceHolder: "testContent",  //textarea ID 입력
	    sSkinURI: "/smarteditor/SmartEditor2Skin.html",  //martEditor2Skin.html 경로 입력
	    fCreator: "createSEditor2",
	    htParams : { 
	        bUseToolbar : true,           // 툴바 사용 여부 (true:사용/ false:사용하지 않음) 
			bUseVerticalResizer : false,  // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음) 
			bUseModeChanger : false       // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음) 
	    }
	});
</script>
<script>
var frm = document.getElementById("frm");
function save(){
	oEditors.getById["testContent"].exec("UPDATE_CONTENTS_FIELD", []);  //스마트 에디터 값을 텍스트컨텐츠로 전달
	frm.submit(); // submit
	return; 
}
</script>
</body>
</html>