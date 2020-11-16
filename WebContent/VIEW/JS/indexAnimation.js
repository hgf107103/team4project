let backgroundNumber = 1;
let signupStatus = {
    id:false,
    pwd:false,
    name:false,
    nickname:false
};

setInterval(() => {
    backgroundNumber++;
    $('body').css("background", `url('VIEW/img/back/backimg${backgroundNumber}.jpg')`);
    $('body').css("background-repeat", `no-repeat`);
    $('body').css("background-size", `cover`);
    $('body').css("background-attachment", `fiexd`);
    if (backgroundNumber >= 3 || backgroundNumber < 0) {
        backgroundNumber = 0;
    }
}, 6000);
let menuOpenKey = true;
function menuOpenFunction() {
    if (menuOpenKey) {
        if ($('#menuLOL').css('opacity') != 0) {
            location.href='#menuCloss';
            return;
        }
        menuOpenKey = false;
        location.href='#menuOpen';
        return;
    }
    menuOpenKey = true;
    location.href='#menuCloss';
    return;
}
function show(id, rgb, text) {
    $(`#${id}`).css('color', rgb);
    setTimeout(() => {
        $(`#${id}`).css('color', 'black');
        $(`#${id}`).val(text);
    }, 500)
}
function out(id, rgb, text) {
    $(`#${id}`).css('color', rgb);
    setTimeout(() => {
        $(`#${id}`).css('color', 'black');
        $(`#${id}`).val(text);
    }, 1100)
}
function loginFunction() {
    if ($('#idInput').val() == 'admin' && $('#pwdInput').val() == '') {
        window.open('master.html','title','height=' + screen.height + ',width=' + screen.width + 'fullscreen=yes, status=no, titlebar=no, location=no, resizable=no');
    
        $('#idInput').val('');
        return;
    }

    if ($('#idInput').val() != '') {
        if ($('#pwdInput').val() != '') {
            location.href = 'login.html';
            return;
        }
        alert('패스워드를 입력하십시오');
        $('#pwdInput').focus()
        return;
    }
    alert('아이디를 입력하십시오');
    $('#idInput').focus()
}

function callblur() {
    $('.callBlur').css('display','none');
}
function blurOut() {
    $('.callBlur').css('display','flex');
}

let strUrl = window.location.href;
let strUrlTemp = strUrl.substr(window.location.href.length - 9 , window.location.href.length);
if (strUrlTemp == 'signupDiv') {
    location.href='#signupDiv';
    setTimeout(() => {
        showSignup();
    },0)
}
else if (strUrlTemp != 'signupDiv') {
    location.href='#menuCloss';
}


function showSignup() {
    callblur();
    set(() => {
        $('#logo').css('left', '20vw');
        set(() => {
            $('#logo').css('opacity', '1');
        })
    });

    set(() => {
        $('#signupFormBack').css('width', '38%');
        set(() => {
            $('#signupFormBack').css('opacity', '1');
        })
    })

    
    setTimeout(() => {
        $('#signupForm').css('right', '0px');
        setTimeout(() => {
            $('#signupForm').css('opacity', '1');
        },30)
    },30)
}
function signupExit() {
    $('#logo').css('left', '0vh');
    $('#logo').css('opacity', '0');
    $('#signupFormBack').css('width', '0px');
    $('#signupFormBack').css('opacity', '0');
    $('#signupForm').css('right', '-35%');
    $('#signupForm').css('opacity', '0');
    blurOut();
}

function set(callback) {
    setTimeout(callback,30);
}

function idCheck() {
    if (!regExpId($('#signupIdInput').val())) {
        $('#signupIdLog').html('아이디는 영문과 숫자가 포함되며, 영문과 숫자로만 이루어진 6자리 이상 15자리 이하의 문자열입니다.');
        $('#signupIdLog').css('color', 'rgb(255, 0, 0)');
        signupStatus.id = false;
        return;
    }

    if ($('#signupIdInput').val() == '') {
        $('#signupIdLog').text('아이디를 입력해주십시오.');
        $('#signupIdLog').css('color', 'rgb(255, 0, 0)');
        signupStatus.id = false;
        return;
    }
    $('#signupIdInput').css('color', 'rgb(0, 141, 7)');
    $('#signupIdInput').css('background-color', 'rgb(240,240,240)');
    $('#signupIdInput').attr('readonly', 'on');
    $('#signupIdInput').css('cursor', 'default');
    $('#signupIdLog').text('아이디 중복확인 되었습니다.');
    $('#signupIdLog').css('color', 'rgb(0, 141, 7)');
    signupStatus.id = true;
    
    $('#signupForm').css('top', '38%');
    $('#moreForm').css('display', 'block');
    setTimeout(() => {
        $('#moreForm').css('opacity', '1');
    }, 400);

    $('#signupPwd').focus()
}

