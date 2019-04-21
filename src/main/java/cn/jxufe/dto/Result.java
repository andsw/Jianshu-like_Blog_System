package cn.jxufe.dto;

/**
 * @ClassName: Result
 * @author: hsw
 * @date: 2019/4/20 11:04
 * @Description: TODO
 */
public class Result<T> {
    private int status;
    private String description;
    private T data;
    private String nextAction;

    public Result() {
    }

    public Result(int status, String description, T data, String nextAction) {
        this.status = status;
        this.description = description;
        this.data = data;
        this.nextAction = nextAction;
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

    public String getNextAction() {
        return nextAction;
    }

    public void setNextAction(String nextAction) {
        this.nextAction = nextAction;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", description='" + description + '\'' +
                ", data=" + data +
                ", nextAction='" + nextAction + '\'' +
                '}';
    }
}
