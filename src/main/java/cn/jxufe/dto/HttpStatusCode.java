package cn.jxufe.dto;

/**
 * @author hsw
 * @create 2019-05-09  21:38
 */
public enum HttpStatusCode {
    /**
     * 200 表示成功处理了请求，通常表示服务器提供了请求的网页！
     * 201 表示请求成功，并且服务器创建了新的资源！
     * 202 表示服务器成功接收了请求，但未处理！
     * 204 表示服务器成功处理了请求，但没有返回任何信息！
     * <p>
     * 401 表示未授权，请求需要有身份验证
     * 403 表示服务器拒绝了请求！
     * <p>
     * 500 表示服务器端发生错误，无法完成请求！
     */
    SERVICE_OK(200)
    ,SERVICE_CREATED(201)
    ,SERVICE_RECEIVED_NOT_PROCESSED(202)
    ,SERVICE_OK_RETURN_EMPTY(204)

    ,SERVICE_AUTHENTIC_ERROR(401)
    ,SERVICE_REFUSE_REQUEST	(403)
    ,SERVICE_ERROR(404)

    ,DB_SERVICE_UNKNOWN_ERROR(500);

    private int code;

    HttpStatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "HttpStatusCode{" +
                "code=" + code +
                '}';
    }
}
