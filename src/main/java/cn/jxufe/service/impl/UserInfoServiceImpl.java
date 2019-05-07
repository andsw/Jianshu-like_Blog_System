package cn.jxufe.service.impl;

import cn.jxufe.bean.User;
import cn.jxufe.dao.UserDao;
import cn.jxufe.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

/**
 * @ClassName: UserInfoServiceImpl
 * @author: hsw
 * @date: 2019/4/30 15:31
 * @Description: TODO
 */
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserDao userDao;

    @Override
    @Cacheable(value = "redisCache", key = "'getUserByUserNo-' + #userNo")
    public User getUserByUserNo(int userNo) {
        return userDao.getUserByUserNo(userNo);
    }
}
