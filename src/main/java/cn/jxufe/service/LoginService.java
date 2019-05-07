package cn.jxufe.service;

import cn.jxufe.bean.User;

import java.util.List;

/**
 * @ClassName: LoginService
 * @author: hsw
 * @date: 2019/4/3 22:00
 * @Description: TODO
 */
public interface LoginService {
    /**
     * 0
     * @param principle 用户的登录信息获取密码。
     * @return
     */
    String getPassword(String principle);

    /**
     * 新增用户
     * @param user
     * @return 返回添加成功用户记录数
     */
    int insertUser(User user);

    /**
     * 判断user信息中是哪个字段在表中已经存在
     * @param user
     * @return string[]
     */
    List<String> whichInfoIsExisted(User user);

    /**
     * 登录成功后获取用户序号。
     * @param username email或tel
     * @return 返回user序号
     */
    int getUserNoWhenLoginSuccessfully(String username);
}
