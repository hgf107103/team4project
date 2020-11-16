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


let boardCount = 3;

$(window).scroll(() => {
    //let board = null;
    if ($(window).scrollTop() + $(window).outerHeight() >= $(window).height()) {
        
        $.ajax({
            url: "JS/sampleBoard.json",        //ajax로 불러올 파일의 주소.
            cache: false,           //사용자캐시 사용 여부
            dataType: "json",       //전달받을 데이터의 타입 (예 : XML, json, html 등)
            success: (data) => {    //요청을 전달받은 후 실행될 기능
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
    const boardText = $('#newTextWrite').val().replace(/(\r\n\t|\n|\r\t)/gm," ");

    $('#allBoard').html(
    "<div class=board><input type='button' value='글 삭제' class='boardDeleteButton'>" + 
    `<table class='userBoardContent tableNomal'><tr><td rowspan='2' class='boardSmallTd'>${boardCount}</td><td class="showNameTd">작성자 : 홍길동</td><td class="showDateTd">${nowDate.getFullYear()} ${nowDate.getMonth() + 1} ${nowDate.getDate()}</td></tr>` + 
    `<tr><td class="showBoardTd" colspan="2">${boardText}</td></tr></table>` + 
    `<table class="userBoardCommentTable tableNomal" id="comment${boardCount}"></table>` + 
    `<table class="userBoardCommentWriteTable tableNomal"><tr><td class="writeCommentTd"><textarea class="commentTextarea" id="textarea${boardCount}" onkeyup="commentCountFunction(this, 150)"  rows="3" placeholder="댓글 입력"></textarea></td><td class="submitCommentTd"><input type="button" class="commentWriteButton" onclick="newComment('textarea${boardCount}','comment${boardCount}')" value="댓글쓰기"></td></tr></table>` + 
    `</div>` + $('#allBoard').html());

    alert('새 글이 등록되었습니다.');
    $('#newTextWrite').val('');
    boardCount++;
}