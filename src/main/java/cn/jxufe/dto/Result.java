package cn.jxufe.dto;

/**
 * @ClassName: Result
 * @author: hsw
 * @date: 2019/4/20 11:04
 * @Description: TODO
 */
public class Result<T> {
    /**
     * 请求的返回状态
     */
    private int status;
    /**
     * 返回的描述信息
     */
    private String description;
    /**
     * 返回的数据信息，任何类型
     */
    private T data;

    public Result() {
    }

    public Result(int status, String description, T data) {
        this.status = status;
        this.description = description;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                "status=" + status +
                ", description='" + description + '\'' +
                ", data=" + data +
                '}';
    }
}
