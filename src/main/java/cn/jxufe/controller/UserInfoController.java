package cn.jxufe.controller;

import cn.jxufe.bean.User;
import cn.jxufe.service.UserInfoService;
import cn.jxufe.service.impl.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: UserInfoController
 * @author: hsw
 * @date: 2019/4/30 15:37
 * @Description: 操作用户信息的controller
 */
@Controller
public class UserInfoController {
    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @RequestMapping(value = "/users/{userNo}", method = RequestMethod.GET)
    @ResponseBody
    public User getUserInfoByUserNo(@PathVariable Integer userNo, HttpHeaders httpHeaders) {
        User user = userInfoService.getUserByUserNo(userNo);
        if (user == null) {

        }
        return user;
    }

}
