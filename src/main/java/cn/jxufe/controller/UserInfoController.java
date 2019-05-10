package cn.jxufe.controller;

import cn.jxufe.bean.User;
import cn.jxufe.dto.Result;
import cn.jxufe.service.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        return user == null ? Result.fail("请求数据不存在！") : Result.success(user, null);
    }

    @RequestMapping(value = "/users/password", method = RequestMethod.PUT)
    @ResponseBody
    public Result<?> changePassword(@RequestBody String newPassword) {
        System.out.println("new password : " + newPassword);
        Session session = SecurityUtils.getSubject().getSession();
        int myUserNO = (int) session.getAttribute("userNo");
        System.out.println("my own userNO" + myUserNO);
        return userInfoService.updatePasswordByUserNo(myUserNO, newPassword) ?
                Result.success("", "密码修改成功！") : Result.fail("密码修改失败！");
    }

}
