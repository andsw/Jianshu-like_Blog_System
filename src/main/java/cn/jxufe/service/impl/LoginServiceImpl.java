package cn.jxufe.service.impl;

import cn.jxufe.bean.User;
import cn.jxufe.dao.UserDao;
import cn.jxufe.exception.PasswordModificationFailedException;
import cn.jxufe.service.LoginService;
import cn.jxufe.util.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private PasswordEncoderUtil passwordEncoderUtil;

    /**
     * 根据登录时的用户名获取userNo然后再获取密码！或者在注册时也能用上！
     * @param principle
     * @return
     */
    @Override
    public Integer getUserNoByPrinciple(String principle) {
        return principle.indexOf(CHAR_IN_EMAIL) != -1 ? userDao.getUserNoByEmail(principle) : userDao.getUserNoByTel(principle);
    }

    @Override
    public String getPasswordByUserNo(int userNo) {
        return userDao.getPasswordByUserNo(userNo);
    }

    /**
     * 这里先添加密码为空字符串的用户，获取到自增的userNo后再用useNo作为盐值加密密码，然后再修改密码
     * @param user
     * @return 返回的是自增主键id的值，int类型。
     */
    @Override
    @Transactional(rollbackFor = PasswordModificationFailedException.class)
    public boolean registerUser(User user, String realPassword) throws PasswordModificationFailedException {
        int num = userDao.insertUser(user);
        // 插入成功就再设置密码，否则就不设置！
        if (num == 1) {
            String passwordUserNo = passwordEncoderUtil.encode(user.getUserNo(), realPassword);
            if (userDao.updatePasswordByUserNo(passwordUserNo, user.getUserNo()) == 0) {
                throw new PasswordModificationFailedException("注册时密码修改失败，导致用户注册失败！");
            }
            return true;
        }
        return false;
    }

    @Override
    public List<String> whichInfoIsExisted(User user) {
        System.out.println(user);
        List<String> infoExisted = new ArrayList<>(3);
        if (!userDao.isUsernameExisted(user.getUsername())) {
            infoExisted.add("username");
        }
        if (!userDao.isEmailExisted(user.getEmail())) {
            infoExisted.add("email");
        }
        if (!userDao.isTelExisted(user.getTel())) {
            infoExisted.add("tel");
        }

        if (infoExisted.isEmpty()) {
            infoExisted.add("unknown");
        }

        return infoExisted;
    }

    @Override
    public Integer getUserNoWhenLoginSuccessfully(String username) {
        return username.contains("@") ? userDao.getUserNoByEmail(username) : userDao.getUserNoByTel(username);
    }

}
