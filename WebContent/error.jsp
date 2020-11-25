<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="ko" oncontextmenu="return false" ondragstart="return false" onselectstart='return false'>
<head>
<meta charset="UTF-8">
<title>에러페이지 - <%=response.getStatus() %></title>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&family=Bebas+Neue&display=swap" rel="stylesheet">
<link rel="shortcut icon" href="/project/favicon.ico" type="image/x-icon">
<link rel="icon" href="/project/favicon.ico" type="image/x-icon">
<style type="text/css">
	*{
		margin: 0px 0px;
		padding: 0px 0px;
		cursor: default;
		font-family: "Bebas Neue";
	}
	*:focus{
		outline: none;
	}
	div {
		margin: auto;
		padding-top: 27vh;
		text-align: center;
	}
	img {
		transform: rotate(-2deg);
    	transition: transform ease 0.2s 0s;
	}
	img:hover {
		transform: rotate(1deg);
    	transition: transform ease 0.2s 0s;
	}
	h1 {
		font-size: 75px;
	}
	h2 {
		font-family: "Noto Sans KR";
		font-size: 20px;
		padding-bottom: 13px;
	}
	input {
		font-family: "Noto Sans KR";
		padding: 0px 10px;
		padding-bottom: 6px;
		font-size: 18px;
		color: rgb(230,230,230);
		background-color: white;
		border: 2px solid white;
		border-bottom: 2px solid rgb(230,230,230);
		cursor: pointer;
    	transition: border-bottom ease 0.4s 0s, color ease 0.4s 0s;
	}
	input:hover {
		color: rgb(180,180,180);
		border-bottom: 2px solid rgb(210,210,210);
    	transition: border-bottom ease 0.4s 0s, color ease 0.4s 0s;
	}
	
</style>
</head>
<body>
<div>
	<img alt="" src="/project/VIEW/img/icon/errorpolo.png" onclick="">
	<h1>ERROR - <%=response.getStatus() %></h1>
	<h2><% if(response.getStatus() == 400) { %>
		잘못된 요청입니다.
	<%} else if(response.getStatus() == 404) { %>
		찾을 수 없는 페이지입니다.
	<%} else if(response.getStatus() == 403) { %>
		비인가된 사용자입니다.
	<%} else if(response.getStatus() == 500) { %>
		서버 처리 오류가 발생했습니다.
	<%} else { %>
		알 수 없는 오류가 발생했습니다.
	<%} %>
	</h2>
	<input type="button" value="메인페이지로" onclick="location.href='/project/index.jsp';">
</div>
</body>
</html>