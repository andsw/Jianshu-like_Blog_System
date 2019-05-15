package cn.jxufe.exception;

/**
 * @ClassName: InsertDbException
 * @author: hsw
 * @date: 2019/5/14 17:42
 * @Description: TODO
 */
public class InsertDbException extends Exception {
    public InsertDbException() {
        super("插入数据库失败！");
    }
}
