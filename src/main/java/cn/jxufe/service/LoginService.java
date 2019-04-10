package cn.jxufe.service;

import org.springframework.stereotype.Repository;

/**
 * @ClassName: LoginService
 * @author: hsw
 * @date: 2019/4/3 22:00
 * @Description: TODO
 */
public interface LoginService {
    String getPassword(String principle);
}
