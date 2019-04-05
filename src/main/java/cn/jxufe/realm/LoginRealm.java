package cn.jxufe.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 * @ClassName: LoginRealm
 * @author: hsw
 * @date: 2019/4/4 11:18
 * @Description: TODO
 */
public class LoginRealm extends AuthenticatingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("login realm");
        return null;
    }
}
