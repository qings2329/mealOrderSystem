<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>try attacking</title>
<link rel="stylesheet" href="/css/attack.css" type="text/css" />

</head>
<body>
<!-- 	<iframe onload="test()" src="http://0317love.com/gprs.php"></iframe> -->
<!-- 	<iframe src="http://0317love.com/gprs.php"></iframe> -->
		<iframe id="attackiframe" name="gprs"></iframe>
</body>
<!-- <script src="/js/attack.js"> -->
<!-- </script> -->
<script type="text/javascript">

    //这种设置方法是错误的
//	window.gprs.src = "http://0317love.com/gprs.php";
    
    
// 	document.getElementById("attackiframe").src = "http://0317love.com/gprs.php";
	var conw = document.getElementById("attackiframe").contentWindow;
	var iframeDoc = document.getElementById("attackiframe").contentWindow.document;
// 	iframeDoc.write('<script src="/js/attack.js?random=' + Math.random() + '"><\/script>');
	
	function test(){
		console.log("onload()")
	}
</script>
</html>