let strUrl = window.location.href;
let strUrlTemp = strUrl.substr(window.location.href.length - 9 , window.location.href.length);
console.log(strUrlTemp);

setTimeout(() => {
    messageSend(strUrlTemp);
})

function messageSend(key) {
    const list = ['reception','mySending','goMessage','mySetting'];
    $(`#${key}Show`).css('display', 'block');
    location.href = `#${key}`;
    for (let index = 0; index < list.length; index++) {
        if (key == list[index]) {continue;}
        $(`#${list[index]}Show`).css('display', 'none');
    }
}


function countFunction(CONTENT_MAX_SIZE) {
    if ($('#receptionContentsInput').val().length <= CONTENT_MAX_SIZE) {
        $('#receptionCountInput').val(`${CONTENT_MAX_SIZE - $('#receptionContentsInput').val().length}`)
        return;
    }
    let str = $('#receptionContentsInput').val();
    let str2 = str.substr(0,CONTENT_MAX_SIZE - 1);
    $('#receptionContentsInput').val(str2);
    alert('쪽지는 500자까지만 적을 수 있습니다.')
    setTimeout(() => {
        $('#receptionCountInput').val(`${CONTENT_MAX_SIZE - $('#receptionContentsInput').val().length}`)
    },0)
}