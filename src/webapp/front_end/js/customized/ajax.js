//json数据传入后台
function request_application_json(url, method, data, successfulMethod, errorMethod, async) {
    $.ajax({
        url: url,
        method: method,
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: successfulMethod,
        error: errorMethod,
        async: async,
        timeout: 5000
    });
}

//使用plain/text格式的内容传递给后台
function request_plain_text(url, method, data, successfulMethod, errorMethod, async) {
    $.ajax({
        url: url,
        method: method,
        contentType: 'plain/text',
        data: data,
        success: successfulMethod,
        error: errorMethod,
        async: async,
        timeout: 5000
    });
}

function getUserInfo(userNo, method) {
    request_application_json("http://localhost:8080/users/" + userNo,
        "GET",
        "",
        method,
        defaultErrorMethod, false);
}

function defaultErrorMethod(XMLHttpRequest, textStatus, errorThrown) {
    console.log(XMLHttpRequest.status);
    console.log(XMLHttpRequest.readyState);
    console.log(textStatus);
}

// 获取iframe的src属性中带的参数组成的对象！
function getQueryStrings(iframeId) {
    const url = decodeURI(top.document.getElementById(iframeId).src); //获取url中"?"符后的字串
    const theRequest = {};
    let s;
    if (url.indexOf("?") !== -1) {
        const str = url.substr(url.indexOf("?") + 1);
        s = str.split("&");
        for (let i = 0; i < s.length; i++) {
            // console.log(s[i].split("=")[0] + " " + s[i].split("=")[1]);
            theRequest[s[i].split("=")[0]] = s[i].split("=")[1];
        }
    }
    return theRequest;
}