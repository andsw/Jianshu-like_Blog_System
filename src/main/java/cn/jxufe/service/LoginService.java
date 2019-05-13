package cn.jxufe.service;

import cn.jxufe.bean.User;
import cn.jxufe.exception.PasswordModificationFailedException;

import java.util.List;

/**
 * @ClassName: LoginService
 * @author: hsw
 * @date: 2019/4/3 22:00
 * @Description: TODO
 */
public interface LoginService {
    /**
     * 根据登录时用户名获取userNo,然后再通过userNo生成或获取密码！
     * @param principle
     * @return
     */
    Integer getUserNoByPrinciple(String principle);

    /**
     * 0
     *
     * @param userNo 。
     * @return
     */
    String getPasswordByUserNo(int userNo);

    /**
     * 注册用户
     * @param user
     * @param realPassword
     * @return 返回结果
     * @throws PasswordModificationFailedException
     */
    boolean registerUser(User user, String realPassword) throws PasswordModificationFailedException;

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
    Integer getUserNoWhenLoginSuccessfully(String username);
}
