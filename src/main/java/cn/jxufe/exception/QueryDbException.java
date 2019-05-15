package cn.jxufe.exception;

/**
 * @ClassName: QueryDbException
 * @author: hsw
 * @date: 2019/5/14 17:43
 * @Description: TODO
 */
public class QueryDbException extends Exception {
    public QueryDbException() {
        super("查询数据库失败！");
    }
}
