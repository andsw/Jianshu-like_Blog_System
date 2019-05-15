package cn.jxufe.exception;

/**
 * @ClassName: UpdateDbException
 * @author: hsw
 * @date: 2019/5/14 17:41
 * @Description: TODO
 */
public class UpdateDbException extends Exception {
    public UpdateDbException() {
        super("数据更新失败！");
    }
}
