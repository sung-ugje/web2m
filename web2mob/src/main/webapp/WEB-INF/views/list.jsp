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
	<div data-role="header"  data-position="fixed">
		<h1>
			도깨비어린이집 
		</h1>
	</div>
	<div data-role="content">	
		<ul>
		<li><a href="/list.dkb?p1=dokkaebi&sort=17">공지사항</a>
		<li><a href="/list.dkb?p1=dokkaebi&sort=1598">깨비네-아빠모임</a>
		<li><a href="/list.dkb?p1=dokkaebi&sort=1592">깨비네-소모임방</a>
		<li><a href="/list.dkb?p1=dokkaebi&sort=1590">깨비네-졸업생</a>
		<li><a href="/list.dkb?p1=dokkaebi&sort=468">자유게시판</a>
		<li><a href="/list.dkb?p1=dokkaebi&sort=468">가입문의(대기자의 방)</a>
		</ul>
		<ul data-role="listview" data-inset="true" data-filter="true">
		<c:forEach items="${list }" var="row">
		<li><a href="${row.convViewLink }" >${row.title}</a></li>
		</c:forEach>	
		</ul>
	</div>
	<div data-role="footer"  data-position="fixed">
	<h4>
		도깨비어린이집 
	</h4>
	</div>
</div>
</body>
</html>
