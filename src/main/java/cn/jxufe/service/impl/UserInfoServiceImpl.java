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
    @Cacheable(key = "'userInfo-' + #userNo")
    public User getUserByUserNo(int userNo) {
        return userDao.getUserByUserNo(userNo);
    }

    @Override
    public boolean updatePasswordByUserNo(int userNo, String newPassword) {
        //userDao中获取email和tel的方法赞数先不写service方法！
        String passwordUserNo = passwordEncoderUtil.encode(userNo, newPassword);
        return userDao.updatePasswordByUserNo(passwordUserNo, userNo) == 1;
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
     * @param userNo
     * @return
     */
    @Override
    @CachePut(key = "'selfSummary-' + #userNo", condition = "#result != null")
    public String updateSelfSummaryByUserNo(String selfSummary, int userNo) {
        // 因为返回null表示操作失败嘛！所以要确保selfSummary不能为null！
        selfSummary = selfSummary == null ? "" : selfSummary;
        return userDao.updateSelfSummaryByUserNo(selfSummary, userNo) == 1 ? selfSummary : null;
    }

    @Override
    @CacheEvict(key = "'userInfo-' + #userNo")
    public boolean updateAccountInfo(int userNo, String avatar, String username, String email, String tel) {
        return userDao.updateAccountInfoByUserNo(userNo, avatar, username, email, tel) == 1;
    }

    @Override
    @CacheEvict()
    public boolean updatePersonalInfo(int userNo, byte gender, String github, String wechatQrImgLink) {
        return userDao.updatePersonalInfoByUserNo(userNo, gender, github, wechatQrImgLink) == 1;
    }
}
