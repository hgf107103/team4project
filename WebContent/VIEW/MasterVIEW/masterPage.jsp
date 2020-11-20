<%@page import="object.userVO"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="ko" oncontextmenu="return false" ondragstart="return false" onselectstart='return false'>
<head>
    <meta charset="UTF-8">
    <title>메인화면</title>
    <script src="/project/VIEW/JS/jquery-3.5.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/project/VIEW/CSS/masterPage.css">
</head>
<body>
<%	
	/*if(session.getAttribute("userLogin") == null) {
		response.sendError(403);
		return;
	}
	System.out.println(session.getAttribute("userLogin"));
	if(session.getAttribute("userLogin") != null) {
		userVO userTemp = (userVO)(session.getAttribute("userLogin"));
		System.out.println(userTemp.toString());
		if(userTemp.getUserID() == "admin" || userTemp.getUserNumber() == 1) {*/%>
    <div id="userListDiv">
        <table id="userTableHeader">
            <tr>
                <th class="userId">아이디</th>
                <th class="userName">이름</th>
                <th class="userNickname">닉네임</th>
            </tr>
        </table>
        <div id="userList">
            <table id="userTableList">
                
            </table>
        </div>
    </div>
    <div id="userContentDiv">
        <div id="userControlDiv">
            <div id="userStatus">
            	<label>선택된 유저</label><input id="selecteUserName" type="text" readonly class="inputDefaultStyle" draggable="false" value="" placeholder="유저를 선택하시오">
            	<label>남은 정지 일수</label><input type="text" readonly id="userStopDay" class="inputDefaultStyle" draggable="false" value="" placeholder="일">
            	<label>현재 계정 상태</label><input type="text" readonly id="userOut" class="inputDefaultStyle" draggable="false" value="" placeholder="활성/비활성">
            </div>
            <div id="userDirectControl">
            	
            	<select name="" id="">
                	<option value="0">정지일수</option>
                	<option value="1">1일</option>
                	<option value="3">3일</option>
                	<option value="7">7일</option>
                	<option value="14">14일</option>
                	<option value="30">30일</option>
                	<option value="365">365일</option>
            	</select>
            	<input type="button" class="inputDefaultStyle" value="제제일추가" onclick="alert('아직 구현되지 않음')">
            	<input type="button" class="inputDefaultStyle" value="제제일삭감" onclick="alert('아직 구현되지 않음')">
            	<input type="button" class="inputDefaultStyle" value="영구정지" onclick="alert('아직 구현되지 않음')">
            	<input type="button" class="inputDefaultStyle" value="영구정지해제" onclick="alert('아직 구현되지 않음')">
            </div>
        </div>
        <div id="userBoardListDiv">
            <table id="selectedUserBoardHeader">
                <tr>
                    <th class="boardNumber">글번호</th>
                    <th class="boardContents">글내용</th>
                    <th class="boardDate">날짜</th>
                    <th class="boardControl">삭제</th>
                </tr>
            </table>
            <div id="borderListScroll">
                <table id="selectedUserBoardList">
                    
                </table>
            </div>
        </div>
        <div id="userCommentListDiv">
            <table id="selectedUserCommentHeader">
                <tr>
                    <th class="commentContents">댓글내용</th>
                    <th class="commentDate">날짜</th>
                    <th class="commentControl">삭제</th>
                </tr>
            </table>
            <div id="commentListScroll">
                <table id="selectedUserCommentList">
                    
                </table>
            </div>
        </div>
    </div>
<%//}}%>
</body>
</html>