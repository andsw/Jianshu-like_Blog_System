package cn.jxufe.dto;

import lombok.*;
import lombok.experimental.Accessors;

import cn.jxufe.myenum.HttpStatusCode;

/**
 * 使用Accessors能使该类链式创建：result.setCode(200).setMessage("成功").setData(null);
 * @ClassName: Result
 * @author: hsw
 * @date: 2019/5/9 21:15
 * @Description: TODO
 */
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Result<T> {

    private int code;
    private String message;
    private T data;

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

    /**
     * 成功，返回数据就行，不需要message！(为避免T为String搞错方法所以不用重载，重新命名方法吧！)
     * @param <T>
     * @return
     */
    public static <T> Result<T> successWithDataOnly(T data) {
        return new Result<>(HttpStatusCode.SERVICE_OK.getCode(), null, data);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(HttpStatusCode.SERVICE_ERROR.getCode(), message, null);
    }
}
