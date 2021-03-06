package cn.jxufe.shiro.realm;

import cn.jxufe.service.LoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.security.auth.login.AccountNotFoundException;

/**
 * @ClassName: LonginRealm
 * @author: hsw
 * @date: 2019/4/4 11:18
 * @Description: TODO
 */
public class LoginRealm extends AuthorizingRealm {

    @Resource
    private LoginService loginService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
//        System.out.println("\n----------------进入 doGetAuthenticationInfo method in LoginRealm!----------------");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        String userNo = token.getUsername();
        String password = loginService.getPasswordByUserNo(Integer.parseInt(userNo));

        if (StringUtils.isEmpty(password)) {
            throw new AuthenticationException();
        }

        return new SimpleAuthenticationInfo(userNo, password, ByteSource.Util.bytes(userNo), "");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        System.out.println("\n----------------进入 doGetAuthorizationInfo method in LoginRealm!----------------");
//        String principle = (String) principalCollection.getPrimaryPrincipal();
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        if ("admin".equals(principle)) {
//            info.addStringPermission("admin:get");
//        } else if ("user".equals(principle)) {
//            info.addStringPermission("user:get");
//        }
//        return info;
        return null;
    }

    /**
     * hex也称为base16，意思是使用16个可见字符来表示一个二进制数组，编码后数据大小将翻倍,因为1个字符需要用2个可见字符来表示。
     * base32，意思是使用32个可见字符来表示一个二进制数组，编码后数据大小变成原来的8/5,也即5个字符用
     *          8个可见字符表示，但是最后如果不足8个字符，将用=来补充。
     * base64，意思是使用64个可见字符来表示一个二进制数组，编码后数据大小变成原来的4/3,也即3个字符用4个可见字符来表示。
     * @param args
     */
    public static void main(String[] args) {
        ByteSource salt = ByteSource.Util.bytes("1");
//        base64 xB18ZuG4QEVFqjoOziAGrA== 时间：20ms
//        hex c41d7c66e1b8404545aa3a0ece2006ac 时间 20ms
        long start = System.currentTimeMillis();
        String passwd = new SimpleHash("MD5", "123", salt, 1024).toBase64();
        long end = System.currentTimeMillis();
        System.out.println(passwd);
        System.out.println("消耗的时间是 ：" + (end - start));
    }
}
