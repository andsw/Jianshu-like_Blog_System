package cn.jxufe.service.impl;

import cn.jxufe.dao.UserDao;
import cn.jxufe.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

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

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public String getPassword(String principle) {
        String password;
        if (principle.indexOf(CHAR_IN_EMAIL) != -1) {
            //用邮箱登录
            password = userDao.getPasswordByEmail(principle);
        } else {
            //否则用电话
            password = userDao.getPasswordByTel(principle);
        }
        if ("".equals(password)) {
            System.out.println("找不到该用户。");
            throw new org.apache.shiro.authc.UnknownAccountException("用户不存在");
        }
        return password;
    }
}
