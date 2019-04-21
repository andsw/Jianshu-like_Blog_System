package cn.jxufe.service.impl;

import cn.jxufe.dao.UserDao;
import cn.jxufe.service.LoginService;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.security.auth.login.AccountNotFoundException;

/**
 * @ClassName: LoginServiceImpl
 * @author: hsw
 * @date: 2019/4/3 22:15
 * @Description: TODO
 */
public class LoginServiceImpl implements LoginService {

    private final static char CHAR_IN_EMAIL = '@';

    @Autowired
    private UserDao userDao;

    @Override
    public String getPassword(String principle) {
        String password;
        System.out.println("\n----------------------------getPassword method in loginService----------------------------");
        if (principle.indexOf(CHAR_IN_EMAIL) != -1) {
            //用邮箱登录
            password = userDao.getPasswordByEmail(principle);
        } else {
            //否则用电话
            password = userDao.getPasswordByTel(principle);
        }
        return password;
    }
}
