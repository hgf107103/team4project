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