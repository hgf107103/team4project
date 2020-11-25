function messagePopupOpen() {
    let message = window.open("userMessage.html#reception", "PopupWin", "width=600,height=601");
}

$(document).ready(() => {
	getUserInfo();
})


function getUserInfo() {
	$.ajax({
        url: `mypage/info`,
        type: "post",
        cache: false,
        dataType: "json",
        success: (data) => {
        	if(data.check == 'success') {
        		console.log('갱신 성공')
        		$('#userBoardCount').text(`${data.boardCount}개`);
        		$('#userCommentCount').text(`${data.commentCount}개`);
        		return;
        	}
        	if(data.check == 'notLogin') {
        		console.error('로그인 되어 있지 않음')
        		return;
        	}
        	if(data.check != 'success') {
        		console.error('정보 갱신 실패')
        		return;
        	}
        },
        error: () => {
            console.error('유저리스트 갱신 오류발생');
        }
    });
}


function getBoardList() {
	$.ajax({
        url: `mypage/board`,
        type: "post",
        cache: false,
        dataType: "json",
        success: (data) => {
        	console.log(data)
        	if(data.check == 'success') {
        		console.log('게시물 갱신 성공')
        		$(`#mainContentsTable`).html('');
        		
        		if(data.board.length <= 0) {
        			$(`#mainContentsTable`).html(returnEmptyString());
        			return;
        		}
        		
        		$.each (data.board, function (index, el) {
                    $(`#mainContentsTable`).append(returnBoardString(el));
                });
        		return;
        	}
        	if(data.check != 'success') {
        		console.error('게시물 갱신 실패')
        		return;
        	}
        },
        error: () => {
            console.error('게시물리스트 갱신 오류발생');
        }
    });
}


function getCommentList() {
	$.ajax({
        url: `mypage/comment`,
        type: "post",
        cache: false,
        dataType: "json",
        success: (data) => {
        	console.log(data)
        	if(data.check == 'success') {
        		console.log('게시물 갱신 성공')
        		$(`#mainContentsTable`).html('');
        		if(data.comment.length <= 0) {
        			$(`#mainContentsTable`).html(returnEmptyString());
        			return;
        		}
        		
        		$.each (data.comment, function (index, el) {
                    $(`#mainContentsTable`).append(returnCommentString(el));
                });
        		return;
        	}
        	if(data.check != 'success') {
        		console.error('게시물 갱신 실패')
        		return;
        	}
        },
        error: () => {
            console.error('게시물리스트 갱신 오류발생');
        }
    });
}


function returnBoardString(boardObject) {
	let str = `<tr><td class="number">${boardObject.boardNumber}</td>` + 
	`<td class="text"><div class="boardTextDivScroll">${boardObject.boardText}</div></td>` + 
	`<td class="date">${boardObject.boardDate}</td></tr>`;
	
	return str;
}

function returnCommentString(commentObject) {
	let str = `<tr><td class="number">${commentObject.commentNumber}</td>` + 
	`<td class="text"><div class="commentTextDivScroll">${commentObject.commentText}</div></td>` + 
	`<td class="date">${commentObject.commentDate}</td></tr>`;
	
	return str;
}


function returnEmptyString() {
	let str = `<tr><td id="emptyTd">${$('#userNameHidden').val()}님은 아직 게시물을 작성하지 않으셨습니다.</td></tr>`;
	return str;
}