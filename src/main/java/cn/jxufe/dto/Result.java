package cn.jxufe.dto;

/**
 * @ClassName: Result
 * @author: hsw
 * @date: 2019/5/9 21:15
 * @Description: TODO
 */
public class Result<T> {

    private int code;
    private String message;
    private T data;

    public Result() {
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * message必有好吧！
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data,String message) {
        return new Result<>(HttpStatusCode.SERVICE_OK.getCode(), message, data);
    }

    /**
     * 成功但不返回数据！
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(String message) {
        return new Result<>(HttpStatusCode.SERVICE_OK.getCode(), message, null);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(HttpStatusCode.SERVICE_ERROR.getCode(), message, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
