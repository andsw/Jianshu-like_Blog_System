package cn.jxufe.service.impl;

import cn.jxufe.bean.User;
import cn.jxufe.dao.UserDao;
import cn.jxufe.service.UserInfoService;
import cn.jxufe.util.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;

/**
 * @ClassName: UserInfoServiceImpl
 * @author: hsw
 * @date: 2019/4/30 15:31
 * @Description: TODO
 */
@CacheConfig(cacheNames = "redisCache")
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoderUtil passwordEncoderUtil;

    @Override
    @Cacheable(key = "'user-' + #userNo")
    public User getUserByUserNo(int userNo) {
        return userDao.getUserByUserNo(userNo);
    }

    @Override
    public boolean updatePasswordByUserNo(int currentUserNo, String newPassword) {
        //userDao中获取email和tel的方法赞数先不写service方法！
        String passwordUserNo = passwordEncoderUtil.encode(currentUserNo, newPassword);
        return userDao.updatePasswordByUserNo(passwordUserNo, currentUserNo) == 1;
    }

    @Override
    @Cacheable(key = "'selfSummary-' + #userNo")
    public String getSelfSummaryByUserNo(int userNo) {
        return userDao.getSelfSummaryByUserNo(userNo);
    }

    /**
     * 这里记得要淘汰cache
     * 先试试，cachePut会先执行方法，然后再将执行的数据以键值对的方式存入缓存，可以直接修改！防止了一次cache miss然后查DB放cache操作！
     *
     * 本来是返回boolean直接表示是否运行成功的，但是为了实现上面的功能（cachePut默认使用返回值作为值放入缓存）还是返回String，判断延迟到Controller！
     * @param selfSummary
     * @param currentUserNo
     * @return
     */
    @Override
    @CachePut(key = "'selfSummary-' + #currentUserNo", condition = "#result != null")
    public String updateSelfSummaryByUserNo(String selfSummary, int currentUserNo) {
        // 因为返回null表示操作失败嘛！所以要确保selfSummary不能为null！
        selfSummary = selfSummary == null ? "" : selfSummary;
        return userDao.updateSelfSummaryByUserNo(selfSummary, currentUserNo) == 1 ? selfSummary : null;
    }

    @Override
    @CacheEvict(key = "'user-' + #currentUserNo")
    public boolean updateAccountInfo(int currentUserNo, String avatar, String username, String email, String tel) throws DataAccessException {
        return userDao.updateAccountInfoByUserNo(currentUserNo, avatar, username, email, tel) == 1;
    }

    @Override
    @CacheEvict(key = "'user-' + #currentUserNo")
    public boolean updatePersonalInfo(int currentUserNo, byte gender, String github, String wechatQrImgLink) {
        return userDao.updatePersonalInfoByUserNo(currentUserNo, gender, github, wechatQrImgLink) == 1;
    }

    @Override
    public String whichInfoExisted(String username, String email, String tel) {
        if (userDao.isUsernameExisted(username)) {
            return "用户名已被使用！";
        } else if (userDao.isEmailExisted(email)) {
            return "邮箱已注册！";
        } else if (userDao.isTelExisted(tel)) {
            return "电话已注册！";
        }
        return "未知错误！";
    }
}
