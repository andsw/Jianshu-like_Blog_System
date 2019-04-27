function request(url, method, data, successfulMethod, errorMethod, async) {
    $.ajax({
        url: url,
        method: method,
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: successfulMethod,
        error: errorMethod,
        async: async,
        timeout: 2000
    });
}

function defaultErrorMethod(XMLHttpRequest, textStatus, errorThrown) {
    alert("request failed！");
    console.log(XMLHttpRequest.status);
    console.log(XMLHttpRequest.readyState);
    console.log(textStatus);
}