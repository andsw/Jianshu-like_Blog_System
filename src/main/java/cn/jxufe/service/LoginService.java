package cn.jxufe.service;

import org.springframework.stereotype.Repository;

import javax.security.auth.login.AccountNotFoundException;

/**
 * @ClassName: LoginService
 * @author: hsw
 * @date: 2019/4/3 22:00
 * @Description: TODO
 */
public interface LoginService {
    /**
     * 0
     * @param principle
     * @return
     */
    String getPassword(String principle);
}
