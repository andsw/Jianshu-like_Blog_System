package cn.jxufe.service.impl;

import cn.jxufe.bean.User;
import cn.jxufe.dao.UserDao;
import cn.jxufe.service.LoginService;
import javafx.beans.binding.ListBinding;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: LoginServiceImpl
 * @author: hsw
 * @date: 2019/4/3 22:15
 * @Description: 该服务类管理范围从 注册 登录直到登录成功返回useNoCookie。
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

    /**
     * 确保添加
     * @param user
     * @return 返回的是自增主键id的值，int类型。
     */
    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public List<String> whichInfoIsExisted(User user) {
        System.out.println(user);
        List<String> infoExisted = new ArrayList<>(3);
        if (!userDao.doseUsernameExisted(user.getUsername())) {
            infoExisted.add("username");
        }
        if (!userDao.doseEmailExisted(user.getEmail())) {
            infoExisted.add("email");
        }
        if (!userDao.doseTelExisted(user.getTel())) {
            infoExisted.add("tel");
        }

        if (infoExisted.isEmpty()) {
            infoExisted.add("unknown");
        }

        return infoExisted;
    }

    @Override
    public int getUserNoWhenLoginSuccessfully(String username) {
        return username.contains("@") ? userDao.getUserNoByEmail(username) : userDao.getUserNoByTel(username);
    }

}
