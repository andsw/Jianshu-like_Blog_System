package cn.jxufe.dao;

/**
 * @ClassName: UserDao
 * @author: hsw
 * @date: 2019/3/30 20:06
 * @Description: 一些需求：通过
 */
public interface UserDao {

    /**
     * .
     * @param username 用户名
     * @return 通过用户名获取密码。
     */
    String getPasswordByUsername(String username);

    /**
     * .
     * @param email 用户注册邮箱
     * @return 通过邮箱密码
     */
    String getPasswordByEmail(String email);

    /**
     * .
     * @param tel 注册号码
     * @return .
     */
    String getPasswordByTel(String tel);


}
