package cn.jxufe.controller;

import cn.jxufe.bean.User;
import cn.jxufe.dto.Result;
import cn.jxufe.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Result<User> getUserInfoByUserNo(@PathVariable("userNo") Integer userNo) {
        User user = userInfoService.getUserByUserNo(userNo);

        return user == null ? Result.fail() : Result.success(user);
    }

}
