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
</head>
<body>

<div data-role="page">
	<div data-role="header"  data-position="fixed"  data-theme="c">
		<a href="list.dkb?p1=dokkaebi&sort=${sort}" data-icon="back">목록</a>
		<h5> ${view.title }</h5>
	</div>
	<div data-role="content"  data-theme="c">
	<p>
	${view.contents1 }
	</p><p>
	${view.contents2 }
	</p>
	<c:forEach items="${view.reply}" var="reply">
	<div data-role="collapsible" data-collapsed="false" data-theme="e" data-content-theme="c">
	<h3>${reply.writer}</h3>
	<p>${reply.contents1}</p>
	<p style="text-align:right">-- ${reply.writeDate}</p>
	</div>
	</c:forEach>
	</div>
	<div data-role="footer"  data-theme="d">
		<h6>${view.writer} (${view.writeDate})</h6>
	</div>
</div>
</body>
</html>
