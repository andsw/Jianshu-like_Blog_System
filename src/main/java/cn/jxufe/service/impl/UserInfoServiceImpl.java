package cn.jxufe.service.impl;

import cn.jxufe.bean.User;
import cn.jxufe.dao.UserDao;
import cn.jxufe.service.UserInfoService;
import cn.jxufe.util.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;

/**
 * @ClassName: UserInfoServiceImpl
 * @author: hsw
 * @date: 2019/4/30 15:31
 * @Description: TODO
 */
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoderUtil passwordEncoderUtil;

    @Override
    @Cacheable(value = "redisCache", key = "'getUserByUserNo-' + #userNo")
    public User getUserByUserNo(int userNo) {
        return userDao.getUserByUserNo(userNo);
    }

    @Override
    public boolean updatePasswordByUserNo(int userNo, String newPassword) {
        //userDao中获取email和tel的方法赞数先不写service方法！
        String email = userDao.getEmailByUserNo(userNo);
        String tel = userDao.getTelByUserNo(userNo);
        String passwordEmail = "";
        String passwoedTel = "";
        if (!StringUtils.isEmpty(email)) {
            passwordEmail = passwordEncoderUtil.encode(email, newPassword);
        }
        if (!StringUtils.isEmpty(tel)) {
            passwoedTel = passwordEncoderUtil.encode(tel, newPassword);
        }
        System.out.println(passwoedTel + " " + tel);
        System.out.println(passwordEmail + " " + email);
        return userDao.updatePasswordByUserNo(passwordEmail, passwoedTel, userNo) == 1;
    }
}
