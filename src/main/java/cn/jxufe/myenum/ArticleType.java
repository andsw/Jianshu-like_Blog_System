package cn.jxufe.myenum;

import lombok.Getter;

/**
 * @author hsw
 * @create 2019-05-24  16:41
 */
@Getter
public enum ArticleType {
    /**
     * 原创、转载、翻译
     */
    ORIGINAL((byte)0, "原创"),
    REPRINTED((byte)1,"转载"),
    TRANSLATION((byte) 2, "翻译");

    private byte code;
    private String message;

    ArticleType(byte code, String message) {
        this.code = code;
        this.message = message;
    }

}
