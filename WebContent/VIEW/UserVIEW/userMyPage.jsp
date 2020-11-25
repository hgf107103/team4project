<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="ko" oncontextmenu="return false" ondragstart="return false" onselectstart='return false'>
<head>
    <meta charset="UTF-8">
    <title><c:out value="${userLogin.userNickname}"></c:out>님의 마이페이지</title>
	<script src="/project/VIEW/JS/jquery-3.5.1.min.js"></script>
    <script src="/project/VIEW/JS/mypageScript.js"></script>
    <link rel="stylesheet" href="/project/VIEW/CSS/mypageStyle.css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="/project/favicon.ico" type="image/x-icon">
	<link rel="icon" href="/project/favicon.ico" type="image/x-icon">
</head>
<body>
<%	
	if(session.getAttribute("userLogin") == null) {
		response.sendError(404);
	}
%>
	<c:if test="${userLogin.adminUserCheck == 0}">
	<div id="smoke">
        <section id="mainSection">
            <div class="flexDiv" id="headFlex">
                <table>
                    <tr>
                        <td class="tableHead">이름</td>
                        <td>
                        	<c:out value="${userLogin.userName}"></c:out>
                        	<input type="hidden" id="userNameHidden" value="<c:out value="${userLogin.userNickname}"></c:out>">
                        </td>
                        <td class="tableHead">닉네임</td>
                        <td style="border-bottom: 1px solid rgb(200,200,200);"><c:out value="${userLogin.userNickname}"></c:out></td>
                    </tr>
                    <tr>
                        <td class="tableHead" colspan="2">전체게시글 수</td>
                        <td id="userBoardCount" colspan="2" style="border-bottom: 1px solid rgb(200,200,200);">0개</td>
                    </tr>
                    <tr>
                        <td class="tableHead" colspan="2">전체댓글 수</td>
                        <td id="userCommentCount" colspan="2">0개</td>
                    </tr>
                </table>
            </div>
            <div id="mainMenu">
                <table id="contentTable">
                    <tr>
                        <td class="tableMenu" id="contentMenu" onclick="location.href = '#contentMenu'; getBoardList();">내 게시물</td>
                        <td class="tableMenu" id="commentMenu" onclick="location.href = '#commentMenu'; getCommentList();">내 댓글</td>
                        <td class="tableMenu" onclick="messagePopupOpen()">쪽지</td>
                    </tr>
                </table>
				<div id="mainContentsDiv">
                	<table id="mainTable">
                    	<tr>
                    		<th class="number">번호</th>
                    		<th class="text">내용</th>
                    		<th class="control">작성일</th>
                    	</tr>
                	</table>
                	<table class="showTables" id="mainContentsTable">
                		<tr>
                			<td class="emptyTd">
                				메뉴를 선택해주세요
                			</td>
                		</tr>
                	</table>
                </div>
            </div>
        </section>
	</div>
	<div id="pageTitleText">마이페이지</div>
	</c:if>
</body>
</html>