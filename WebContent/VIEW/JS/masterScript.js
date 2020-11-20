$(document).ready(() => {
	$('.boardDeleteButton').click(() => {
		alert('아직 구현되지 않음');
	});
});

//유저 리스트를 출력하는 함수
function getUserList() {
	$.ajax({
        url: `/callBoardServlet`,
        type: "post",
        data: {categoryName:$('#categoryName').val(),
        		boardNumber:$('#boardNumber').val()},
        cache: false,
        dataType: "json",
        success: (data) => {
        	
        },
        error: () => {
            console.log('마스터 : 유저 갱신 오류발생');
        }
    });
}

//위 함수에서 받은 json 객체를 가지고 html String을 반환하는 함수
function returnUserObjectString(userObject) {
	let returnString = `<tr><td class="userId">${userObject.userID}</td>` + 
	`<td class="userName">${userObject.userName}</td>` + 
	`<td class="userNickname">${userObject.userNickname}</td></tr>`;
	return returnString;
}