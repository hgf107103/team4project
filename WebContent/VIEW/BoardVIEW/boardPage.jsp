<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="ko" oncontextmenu="return false" ondragstart="return false" onselectstart='return false'>
<head>
<title><c:out value="${categoryALLName}"></c:out>보드</title>
	<script src="/VIEW/JS/jquery-3.5.1.min.js"></script>
    <script src="/VIEW/JS/boardScript.js"></script>
    <link rel="stylesheet" href="/VIEW/CSS/boardStyle.css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>
<body>
	<input type="hidden" id="categoryName" value="<c:out value="${categoryName}"></c:out>">
	<input type="hidden" id="boardNumber" value="0">
	<header class="flexBox">
        <img src="/VIEW/img/boardIcon/board<c:out value="${categoryName}"></c:out>.png" onclick="window.close()" alt="" title="누르면 홈페이지로 이동">
        <h1><c:out value="${categoryALLName}"></c:out> 유저 대화방</h1>
        <img src="/VIEW/img/boardIcon/board<c:out value="${categoryName}"></c:out>.png" onclick="window.close()" alt="" title="누르면 홈페이지로 이동">
    </header>
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