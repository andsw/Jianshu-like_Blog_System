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

// 如果是异步的，即异步非阻塞，后台在没有得到结果的情况下也会返回结果，但是失败的结果，知道获取到结果才会执行成功方法！
// 那么请求时会返回多个结果，所以不可以显示设置这个不然后面成功了还是显示错误会不好！
function defaultErrorMethod(XMLHttpRequest, textStatus, errorThrown) {
    //Materialize.toast('oops！有请求出错了！！！', 2000);
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