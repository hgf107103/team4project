//초반 진입시 유저리스트 갱신함수
$(document).ready(() => {
	console.log('유저리스트 갱신')
	$.ajax({
            url: `master/user/list`,
            type: "post",
            cache: false,
            dataType: "json",
            success: (data) => {
            	if(data.check == 'success') {
            		$.each (data.userList, function (index, el) {
                        $(`#userTableList`).append(returnUserObjectString(el));
                    });
            		return;
            	}
            	if(data.check == 'noAdmin') {
            		console.log('어드민이 아닌 사용자의 요청을 받음')
            		return;
            	}
            	if(data.check == 'noUser') {
            		console.log('비로그인 사용자의 요청을 받음')
            		return;
            	}
            },
            error: () => {
                console.log('유저리스트 갱신 오류발생');
            }
        });
	
	check = 1;
})

//위 함수에서 받은 json 객체를 가지고 html String을 반환하는 함수
function returnUserObjectString(userObject) {
	let returnString = `<tr onclick="userSelect(${userObject.userNumber}, '${userObject.userID}', ${userObject.userStopDay}, '${userObject.userStatus}')"><td class="userId">${userObject.userID}</td>` + 
	`<td class="userName">${userObject.userName}</td>` + 
	`<td class="userNickname">${userObject.userNickname}</td></tr>`;
	return returnString;
}


function userSelect(userNumber, userID, userStopDay, userStatus) {
	$('#selecteUserNumber').val(userNumber);
	$('#selecteUserID').val(userID);
	$('#selecteUserStopDay').val(userStopDay);
	$('#selecteUserStatus').val(userStatus);
}