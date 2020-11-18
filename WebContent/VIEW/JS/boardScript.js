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


let boardCount = 0;

$(window).scroll(() => {
    //let board = null;
    if ($(window).scrollTop() + $(window).outerHeight() >= $(window).height()) {
        
        $.ajax({
            url: "JS/sampleBoard.json",
            cache: false,
            dataType: "json",
            success: (data) => {
                $.each (data.board, function (index, el) {
                    console.log(index);
                    $('#allBoard').append(boardTableStringReturn(el));
                    boardCount++;
                });
            },
            error: () => {
                console.log('오류발생');
            }
        });
        //
    }
});


function boardTableStringReturn(boardObject) {
    let returnBoard = `<div class="board">` +
    `<input type="button" value="글 삭제" class="boardDeleteButton">` +
    `<table class="userBoardContent tableNomal"><tr><td rowspan="2" class="boardSmallTd">${boardObject.idx}</td>` +
    `<td class="showNameTd">작성자 : ${boardObject.writer}</td><td class="showDateTd">${boardObject.date}</td></tr>` + 
    `<tr><td class="showBoardTd" colspan="2">${boardObject.contents}</td></tr>` + 
    `<table class="userBoardCommentTable tableNomal" id="comment${boardCount}"></table>` + 
    `<table class="userBoardCommentWriteTable tableNomal"><tr><td class="writeCommentTd"><textarea class="commentTextarea" id="textarea${boardCount}" onkeyup="commentCountFunction(this, 150)"  rows="3" placeholder="댓글 입력"></textarea></td>` + 
    `<td class="submitCommentTd"><input type="button" class="commentWriteButton" onclick="newComment('textarea${boardCount}','comment${boardCount}')" value="댓글쓰기"></td></tr>` + 
    `</div>`;
    return returnBoard;
}

function newComment(myID, targetID) {
    if ( $(`#${myID}`).val() == '') {
        alert('입력된 내용이 없습니다.');
        return;
    }
    $(`#${targetID}`).html($(`#${targetID}`).html() + `<tr><td class="showCommentWriterTd">홍길동</td><td class="showCommentTd">${$(`#${myID}`).val()}</td><td class="commentDeleteTd">삭제</td></tr>`)
    $(`#${myID}`).val('');
}


let nowDate = new Date();
console.log(nowDate.getFullYear());
console.log(nowDate.getMonth());
console.log(nowDate.getDate());

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
        	if(data.check == "success") {
        		console.log('글쓰기 성공');
        		alert('글쓰기 성공 테스트');
        		window.location.reload();
        	}
        },
        error: () => {
        	console.log('오류발생');
        	alert('글쓰기 실패');
        }
    });
}