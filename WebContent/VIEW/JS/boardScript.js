function countFunction(CONTENT_MAX_SIZE) {
    if ($('#newTextWrite').val().length <= CONTENT_MAX_SIZE) {
        $('#counter').text(`${$('#newTextWrite').val().length}/${CONTENT_MAX_SIZE}`)
        return;
    }
    let str = $('#newTextWrite').val();
    let str2 = str.substr(0,CONTENT_MAX_SIZE);
    $('#newTextWrite').val(str2);
    countFunction();
}

function commentCountFunction(dom, CONTENT_MAX_SIZE) {
    if ($(dom).val().length <= CONTENT_MAX_SIZE) {
        return;
    }
    alert(`댓글은 ${CONTENT_MAX_SIZE}자까지만 적을 수 있습니다.`);
    let str = $(dom).val();
    let str2 = str.substr(0,CONTENT_MAX_SIZE);
    $(dom).val(str2);
}

let check = 0;

$(window).scroll(() => {
    //let board = null;
	if(check <= 0) {
		return;
	}
    if ($(window).scrollTop() + $(window).outerHeight() >= $(window).height()) {
        console.log('스크롤 이벤트')
        $.ajax({
            url: `board/call`,
            type: "post",
            data: {categoryName:$('#categoryName').val(),
            		boardNumber:$('#boardNumber').val()},
            cache: false,
            dataType: "json",
            success: (data) => {
            	$('#boardNumber').val(data.lastNumber);
            	$.each (data.board, function (index, el) {
                    $('#allBoard').append(boardTableStringReturn(el));
                });
            	if(data.sessionID == "null") {
            		$('.userBoardCommentWriteTable').css('display', 'none');
            	}
            	
            	if(data.board.length < 5) {
            		check = -10;
                    $('#boardMainSection').append(`<footer>모든 게시물을 불러왔습니다.</footer>`);
            	}
            },
            error: () => {
                console.log('글 갱신 오류발생');
            }
        });
        //
    }
});


function boardTableStringReturn(boardObject) {
	let commentTableHtml = "";
	if (boardObject.commentCount > 0) {
		commentTableHtml = `<table class="showCommentButtonTable" id="showCommentButton${boardObject.boardNumber}"><tr><td id="showCommentButtonTd${boardObject.boardNumber}" onclick="callComment(${boardObject.boardNumber})">댓글보기(댓글 수 : ${boardObject.commentCount})</td></tr></table>`
	}
	
	let returnBoard = `<div class="board" id="boardID${boardObject.boardNumber}">` +
    `<input type="button" value="글 삭제" class="boardDeleteButton" id="boardDeleteButton${boardObject.boardNumber}" onclick="deleteBoard(${boardObject.boardNumber}, ${boardObject.userNumber})">` +
    `<table class="userBoardContent tableNomal" id="userBoardContent${boardObject.boardNumber}"><tr><td rowspan="2" class="boardSmallTd">${boardObject.boardNumber}</td>` +
    `<td class="showNameTd">작성자 : ${boardObject.userName}</td><td class="showDateTd">${boardObject.boardDate}</td></tr>` + 
    `<tr><td class="showBoardTd" colspan="2">${boardObject.boardText}</td></tr></table>` + 
    `<table class="userBoardCommentTable tableNomal" id="comment${boardObject.boardNumber}"></table>${commentTableHtml}` + 
    `<table class="userBoardCommentWriteTable tableNomal" id="commentWriter${boardObject.boardNumber}"><tr><td class="writeCommentTd"><textarea class="commentTextarea" id="textarea${boardObject.boardNumber}" onkeyup="commentCountFunction(this, 150)"  rows="3" placeholder="댓글 입력"></textarea></td>` + 
    `<td class="submitCommentTd"><input type="button" class="commentWriteButton" onclick="newComment(${boardObject.boardNumber})" value="댓글쓰기"></td></tr>` + 
    `</div>`;
    return returnBoard;
}

function commentTableStringReturn(commentObject, boardNumber) {
	let returnComment = `<tr><td class="showCommentWriterTd">${commentObject.commentWriter}</td><td class="showCommentTd">${commentObject.commentText}</td><td class="commentDeleteTd" onclick="deleteComment(${commentObject.commentNumber}, ${boardNumber}, ${commentObject.userNumber})">삭제</td></tr>`
	return returnComment;
}

//댓글 갱신
function callComment(number) {
	$(`#showCommentButtonTd${number}`).text('서버에 연결중입니다.');
	$.ajax({
        url: `board/comment/call`,
        type: "post",
        data: {boardNumber:number},
        cache: false,
        dataType: "json",
        success: (data) => {
        	$(`#showCommentButton${number}`).css('display', 'none');
        	$(`#comment${number}`).html('');
        	$.each (data.comment, function (index, el) {
                $(`#comment${number}`).append(commentTableStringReturn(el, data.boardNumber));
            });
        },
        error: () => {
            console.log('댓글 갱신 오류발생');
        }
    });
}

//새 댓글
function newComment(number) {
    if ( $(`#textarea${number}`).val() == '') {
        alert('입력된 내용이 없습니다.');
        return;
    }
    const boardTextreplace = $(`#textarea${number}`).val().replace(/(\r\n\t|\n|\r\t)/gm," ");
    const boardText = boardTextreplace.replace(/(<|>|$)/gm," ");
    $.ajax({
        url: `board/comment/add`,
        type: "post",
        data: {
        	boardNumber:number,
        	commentText:boardText
        	},
        cache: false,
        dataType: "json",
        success: (data) => {
        	if (data.check == "userStop") {
				alert(`제한된 사용자입니다.\n정지일 : ${data.dateString}까지`);
			}
        	
        	$(`#textarea${number}`).val('');
        	callComment(number);
        },
        error: () => {
            console.log('새 댓글 오류발생');
            alert('오류가 발생하였습니다.');
        }
    });
}

