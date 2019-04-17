package cn.jxufe.dao;

import cn.jxufe.bean.User;

/**
 * @ClassName: UserDao
 * @author: hsw
 * @date: 2019/3/30 20:06
 * @Description: 一些需求：通过
 */
public interface UserDao {
    /**
     * 先获取密码，成功后再用邮箱或电话获取用户全部信息，防止每次都传递整个用户信息，造成浪费缓存等影响。
     * @param email 用户注册邮箱
     * @return 通过邮箱密码
     */
    String getPasswordByEmail(String email);

    /**
     * 电话获取密码
     * @param tel 注册号码
     * @return .
     */
    String getPasswordByTel(String tel);

    /**
     * 邮箱号获取用户所有信息
     * @param email 邮箱号
     * @return 用户对象
     */
    User getUserByEmail(String email);

    /**
     * 电话获取用户所有信息
     * @param tel 电话
     * @return 用户对象
     */
    User getUserByTel(String tel);

    /**
     * 用户序号获取用户
     * @param userNo 用户
     * @return 用户信息
     */
    User getUserByUserNo(Integer userNo);

    /**
     * 注册时插入用户信息
     * @param user 用户对象
     * @return 添加成功返回用户user_no
     */
    int insertUser(User user);
}
