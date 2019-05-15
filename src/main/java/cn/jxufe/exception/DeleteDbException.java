package cn.jxufe.exception;

/**
 * @ClassName: DeleteDbException
 * @author: hsw
 * @date: 2019/5/14 17:42
 * @Description: TODO
 */
public class DeleteDbException extends Exception {
    public DeleteDbException() {
        super("删除数据库失败！");
    }
}
