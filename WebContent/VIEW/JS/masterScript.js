let localUserNumber = 0;
let localUserID = "";
let localUserStopDay = "";
let localUserStatus = "";

//초반 진입시 유저리스트 갱신함수
$(document).ready(() => {
	console.log('유저리스트 갱신');
	getUserList();
})


function getUserList() {
	$.ajax({
        url: `master/user/list`,
        type: "post",
        cache: false,
        dataType: "json",
        success: (data) => {
        	if(data.check == 'success') {
        		$(`#userTableList`).html('');
        		$.each (data.userList, function (index, el) {
                    $(`#userTableList`).append(returnUserObjectString(el));
                });
        		return;
        	}
        	if(data.check == 'noAdmin') {
        		console.error('어드민이 아닌 사용자의 요청을 받음')
        		return;
        	}
        	if(data.check == 'noUser') {
        		console.error('비로그인 사용자의 요청을 받음')
        		return;
        	}
        },
        error: () => {
            console.error('유저리스트 갱신 오류발생');
        }
    });
}

//위 함수에서 받은 json 객체를 가지고 html String을 반환하는 함수
function returnUserObjectString(userObject) {
	let returnString = `<tr onclick="userSetting(${userObject.userNumber}, '${userObject.userID}', ${userObject.userStopDay}, '${userObject.userStatus}');userSelect();"><td class="userId">${userObject.userID}</td>` + 
	`<td class="userName">${userObject.userName}</td>` + 
	`<td class="userNickname">${userObject.userNickname}</td></tr>`;
	return returnString;
}


function userSetting(userNumber, userID, userStopDay, userStatus) {
	localUserNumber = userNumber;
	localUserID = userID;
	localUserStopDay = userStopDay;
	localUserStatus = userStatus;
}


function userSelect() {
	console.log(`${localUserNumber} : ${localUserID}, ${localUserStopDay}, ${localUserStatus}`);
	
	$('#selecteUserNumber').val(localUserNumber);
	$('#selecteUserID').val(localUserID);
	$('#selecteUserStopDay').val(localUserStopDay);
	$('#selecteUserStatus').val(localUserStatus);
	
	$(`#selectedUserBoardList`).html('');
	$(`#selectedUserCommentList`).html('');
	$.ajax({
        url: `master/user/board`,
        type: "post",
        data: {
        	userNumber:localUserNumber
        },
        cache: false,
        dataType: "json",
        success: (data) => {
        	console.log(data.check);
        	if(data.check == 'success') {
        		console.log('유저 선택 성공');
        		$.each (data.boardList, function (index, el) {
                    $(`#selectedUserBoardList`).append(returnBoardList(el));
                });
        		if(data.boardList.length < 1) {
        			$(`#selectedUserBoardList`).append(emptyList());
        		}
        		$.each (data.commentList, function (index, el) {
                    $(`#selectedUserCommentList`).append(returnCommentList(el));
                });
        		if(data.commentList.length < 1) {
        			$(`#selectedUserCommentList`).append(emptyList());
        		}
        		return;
        	}
        	if(data.check == 'noAdmin') {
        		console.error('어드민이 아닌 사용자의 요청을 받음')
        		return;
        	}
        	if(data.check == 'noSelected') {
        		console.error('비로그인 사용자의 요청을 받음')
        		return;
        	}
        },
        error: () => {
            console.error('유저리스트 갱신 오류발생');
        }
    });
}

function returnBoardList(boardObject) {
	let returnString = `<tr><td class=boardNumber>${boardObject.number}</td>` + 
	`<td class=boardContents>${boardObject.text}</td>` + 
	`<td class=boardDate>${boardObject.date}</td>` + 
	`<td class=boardControl><input type="button" class="boardDeleteButton" value="삭제" onclick="boardDelete(${boardObject.number}, ${localUserNumber})"></td></tr>`;
	return returnString;
}

function returnCommentList(commentObject) {
	let returnString = `<tr><td class=commentContents>${commentObject.text}</td>` + 
	`<td class=commentDate>${commentObject.date}</td>` + 
	`<td class=commentControl><input type="button" class="commentDeleteButton" value="삭제"></td></tr>`;
	return returnString;
}

function emptyList() {
	let returnString = `<tr><td class=emptyList>작성한 내용이 없습니다!</td></tr>`;
	return returnString;
}


function boardDelete(boardNumber) {
	$.ajax({
        url: `master/user/board/delete`,
        type: "post",
        data: {
        	boardNumber:boardNumber,
        	userNumber:localUserNumber
        },
        cache: false,
        dataType: "json",
        success: (data) => {
        	console.log(data.check);
        	if(data.check == "success") {
        		alert('삭제에 성공했습니다.');
        		userSelect();
        	}
        	if(data.check != "success") {
        		console.error('삭제에 실패했습니다.')
        		userSelect();
        	}
        },
        error: () => {
            console.error('게시물 삭제 오류발생');
        }
    });
}



