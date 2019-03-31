package cn.jxufe.dao;

import cn.jxufe.bean.User;

import java.util.List;

/**
 * @ClassName: UserDao
 * @author: hsw
 * @date: 2019/3/30 20:06
 * @Description: TODO
 */
public interface UserDao {
    /**
     * 获取所有用户信息
     * @return 用户列表
     */
    List<User> getAllUsers();
}
