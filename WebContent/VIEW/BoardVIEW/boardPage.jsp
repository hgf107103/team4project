<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="ko" oncontextmenu="return false" ondragstart="return false" onselectstart='return false'>
<head>
<title><c:out value="${categoryALLName}"></c:out> 게시판</title>
	<script src="/project/VIEW/JS/jquery-3.5.1.min.js"></script>
    <script src="/project/VIEW/JS/boardScript.js"></script>
    <link rel="stylesheet" href="/project/VIEW/CSS/boardStyle.css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="/project/favicon.ico" type="image/x-icon">
	<link rel="icon" href="/project/favicon.ico" type="image/x-icon">
</head>
<body>
	<input type="hidden" id="categoryName" value="<c:out value="${categoryName}"></c:out>">
	<input type="hidden" id="boardNumber" value="0">
	<header class="flexBox">
        <img src="/project/VIEW/img/boardIcon/board<c:out value="${categoryName}"></c:out>.png" onclick="location.href = '/project/'" alt="" title="누르면 홈페이지로 이동">
        <h1><c:out value="${categoryALLName}"></c:out> 유저 대화방</h1>
        <img src="/project/VIEW/img/boardIcon/board<c:out value="${categoryName}"></c:out>.png" onclick="location.href = '/project/'" alt="" title="누르면 홈페이지로 이동">
    </header>
    <div id="categoryMoveMenu">
    	<span>게시판 이동</span>
    	<input type="button" id="menuLOL" value="리그오브레전드" onclick="location.href = 'board?categoryName=LOL'">
        <input type="button" id="menuBG" value="배틀그라운드" onclick="location.href = 'board?categoryName=BG'">
        <input type="button" id="menuOW" value="오버워치" onclick="location.href = 'board?categoryName=OW'">
    </div>
    <section id="boardMainSection">
    	<%if(session.getAttribute("userLogin") != null) {%>
    	
        <table id="newContenWrite">
            <tr>
                <td class="menu write">새 글쓰기</td>
                <td class="menu"><c:out value="${userLogin.userNickname}"></c:out></td>
                <td class="emptySpace" style="border-top: 1px solid rgb(240,240,240);"></td>
                <td id="counter" class="emptySpace" style="border-top: 1px solid rgb(240,240,240);">0/2000</td>
            </tr>
            <tr>
                <td colspan="4">
                    <textarea class="textSpace" onkeyup="countFunction(2000)" id="newTextWrite" placeholder="게시글 입력" rows="6"></textarea>
                </td>
            </tr>
                <td class="emptySpace" colspan="3" style="border-bottom: 1px solid rgb(240,240,240);"></td>
                <td class="menu buttonTd" onclick="newContents()">새글쓰기</td>
        </table>
        <%}%>
        <div id="allBoard">
        </div>
    </section>
</body>
</html>