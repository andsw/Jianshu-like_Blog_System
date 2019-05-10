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

function defaultErrorMethod(XMLHttpRequest, textStatus, errorThrown) {
    console.log(XMLHttpRequest.status);
    console.log(XMLHttpRequest.readyState);
    console.log(textStatus);
}
