package cn.jxufe.controller;

import cn.jxufe.bean.User;
import cn.jxufe.dto.Result;
import cn.jxufe.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @ClassName: UserInfoController
 * @author: hsw
 * @date: 2019/4/30 15:37
 * @Description: 操作用户信息的controller
 */
@Controller
public class UserInfoController {
    private final UserInfoService userInfoService;

    private final HttpSession session;

    @Autowired
    public UserInfoController(UserInfoService userInfoService, HttpSession session) {
        this.userInfoService = userInfoService;
        this.session = session;
    }

    private Integer getUserNoFromSession() {
        return (Integer) session.getAttribute("userNo");
    }

    @RequestMapping(value = "/users/{userNo}", method = RequestMethod.GET)
    @ResponseBody
    public Result<User> getUserInfoByUserNo(@PathVariable("userNo") Integer userNo) {
        User user = userInfoService.getUserByUserNo(userNo);
        return user == null ? Result.fail("请求数据不存在！") : Result.successWithDataOnly(user);
    }

    @RequestMapping(value = "/users/password", method = RequestMethod.PUT)
    @ResponseBody
    public Result<?> changePassword(@RequestBody String newPassword) {
        Integer userNo = getUserNoFromSession();
        if (userNo == null) {
            return Result.fail("找不到session！");
        }
        return userInfoService.updatePasswordByUserNo(userNo, newPassword) ?
                Result.success("密码修改成功！") : Result.fail("密码修改失败！");
    }

    @RequestMapping(value = "/users/{userNo}/self_summary", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> getSelfSummaryByUserNo(@PathVariable int userNo) {
        String selfSummary = userInfoService.getSelfSummaryByUserNo(userNo);
        return selfSummary == null ? Result.fail("获取简介失败") : Result.successWithDataOnly(selfSummary);
    }

    @RequestMapping(value = "/users/self_summary", method = RequestMethod.PUT)
    @ResponseBody
    public Result<?> updateSelfSummary(@RequestBody String selfSummary) {
        Integer userNo = getUserNoFromSession();

        if (userNo == null) {
            return Result.fail("找不到session！");
        }

        return userInfoService.updateSelfSummaryByUserNo(selfSummary, userNo) == null ?
                Result.fail("个人简介修改失败！") : Result.success("修改个人简介成功！");
    }

    /**
     * 注意！！！前台设置界面必须先判断是否有修改的信息，没有就不用传至后台了！
     * 所以这里必要判断是否全为空！
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/users/account_info", method = RequestMethod.PUT)
    @ResponseBody
    public Result<?> updateAccountInfo(@RequestBody User user, HttpSession session) {
        Integer userNo = (Integer) session.getAttribute("userNo");
        if (userNo == null) {
            return Result.fail("找不到session！");
        }
        return userInfoService.updateAccountInfo(userNo, user.getAvatar(), user.getUsername(), user.getEmail(), user.getTel()) ?
                Result.success("保存成功！") : Result.fail("保存失败！");
    }

    @RequestMapping(value = "/users/personal_info", method = RequestMethod.PUT)
    @ResponseBody
    public Result<?> updatePersonalInfo(@RequestBody User user) {
        System.out.println(user);
        Integer userNo = getUserNoFromSession();
        System.out.println("userNo : " + userNo);
        if (user == null) {
            return Result.fail("user is null!");
        }
        if (userNo == null) {
            return Result.fail("找不到session！");
        }
        return userInfoService.updatePersonalInfo(userNo, user.getGender(), user.getGithub(), user.getWechatQrImgLink()) ?
                Result.success("保存成功！") : Result.fail("保存失败！");
    }

}