//댓글 삭제
function deleteComment(cnumber, bnumber, unumber) {
	let userCheck = confirm(`정말로 ${bnumber}번 게시물의 댓글을 삭제하시겠습니까?\n주의 : 되돌릴 수 없습니다.`);
	
	if (!userCheck) {
		alert('취소되었습니다.');
		return;
	}
	
	$.ajax({
        url: `board/comment/delete`,
        type: "post",
        data: {
        	boardNumber:bnumber,
        	commentNumber:cnumber,
        	userNumber:unumber
        	},
        cache: false,
        dataType: "json",
        success: (data) => {
        	if(data.check == "notLogin") {
        		alert('삭제 권한이 없습니다.\n비로그인 사용자입니다.');
        		console.log(data.boardNumber);
        		console.log();
        		return;
        	}
        	if(data.check == "notYours") {
        		alert('삭제 권한이 없습니다.\n자신의 댓글이 아닙니다.');
        		console.log(data.boardNumber);
        		return;
        	}
        	if(data.check == "success") {
        		alert('댓글을 삭제했습니다.');
        		callComment(data.boardNumber);
        		return;
        	}
        	if(data.check == "fail") {
        		alert('댓글 삭제에 실패했습니다.')
        		return;
        	}
        },
        error: () => {
            console.error('댓글삭제 오류발생');
            alert('오류가 발생하였습니다.');
        }
    });
}

//게시물 삭제
function deleteBoard(bnumber, unumber) {
	let userCheck = confirm(`정말로 ${bnumber}번 게시물을 삭제하시겠습니까?\n주의 : 되돌릴 수 없으며, 댓글도 모두 삭제됩니다.`);
	
	if (!userCheck) {
		alert('취소되었습니다.');
		return;
	}
	
	$.ajax({
        url: `board/delete`,
        type: "post",
        data: {
        	boardNumber:bnumber,
        	userNumber:unumber
        	},
        cache: false,
        dataType: "json",
        success: (data) => {
        	if(data.check == "notLogin") {
        		alert('삭제 권한이 없습니다.\n비로그인 사용자입니다.');
        		return;
        	}
        	if(data.check == "notYours") {
        		alert('삭제 권한이 없습니다.\n자신의 댓글이 아닙니다.');
        		return;
        	}
        	if(data.check == "success") {
        		alert('게시글을 삭제했습니다.');
        		$(`#boardID${data.boardNumber}`).html('');
        		$(`#boardID${data.boardNumber}`).css('display', 'none');
        		return;
        	}
        	if(data.check == "fail") {
        		alert('댓글 삭제에 실패했습니다.')
        		return;
        	}
        	if(data.check == "commentDeleteFail") {
        		alert('댓글이 존재해 글을 삭제할 수 없습니다.')
        		return;
        	}
        },
        error: () => {
            console.error('게시글 삭제 오류발생');
            alert('오류가 발생하였습니다.');
        }
    });
}

//새글쓰기
function newContents() {
    if ($('#newTextWrite').val() == '') {
        alert('입력된 내용이 없습니다.');
        return;
    }
    const textreplace = $('#newTextWrite').val().replace(/(\r\n\t|\n|\r\t)/gm," ");
    const boardTextreplace = textreplace.replace(/(<|>|$)/gm," ");
    $.ajax({
        url: `board/add`,
        type: "post",
        data: {categoryName:$('#categoryName').val(),
        		boardText:boardTextreplace},
        cache: false,
        dataType: "json",
        success: (data) => {
        	if(data.check === "success") {
        		console.log('글쓰기 성공');
        	}
        	if(data.check === "userStop") {
        		console.error('제제 유저');
        		alert(`제한된 사용자입니다.\n정지일 : ${data.dateString}까지`);
        	}
        	if(data.check === "false") {
        		console.error('글쓰기 실패');
        		alert(`글 쓰기에 실패하였습니다.`);
        	}
        	if(data.check === "overCount") {
        		console.error('글쓰기 실패');
        		alert(`글쓰기 성공\n쓴 글이 30개를 초과하여 이전에 작성한 글이 삭제되었습니다.`);
        	}
    		window.location.reload();
        },
        error: () => {
        	console.error('글쓰기 오류발생');
        	alert('글 쓰기에 오류가 발생하였습니다.');
        }
    });
}


//도큐먼트 준비 후 실행함수
$(document).ready(() => {
	console.log('준비 이벤트')
	$.ajax({
            url: `board/call`,
            type: "post",
            data: {categoryName:$('#categoryName').val(),
            		boardNumber:$('#boardNumber').val()},
            cache: false,
            dataType: "json",
            success: (data) => {
            	$('#boardNumber').val(data.lastNumber);
            	$.each (data.board, function (index, el) {
                    $('#allBoard').append(boardTableStringReturn(el));
                });
            	if(data.sessionID == "null") {
            		$('.userBoardCommentWriteTable').css('display', 'none');
            	}
            	
            	if(data.board.length < 5) {
            		check = -10;
                    $('#boardMainSection').append(`<footer>모든 게시물을 불러왔습니다.</footer>`);
            	}
            },
            error: () => {
                console.log('글 갱신 오류발생');
            }
        });
	
	check = 1;
})