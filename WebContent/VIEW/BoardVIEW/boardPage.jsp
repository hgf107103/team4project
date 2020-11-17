<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="ko" oncontextmenu="return false" ondragstart="return false" onselectstart='return false'>
<head>
<title><c:out value="${param.boardCategoryName}"></c:out>보드</title>
	<script src="../JS/jquery-3.5.1.min.js"></script>
    <script src="../JS/boardScript.js"></script>
    <link rel="stylesheet" href="../CSS/boardStyle.css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>
<body>
	<header class="flexBox">
        <img src="../img/boardIcon/lolboard.png" onclick="location.href = 'login.html'" alt="귀여운포로" title="누르면 홈페이지로 이동">
        <h1>리그오브레전드 유저 대화방</h1>
        <img src="../img/boardIcon/lolboard.png" onclick="location.href = 'login.html'" alt="귀여운포로" title="누르면 홈페이지로 이동">
    </header>
    <section id="boardMainSection">
        <table id="newContenWrite">
            <tr>
                <td class="menu write">새 글쓰기</td>
                <td class="menu">홍길동</td>
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
        <div id="allBoard">
            <div class="board">
                <input type="button" value="글 삭제" class="boardDeleteButton">
                <table class="userBoardContent tableNomal">
                    <tr>
                        <td rowspan="2" class="boardSmallTd">1</td>
                        <td class="showNameTd">작성자 : 홍길동</td>
                        <td class="showDateTd">2020 11 10</td>
                    </tr>
                    <tr>
                        <td class="showBoardTd" colspan="2">여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.</td>
                    </tr>
                </table>
                <table class="userBoardCommentTable tableNomal" id="comment1">
                    <tr>
                        <td class="showCommentWriterTd">댓작성자</td>
                        <td class="showCommentTd">여기는 댓글을 봅니다</td>
                        <td class="commentDeleteTd">삭제</td>
                    </tr>
                </table>
                <table class="userBoardCommentWriteTable tableNomal">
                    <tr>
                        <td class="writeCommentTd">
                            <textarea class="commentTextarea" id="textarea1" onkeyup="commentCountFunction(this, 150)"  rows="3" placeholder="댓글 입력"></textarea>
                        </td>
                        <td class="submitCommentTd">
                            <input type="button" class="commentWriteButton" onclick="newComment('textarea1','comment1')" value="댓글쓰기">
                        </td>
                    </tr>
                </table>
            </div>
            <div class="board">
                <input type="button" value="글 삭제" class="boardDeleteButton">
                <table class="userBoardContent tableNomal">
                    <tr>
                        <td rowspan="2" class="boardSmallTd">2</td>
                        <td class="showNameTd">작성자 : 홍길동</td>
                        <td class="showDateTd">2020 11 10</td>
                    </tr>
                    <tr>
                        <td class="showBoardTd" colspan="2">여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.여기는 본문입니다.</td>
                    </tr>
                </table>
                <table class="userBoardCommentTable tableNomal" id="comment2">
                    <tr>
                        <td class="showCommentWriterTd">댓작성자</td>
                        <td class="showCommentTd">여기는 댓글을 봅니다</td>
                        <td class="commentDeleteTd">삭제</td>
                    </tr>
                </table>
                <table class="userBoardCommentWriteTable tableNomal">
                    <tr>
                        <td class="writeCommentTd">
                            <textarea class="commentTextarea" id="textarea2" onkeyup="commentCountFunction(this, 150)"  rows="3" placeholder="댓글 입력"></textarea>
                        </td>
                        <td class="submitCommentTd">
                            <input type="button" class="commentWriteButton" onclick="newComment('textarea2','comment2')" value="댓글쓰기">
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </section>
</body>
</html>