function regExpPwd(str) {
    const pwdReg = new RegExp('(?!^[0-9]*$)(?!^[a-zA-Z!@#$%^&*()_+=<>?]*$)^([a-zA-Z!@#$%^&*()_+=<>?0-9]{8,15})$', 'gm');
    return pwdReg.test(str);
}

function regExpId(str) {
    const idReg = new RegExp('(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,20})$', 'gm');
    return idReg.test(str);
}

function regExpNickName(str) {
    const idReg = new RegExp('(?!^[0-0]*$)^([가-힣||ㄱ-ㅎ||ㅏ-ㅣ||a-z||A-Z||0-9||あ-ん||?~!@#$%^&*]{0,15})$', 'gm');
    return idReg.test(str);
}

function regExpName(str) {
    const idReg = new RegExp('(?!^[0-9]*$)^([가-힣]{0,6})$', 'gm');
    return idReg.test(str);
}

function pwdCheck() {
    if (!regExpPwd($('#signupPwd').val())) {
        $('#signupPwdLog').html('비밀번호는 영문과 숫자가 반드시 포함되어야 하며<br>8자리 이상 15자리 이하로 만들어야합니다.');
        $('#signupPwdLog').css('color', 'rgb(255, 0, 0)');
        $('#signupForm').css('top', '38%');
        $('#optionForm').css('display', 'none');
        setTimeout(() => {
            $('#optionForm').css('opacity', '0');
        }, 100);
        signupStatus.pwd = false;
        return;
    }

    if ($('#signupPwd').val() === $('#signupPwdCheck').val()) {
        $('#signupPwdLog').text('비밀번호가 같습니다.')
        $('#signupPwdLog').css('color', 'rgb(0, 141, 7)');
        $('#signupForm').css('top', '26%');
        $('#optionForm').css('display', 'block');
        setTimeout(() => {
            $('#optionForm').css('opacity', '1');
        }, 400);
        signupStatus.pwd = true;
        return;
    }

    $('#signupPwdLog').text('비밀번호가 다릅니다.');
    $('#signupPwdLog').css('color', 'rgb(255, 0, 0)');
    $('#signupForm').css('top', '38%');
    $('#optionForm').css('display', 'none');
    setTimeout(() => {
        $('#optionForm').css('opacity', '0');
    }, 100);
    signupStatus.pwd = false;
}

function nameCheck() {
    if (!regExpName($('#signupName').val())) {
        $('#signupNameLog').text('이름은 6자리로 제한되며 한글만 들어갈 수 있습니다.')
        $('#signupNameLog').css('color', 'rgb(255, 0, 0)');
        signupStatus.name = false;
        return;
    }
    $('#signupNameLog').text('올바른 이름입니다.')
    $('#signupNameLog').css('color', 'rgb(0, 141, 7)');
    signupStatus.name = true;
}
function nicknameCheck() {
    if (!regExpNickName($('#signupNickName').val())) {
        $('#signupNickNameLog').text('닉네임은 15자까지 가능합니다.')
        $('#signupNickNameLog').css('color', 'rgb(255, 0, 0)');
        signupStatus.nickname = false;
        return;
    }
    $('#signupNickNameLog').text('올바른 닉네임입니다.')
    $('#signupNickNameLog').css('color', 'rgb(0, 141, 7)');
    signupStatus.nickname = true;
}

function signupSubmit() {
    if (signupStatus.id) {
        if (signupStatus.pwd) {
            if (signupStatus.name) {
                if (signupStatus.nickname) {
                    alert('회원가입 되었습니다.');
                    location.href = 'login.html';
                    return;
                }
            }
        }
    }
    alert('회원가입 실패');
}