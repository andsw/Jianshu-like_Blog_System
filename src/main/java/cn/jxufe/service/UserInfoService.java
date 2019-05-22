package cn.jxufe.service;

import cn.jxufe.bean.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.dao.DataAccessException;

import java.sql.SQLIntegrityConstraintViolationException;

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

    /**
     * 获取简介
     * @param userNo
     * @return
     */
    String getSelfSummaryByUserNo(int userNo);

    /**
     * 修改个人简介
     * @param selfSummary
     * @param userNo
     * @return 修改成功与否
     */
    String updateSelfSummaryByUserNo(String selfSummary, int userNo);

    /**
     * 更新账户信息
     * @param userNo
     * @param avatar
     * @param username
     * @param email
     * @param tel
     * @return
     */
    boolean updateAccountInfo(int userNo, String avatar, String username, String email, String tel) throws DataAccessException;

    /**
     * 更新个人信息
     * @param userNo
     * @param gender
     * @param github
     * @param wechatQrImgLink
     * @return
     */
    boolean updatePersonalInfo(int userNo, byte gender, String github, String wechatQrImgLink);

    /**
     * 判断哪个字段值发生重复
     * @param username
     * @param email
     * @param tel
     * @return
     */
    String whichInfoExisted(String username, String email, String tel);
}