function resetStopDay() {
	if(localUserID == "" || localUserID == null) {
		alert('유저를 선택해주십시오');
		return;
	}
	if(localUserStopDay <= 0) {
		alert('이 유저는 이미 제제일이 없습니다.');
		return;
	}
	
	let check = confirm(`${localUserID}유저의 제제일을 초기화하겠습니까?`);
	if(!check) {
		alert('취소되었습니다.');
		return;
	}
	$.ajax({
        url: `master/user/stopReset`,
        type: "post",
        data: {
        	userNumber:localUserNumber
        },
        cache: false,
        dataType: "json",
        success: (data) => {
        	console.log(data);
        	if(data.check == "success") {
        		alert('제제일 초기화에 성공했습니다.');
        		localUserStopDay = 0;
        		getUserList();
        		userSelect();
        		return;
        	}
        	if(data.check == "outUser") {
        		alert('영구정지된 유저는 초기화할수 없습니다.');
        		console.error('초기화 업데이트에 실패했습니다.');
        		getUserList();
        		return;
        	}
        	if(data.check == "notAdmin") {
        		alert('당신은 어드민 유저가 아닙니다.');
        		console.error('초기화 업데이트에 실패했습니다.');
        		getUserList();
        		return;
        	}
        	if(data.check != "success") {
        		alert('제제일 업데이트에 실패했습니다.');
        		console.error('제제일 업데이트에 실패했습니다.');
        		getUserList();
        		return;
        	}
        },
        error: () => {
            console.error('유저 제제일 갱신 오류발생');
        }
    });
	
}

function updateStopDay() {
	console.log($("#selectUserStopDayList option:selected").val());
	
	if(localUserID == "" || localUserID == null) {
		alert('유저를 선택해주십시오');
		return;
	}
	if($("#selectUserStopDayList option:selected").val() < 1) {
		alert('날짜를 선택해주십시오');
		return;
	}
	
	let check = confirm(`${localUserID}유저의 제제일을 증가시키겠습니까?`);
	if(!check) {
		alert('취소되었습니다.');
		return;
	}
	
	$.ajax({
        url: `master/user/stopUp`,
        type: "post",
        data: {
        	addDay:$("#selectUserStopDayList option:selected").val(),
        	userNumber:localUserNumber
        },
        cache: false,
        dataType: "json",
        success: (data) => {
        	console.log(data);
        	if(data.check == "success") {
        		alert('제제일 업데이트에 성공했습니다.');
        		getUserList();
        		localUserStopDay += data.plusDay;
        		userSelect();
        		return;
        	}
        	if(data.check == "outUser") {
        		alert('영구정지된 유저는 제제할수 없습니다.');
        		console.error('제제일 업데이트에 실패했습니다.');
        		getUserList();
        		return;
        	}
        	if(data.check == "notAdmin") {
        		alert('당신은 어드민 유저가 아닙니다.');
        		console.error('제제일 업데이트에 실패했습니다.');
        		getUserList();
        		return;
        	}
        	if(data.check != "success") {
        		alert('제제일 업데이트에 실패했습니다.');
        		console.error('제제일 업데이트에 실패했습니다.');
        		getUserList();
        		return;
        	}
        },
        error: () => {
            console.error('유저 제제일 갱신 오류발생');
        }
    });
}



function updateUserOut() {
	
	if(localUserID == "" || localUserID == null) {
		alert('유저를 선택해주십시오');
		return;
	}
	
	let check = confirm(`${localUserID}유저를 영구정지시키시겠습니까?`);
	if(!check) {
		alert('취소되었습니다.');
		return;
	}
	
	$.ajax({
		url: `master/user/out`,
		type: "post",
		data: {
			userNumber:localUserNumber
		},
		cache: false,
		dataType: "json",
		success: (data) => {
			console.log(data);
			if(data.check == "success") {
				alert('영구정지에 성공했습니다.');
				localUserStatus = "영구정지";
				localUserStopDay = 0;
				userSelect();
				getUserList();
				console.log('영구정지 업데이트에 성공했습니다.');
				return;
			}
			if(data.check == "outUser") {
				alert('이미 영구정지된 유저입니다.');
				console.error('영구정지 업데이트에 실패했습니다.');
				return;
			}
			if(data.check == "notAdmin") {
				alert('당신은 어드민 유저가 아닙니다.');
				console.error('영구정지 업데이트에 실패했습니다.');
				return;
			}
			if(data.check != "success") {
				alert('영구정지 업데이트에 실패했습니다.');
				console.error('영구정지 업데이트에 실패했습니다.');
				return;
			}
		},
		error: () => {
			console.error('유저 영구정지 갱신 오류발생');
		}
	});
}


function updateUserBack() {
	
	if(localUserID == "" || localUserID == null) {
		alert('유저를 선택해주십시오');
		return;
	}
	
	let check = confirm(`${localUserID}유저를 복귀시키겠습니까?`);
	if(!check) {
		alert('취소되었습니다.');
		return;
	}
	
	$.ajax({
		url: `master/user/back`,
		type: "post",
		data: {
			userNumber:localUserNumber
		},
		cache: false,
		dataType: "json",
		success: (data) => {
			console.log(data);
			if(data.check == "success") {
				alert('다시 활성화 되었습니다.');
				localUserStatus = "활성";
				localUserStopDay = 0;
				userSelect();
				getUserList();
				console.log('활성화 업데이트에 성공했습니다.');
				return;
			}
			if(data.check == "alreadyUser") {
				alert('이미 활성화 되어있는 유저입니다.');
				console.error('활성화 업데이트에 실패했습니다.');
				return;
			}
			if(data.check == "notAdmin") {
				alert('당신은 어드민 유저가 아닙니다.');
				console.error('활성화 업데이트에 실패했습니다.');
				return;
			}
			if(data.check != "success") {
				alert('활성화 업데이트에 실패했습니다.');
				console.error('활성화 업데이트에 실패했습니다.');
				return;
			}
		},
		error: () => {
			console.error('유저 활성화 갱신 오류발생');
		}
	});
}