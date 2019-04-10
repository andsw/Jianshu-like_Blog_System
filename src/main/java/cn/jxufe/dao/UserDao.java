package cn.jxufe.dao;

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
     * .
     * @param tel 注册号码
     * @return .
     */
    String getPasswordByTel(String tel);


}
