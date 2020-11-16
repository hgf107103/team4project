<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="ko" oncontextmenu="return false" ondragstart="return false" onselectstart='return false'>
<head>
    <meta charset="UTF-8">
    <title> 콘텐츠 선택</title>
    <script src="../JS/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="../CSS/contentStyle.css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>
<svg version="1.1" xmlns="http://www.w3.org/2000/svg">
    <filter id="blur">
        <feGaussianBlur stdDeviation="3" />
    </filter>
</svg>
<body class="bgBlur">
    <div id="smoke">
        <div id="mainContentsDiv">
            <input type="button" id="boardBtn" value="대화방" onclick="window.open('')">
            <input type="button" id="playLogBtn" value="전적검색" onclick="alert('아직 안만듬')">
            <input type="button" id="newsBtn" value="게임소식" onclick="alert('아직 안만듬')">
        </div>
    </div>
</body>
</html>