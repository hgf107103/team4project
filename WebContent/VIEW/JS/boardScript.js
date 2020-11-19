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
            url: `/callBoardServlet`,
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
            	}
            },
            error: () => {
                console.log('오류발생');
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
	
	let returnBoard = `<div class="board">` +
    `<input type="button" value="글 삭제" class="boardDeleteButton">` +
    `<table class="userBoardContent tableNomal"><tr><td rowspan="2" class="boardSmallTd">${boardObject.boardNumber}</td>` +
    `<td class="showNameTd">작성자 : ${boardObject.userName}</td><td class="showDateTd">${boardObject.boardDate}</td></tr>` + 
    `<tr><td class="showBoardTd" colspan="2">${boardObject.boardText}</td></tr>` + 
    `<table class="userBoardCommentTable tableNomal" id="comment${boardObject.boardNumber}"></table>${commentTableHtml}` + 
    `<table class="userBoardCommentWriteTable tableNomal"><tr><td class="writeCommentTd"><textarea class="commentTextarea" id="textarea${boardObject.boardNumber}" onkeyup="commentCountFunction(this, 150)"  rows="3" placeholder="댓글 입력"></textarea></td>` + 
    `<td class="submitCommentTd"><input type="button" class="commentWriteButton" onclick="newComment(${boardObject.boardNumber})" value="댓글쓰기"></td></tr>` + 
    `</div>`;
    return returnBoard;
}

function commentTableStringReturn(commentObject, boardNumber) {
	let returnComment = `<tr><td class="showCommentWriterTd">${commentObject.commentWriter}</td><td class="showCommentTd">${commentObject.commentText}</td><td class="commentDeleteTd" onclick="deleteComment(${commentObject.commentNumber}, ${boardNumber}, ${commentObject.userNumber})">삭제</td></tr>`
	return returnComment;
}

function callComment(number) {
	$(`#showCommentButtonTd${number}`).text('서버에 연결중입니다.');
	$.ajax({
        url: `/callCommentServlet`,
        type: "post",
        data: {boardNumber:number},
        cache: false,
        dataType: "json",
        success: (data) => {
        	$(`#showCommentButton${number}`).css('display', 'none');
        	console.log(data, " : ", data.boardNumber);
        	$(`#comment${number}`).html('');
        	$.each (data.comment, function (index, el) {
                console.log(index, " : ", el);
                $(`#comment${number}`).append(commentTableStringReturn(el, data.boardNumber));
            });
        },
        error: () => {
            console.log('오류발생');
        }
    });
}

function newComment(number) {
    if ( $(`#textarea${number}`).val() == '') {
        alert('입력된 내용이 없습니다.');
        return;
    }
    $.ajax({
        url: `/addCommentServlet`,
        type: "post",
        data: {
        	boardNumber:number,
        	commentText:$(`#textarea${number}`).val()
        	},
        cache: false,
        dataType: "json",
        success: (data) => {
        	if (data.check == "userStop") {
				alert(`정지된 사용자입니다.\n정지일 : ${data.dateString}까지`);
			}
        	
        	$(`#textarea${number}`).val('');
        	callComment(number);
        },
        error: () => {
            console.log('오류발생');
            alert('오류가 발생하였습니다.');
        }
    });
}

function deleteComment(cnumber, bnumber, unumber) {
	$.ajax({
        url: `/deleteCommentServlet`,
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
        		alert('삭제에 성공했습니다.');
        		callComment(data.boardNumber);
        		return;
        	}
        	if(data.check == "fail") {
        		alert('댓글 삭제에 실패했습니다.')
        		return;
        	}
        },
        error: () => {
            console.log('오류발생');
            alert('오류가 발생하였습니다.');
        }
    });
}

function newContents() {
    if ($('#newTextWrite').val() == '') {
        alert('입력된 내용이 없습니다.');
        return;
    }
    const boardTextreplace = $('#newTextWrite').val().replace(/(\r\n\t|\n|\r\t)/gm," ");

    $.ajax({
        url: `/addBoardServlet`,
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
        		console.log('정지 유저');
        		alert(`당신은 정지상태입니다.\n정지일 : ${data.dateString}까지`);
        	}
        	if(data.check === "false") {
        		console.log('글쓰기 실패');
        	}
    		window.location.reload();
        },
        error: (e) => {
        	console.log('오류발생', e);
        	alert('글쓰기 실패');
        }
    });
}



$(document).ready(() => {
	console.log('준비 이벤트')
	$.ajax({
        url: `/callBoardServlet`,
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
        },
        error: () => {
        	console.log('오류발생');
        	alert('글쓰기 실패');
        }
    });
	check = 1;
})