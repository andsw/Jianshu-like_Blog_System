package cn.jxufe.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @ClassName: PasswordEncoderUtil
 * @author: hsw
 * @date: 2019/4/21 15:53
 * @Description: 密码使用email或tel加密的工具类
 */
public class PasswordEncoderUtil {
    public String encode(int userNo, String password) {
        ByteSource salt = ByteSource.Util.bytes(String.valueOf(userNo));
        return new SimpleHash("MD5", password, salt, 1024).toBase64();
    }
}
