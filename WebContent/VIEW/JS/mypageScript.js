function showMenu(text) {
    $('#mainTableShow').text(text);
}

function messagePopupOpen() {
    let win = window.open("userMessage.html#reception", "PopupWin", "width=600,height=601");
}

function getURLID() {
    let strUrl = window.location.href;
    let strUrlTemp = strUrl.substr(window.location.href.length - 11 , 7);
    return strUrlTemp;
}

function setStyle(tableName) {
    const list = ['content', 'comment'];
    list.forEach((value, index, array) => {
        $(`#${value}Table`).css('display', 'none');
        if (value == tableName) {
            $(`#${tableName}Table`).css('display', 'table');
        }
    });
}