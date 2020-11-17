<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="ko" oncontextmenu="return false" ondragstart="return false" onselectstart='return false'>
<head>
    <meta charset="UTF-8">
    <title>메인화면</title>
    <script src="VIEW/JS/jquery-3.5.1.min.js"></script>
    <script src="VIEW/JS/indexAnimation.js"></script>
    <link rel="stylesheet" href="VIEW/CSS/indexStyle.css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>
<svg version="1.1" xmlns="http://www.w3.org/2000/svg">
    <filter id="blur">
        <feGaussianBlur stdDeviation="3" />
    </filter>
</svg>
<body class="bgBlur">
    <div id="mainDiv">
    </div>
    <div class="fiexdDiv callBlur" id="menubar">
        <input type="button" id="menuOpen" value="메뉴" onclick="menuOpenFunction()">
        <input type="button" id="menuLOL" value="리그오브레전드" onclick="window.open('LOLcontents.html', '_blank')">
        <input type="button" id="menuBG" value="배틀그라운드" onclick="alert('여긴 안만듬')">
        <input type="button" id="menuOW" value="오버워치" onclick="alert('여긴 안만듬')">
    </div>
    <div class="fiexdDiv callBlur" id="titleMove">
        <p>샘</p>
        <p>플</p>
        <p>타</p>
        <p>이</p>
        <p>틀</p>
    </div>
    <div class="fiexdDiv callBlur" id="loginDiv">
        <input type="text" id="idInput" autocomplete="off" onkeyup="if (window.event.keyCode == 13) {loginFunction()}" placeholder="ID">
        <input type="password" id="pwdInput" onkeyup="if (window.event.keyCode == 13) {loginFunction()}" placeholder="PASSWORD">
        <img src="VIEW/img/icon/login.png" width="2.5%" alt="" onclick="loginFunction()">
        <input type="button" id="signup" onclick="showSignup(); location.href='#signupDiv'" onmouseenter="show('signup','rgb(192, 255, 132)', '회원가입')" onmouseout="out('signup','rgb(192, 255, 132)', 'NEW')" value="NEW">
    </div>
    <% if (session.getAttribute("userLogin") == null) {%>
    <div id="signupDiv">
        <img id="exit" src="VIEW/img/icon/out.png" width="45px" onclick="location.href='#menuCloss'; signupExit();" alt="">
        <img id="logo" src="VIEW/img/boardIcon/lolboard.png" alt="">
        <div id="signupFormBack"></div>
        <form id="signupForm">
        <label><span class="labelSet">　아이디</span><input type="text" id="signupIdInput" class="inputTextStyle" onkeyup="if (window.event.keyCode == 13) {idCheck()}" autocomplete="off"></label>
            <input type="button" class="signupButtonStyle" onclick="idCheck()" value="중복확인">
            <p class="signupLog" id="signupIdLog">아이디 중복확인을 해주십시오</p>
            <legend id="moreForm">
                <label><span class="labelSet">비밀번호</span><input type="password" id="signupPwd" style="margin-left: 10px;" onkeyup="pwdCheck()" class="inputTextStyle"></label>
                <br>
                <label><span class="labelSet">　　확인</span><input type="password" id="signupPwdCheck" onkeyup="pwdCheck()" class="inputTextStyle"></label>
                <p class="signupLog" id="signupPwdLog">비밀번호를 한번 더 입력하십시오</p>
            </legend>
            <legend id="optionForm">
                <label><span class="labelSet">　　이름</span><input type="text" id="signupName" class="inputTextStyle" onkeyup="nameCheck()" autocomplete="off"></label>
                <p class="signupLog" id="signupNameLog">이름을 입력하십시오</p>
                <br>
                <label><span class="labelSet">　닉네임</span><input type="text" id="signupNickName" class="inputTextStyle" onkeyup="nicknameCheck()" autocomplete="off"></label>
                <p class="signupLog" id="signupNickNameLog">닉네임을 입력하십시오</p>
                <br>
                <input type="button" id="submitButton" class="signupButtonStyle" onclick="signupSubmit()" value="회원가입">
            </legend>
        </form>
    </div>
    <%} else if (session.getAttribute("userLogin") != null) {%>
    <div class="fiexdDiv" id="loginDiv">
        <input type="button" id="mypage" value="MY" onmouseenter="show('mypage','rgb(255, 120, 120)', '마이페이지')" onmouseout="out('mypage','rgb(255, 120, 120)', 'MY')" onclick="window.open('mypage.html#contentMenu', '_blank')">
        <input type="button" id="logout" style="margin-right: 0px;" value="OUT"onmouseenter="show('logout', 'rgb(183, 145, 255)', '로그아웃')" onmouseout="out('logout', 'rgb(183, 145, 255)', 'OUT')" onclick="location.href='index.html'">
    </div>
    <%} %>
</body>
</html>