<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" %>
<!DOCTYPE html> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>도깨비어린이집</title>
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.1/jquery.mobile-1.4.1.min.css" />
	<script src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.4.1/jquery.mobile-1.4.1.min.js"></script>
</head>
<body>

<div data-role="page">
	<div data-role="header"  data-position="fixed" data-theme="d">
		<h2>도깨비 어린이집</h2>
		<a href="/menu.dkb" data-icon="back">메인</a>
		<div data-role="navbar">
			<ul>
				<li><a href="/list.dkb?p1=dokkaebi&sort=17">공지</a>
				<li><a href="/list.dkb?p1=dokkaebi&sort=466">수다방</a>
				<li><a href="/list.dkb?p1=dokkaebi&sort=1598">아빠</a>
				<li><a href="/list.dkb?p1=dokkaebi&sort=1592">소모임</a>
				<li><a href="/list.dkb?p1=dokkaebi&sort=1590">졸업생</a>
			</ul>
		</div>
	</div>
	<div data-role="content">	
		<div class="content-primary">
			<ul data-role="listview" data-inset="true" data-filter="true" data-theme="c"  data-theme="d" data-count-theme="b">
			<c:forEach items="${list }" var="row"> 
			<li><h3><a href="${row.convViewLink }" >${row.title} [${row.replyCnt}]<span class="ui-li-count">${row.viweCnt}</span></a></h3>
				<p>작성자 : ${row.writer} (작성일 : ${row.writeDate})</p>
			</li> 
			</c:forEach>	
			</ul> 
		</div>
	</div>
	<div data-role="footer" data-theme="a">
	<h4>
		<a href="tel:0319693412">(031-969-3412)</a> 경기도 고양시 덕양구 원흥동 410-6
	</h4>
	</div>
</div>
</body>
</html>
