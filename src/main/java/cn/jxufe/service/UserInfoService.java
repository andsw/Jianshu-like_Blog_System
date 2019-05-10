package cn.jxufe.service;

import cn.jxufe.bean.User;

/**
 * @ClassName: UserInfoService
 * @author: hsw
 * @date: 2019/4/30 15:32
 * @Description: 对user信息的主要操作业务类！
 */
public interface UserInfoService {
    /**
     * userNo获取用户信息！
     * @param userNo
     * @return
     */
    User getUserByUserNo(int userNo);

    /**
     * 修改密码
     * @param userNo
     * @param newPassword
     * @return
     */
    boolean updatePasswordByUserNo(int userNo, String newPassword);
}
