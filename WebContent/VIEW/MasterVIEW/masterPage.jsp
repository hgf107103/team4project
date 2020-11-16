<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="ko" oncontextmenu="return false" ondragstart="return false" onselectstart='return false'>
<head>
    <meta charset="UTF-8">
    <title>메인화면</title>
    <script src="JS/jquery-3.5.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="CSS/masterPage.css">
    <script>
        $(document).ready(() => {
            $('.boardDeleteButton').click(() => {
                alert('아직 구현되지 않음');
            });
        });
    </script>
</head>
<body>
    <div id="userListDiv">
        <table id="userTableHeader">
            <tr>
                <th class="userId">아이디</th>
                <th class="userName">이름</th>
                <th>닉네임</th>
            </tr>
        </table>
        <div id="userList">
            <table id="userTableList">
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('홍길동')">
                    <td class="userId">abc123</td>
                    <td class="userName">홍길동</td>
                    <td class="userNick">별명</td>
                </tr>
                <tr onclick="$('#selecteUserName').val('김길동')">
                    <td class="userId">zxc123</td>
                    <td class="userName">김길동</td>
                    <td class="userNick">안녕</td>
                </tr>
            </table>
        </div>
    </div>
    <div id="userContentDiv">
        <div id="userControlDiv">
            <label>선택된 유저</label><input id="selecteUserName" type="text" readonly class="inputDefaultStyle" draggable="false" value="홍길동">
            <label>남은 정지 일수</label><input type="text" readonly id="userStopDay" class="inputDefaultStyle" draggable="false" value="0">
            <select name="" id="">
                <option value="null">정지일수</option>
                <option value="1">1일</option>
                <option value="3">3일</option>
                <option value="7">7일</option>
                <option value="14">14일</option>
                <option value="30">30일</option>
                <option value="365">365일</option>
            </select>
            <input type="button" class="inputDefaultStyle" value="정지추가" onclick="alert('아직 구현되지 않음')">
            <input type="button" class="inputDefaultStyle" value="강제탈퇴" onclick="alert('아직 구현되지 않음')">
        </div>
        <div id="userBoardListDiv">
            <table id="selectedUserBoardHeader">
                <tr>
                    <th class="boardNumber">글번호</th>
                    <th class="boardContents">내용</th>
                    <th class="boardDate">날짜</th>
                    <th class="boardControl">삭제</th>
                </tr>
            </table>
            <div id="borderListScroll">
                <table id="selectedUserBoardList">
                    <tr>
                        <td class="boardNumber">1</td>
                        <td class="boardContents">
                            <div class="boardContesScroll">
                                안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕
                            </div>
                        </td>
                        <td class="boardDate">2020 11 14</td>
                        <td class="boardControl">
                            <input type="button" class="boardDeleteButton" value="삭제">
                        </td>
                    </tr> 
                    <tr>
                        <td class="boardNumber">2</td>
                        <td class="boardContents">
                            <div class="boardContesScroll">
                                샘플 문구 입니다.
                            </div>
                        </td>
                        <td class="boardDate">2020 11 14</td>
                        <td class="boardControl">
                            <input type="button" class="boardDeleteButton" value="삭제">
                        </td>
                    </tr> 
                    <tr>
                        <td class="boardNumber">3</td>
                        <td class="boardContents">
                            <div class="boardContesScroll">
                                샘플 문구 입니다.
                            </div>
                        </td>
                        <td class="boardDate">2020 11 14</td>
                        <td class="boardControl">
                            <input type="button" class="boardDeleteButton" value="삭제">
                        </td>
                    </tr> 
                    <tr>
                        <td class="boardNumber">4</td>
                        <td class="boardContents">
                            <div class="boardContesScroll">
                                샘플 문구 입니다.
                            </div>
                        </td>
                        <td class="boardDate">2020 11 14</td>
                        <td class="boardControl">
                            <input type="button" class="boardDeleteButton" value="삭제">
                        </td>
                    </tr> 
                    <tr>
                        <td class="boardNumber">5</td>
                        <td class="boardContents">
                            <div class="boardContesScroll">
                                샘플 문구 입니다.
                            </div>
                        </td>
                        <td class="boardDate">2020 11 14</td>
                        <td class="boardControl">
                            <input type="button" class="boardDeleteButton" value="삭제">
                        </td>
                    </tr> 
                    <tr>
                        <td class="boardNumber">6</td>
                        <td class="boardContents">
                            <div class="boardContesScroll">
                                샘플 문구 입니다.
                            </div>
                        </td>
                        <td class="boardDate">2020 11 14</td>
                        <td class="boardControl">
                            <input type="button" class="boardDeleteButton" value="삭제">
                        </td>
                    </tr> 
                </table>
            </div>
        </div>
        <div id="userCommentListDiv">
            <table id="selectedUserCommentHeader">
                <tr>
                    <th class="commentContents">내용</th>
                    <th class="commentDate">날짜</th>
                    <th class="commentControl">삭제</th>
                </tr>
            </table>
            <div id="commentListScroll">
                <table id="selectedUserCommentList">
                    <tr>
                        <td class="commentContents">
                            <div class="boardContesScroll">
                                샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 샘플 댓글입니다. 
                            </div>
                        </td>
                        <td class="commentDate">2020 11 14</td>
                        <td class="commentControl">
                            <input type="button" class="commentDeleteButton" value="삭제">
                        </td>
                    </tr>
                    <tr>
                        <td class="commentContents">댓글 내용입니다</td>
                        <td class="commentDate">2020 11 14</td>
                        <td class="commentControl">
                            <input type="button" class="commentDeleteButton" value="삭제">
                        </td>
                    </tr>
                    <tr>
                        <td class="commentContents">댓글 내용입니다</td>
                        <td class="commentDate">2020 11 14</td>
                        <td class="commentControl">
                            <input type="button" class="commentDeleteButton" value="삭제">
                        </td>
                    </tr>
                    <tr>
                        <td class="commentContents">댓글 내용입니다</td>
                        <td class="commentDate">2020 11 14</td>
                        <td class="commentControl">
                            <input type="button" class="commentDeleteButton" value="삭제">
                        </td>
                    </tr>
                    <tr>
                        <td class="commentContents">댓글 내용입니다</td>
                        <td class="commentDate">2020 11 14</td>
                        <td class="commentControl">
                            <input type="button" class="commentDeleteButton" value="삭제">
                        </td>
                    </tr>
                    <tr>
                        <td class="commentContents">댓글 내용입니다</td>
                        <td class="commentDate">2020 11 14</td>
                        <td class="commentControl">
                            <input type="button" class="commentDeleteButton" value="삭제">
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</body>
</html>