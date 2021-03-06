<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="ko" oncontextmenu="return false" ondragstart="return false" onselectstart='return false'>
<head>
    <meta charset="UTF-8">
    <title><c:out value="${categoryALLName}"></c:out> 콘텐츠 선택</title>
    <script src="/project/VIEW/JS/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="/project/VIEW/CSS/contentStyle.css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="/project/favicon.ico" type="image/x-icon">
	<link rel="icon" href="/project/favicon.ico" type="image/x-icon">
</head>
<svg version="1.1" xmlns="http://www.w3.org/2000/svg">
    <filter id="blur">
        <feGaussianBlur stdDeviation="3" />
    </filter>
</svg>
<body class="bgBlur" style="
	<c:if test="${categoryName eq 'LOL'}"> background-image: url('/project/VIEW/img/back/backimg1.jpg');</c:if>
	<c:if test="${categoryName eq 'BG'}"> background-image: url('/project/VIEW/img/back/backimg2.jpg');</c:if>
	<c:if test="${categoryName eq 'OW'}"> background-image: url('/project/VIEW/img/back/backimg3.jpg');</c:if>
	">
<%if(request.getAttribute("categoryName") != null) { %>
    <div id="smoke">
        <div id="mainContentsDiv">
            <input type="button" id="boardBtn" value="대화방" onclick="location.href = 'contents/board?categoryName=<c:out value='${categoryName}'></c:out>'">
            <input type="button" id="playLogBtn" value="전적검색" onclick="alert('아직 안만듬')">
            <input type="button" id="newsBtn" value="게임소식" onclick="alert('아직 안만듬')">
        </div>
    </div>
<%} else if (request.getAttribute("categoryName") == null) {
	response.sendError(404);
} %>
</body>
</html>