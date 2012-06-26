<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" %>
<!DOCTYPE html> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>도깨비어린이집</title>
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.css" />
	<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.js"></script>
	<style>
	.reply{border:solid 1px #ccc;margin:5px;padding:10px}
	.reply .tit{border:solid 1px #ddd;margin:1px;padding:2px;background-color:#abe}
	</style>
</head>
<body>

<div data-role="page">
	<div data-role="header"  data-position="fixed">
		<h1>
			 ${view.title }
		</h1>
		${view.writer}
	</div>
	<div data-role="content">
	${view.contents1 }
	${view.contents2 }
	<c:forEach items="${view.reply}" var="reply">
	<div class="reply"><div class="tit">${reply.writer} - ${reply.writeDate}</div><br/>
	${reply.contents1}
	</div>
	</c:forEach>
	</div>
	<div data-role="footer"  data-position="fixed">
	<h4>
		도깨비어린이집 
	</h4>
	</div>
</div>
</body>
</html>
