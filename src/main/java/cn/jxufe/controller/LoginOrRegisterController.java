package cn.jxufe.controller;

import cn.jxufe.bean.User;
import cn.jxufe.exception.PasswordModificationFailedException;
import cn.jxufe.service.LoginService;
import cn.jxufe.util.CheckCodeUtil;
import my_enum.PageName;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * @ClassName: LoginController
 * @author: hsw
 * @date: 2019/4/10 22:51
 * @Description: TODO
 */
@Controller
@RequestMapping(value = "/auth")
public class LoginOrRegisterController {

    private static final String HOME_PAGE_NAME = PageName.HOME_PAGE.getPageUrl();
    private static final String REDIRECT_LOGIN_PAGE_NAME = PageName.LOGIN_PAGE.getPageUrl();

    private final LoginService loginService;
    private final CheckCodeUtil checkCodeUtil;

    @Autowired
    public LoginOrRegisterController(CheckCodeUtil checkCodeUtil, LoginService loginService) {
        this.checkCodeUtil = checkCodeUtil;
        this.loginService = loginService;
    }

    private void addCookieBackLoginRegPage(HttpServletResponse response, String cookieName, String cookieValue) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath("/front_end/loginAndRegister.html");
        response.addCookie(cookie);
    }

    /**
     * 登录方法，这里username可能是邮箱可能是电话，暂且用username代替。
     * 错误信息的cookie在前台显示一遍后便会自动删除！
     * 除登录注册外不使用dto对象传输信息！
     * @param username username
     * @param password password
     * @return string
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        @RequestParam(value = "checkCode") String checkCode,
                        HttpServletResponse response) {

        //如果已经登录了，就不需要跳转了！
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated() || currentUser.isRemembered()) {
            return "redirect:" + HOME_PAGE_NAME;
        }

        //先不判断验证码！
//        if (!checkCodeUtil.codeChecking(checkCode)) {
//            System.out.println("验证码错误！");
//            addCookieBackLoginRegPage(response, "loginStatus", "验证码错误");
//            return "redirect:" + REDIRECT_LOGIN_PAGE_NAME;
//        }
        //这个方法要改名!!!
        Integer userNo = loginService.getUserNoWhenLoginSuccessfully(username);

        if (userNo == null) {
            System.out.println("登录信息有误！或不存在session");
            addCookieBackLoginRegPage(response, "loginStatus", "登录信息有误，用户不存在");
            return "redirect:" + REDIRECT_LOGIN_PAGE_NAME;
        }

        UsernamePasswordToken token = new UsernamePasswordToken(String.valueOf(userNo), password);
        // rememberMe is aways true
        token.setRememberMe(true);
        try {
            currentUser.login(token);
        } catch (IncorrectCredentialsException e) {
            System.out.println("登录密码错误！");
            addCookieBackLoginRegPage(response, "loginStatus", "登录密码错误！");
            return "redirect:" + REDIRECT_LOGIN_PAGE_NAME;
        } catch (AuthenticationException e) {
            System.out.println("登录信息有误！");
            addCookieBackLoginRegPage(response, "loginStatus", "登录信息有误！");
            return "redirect:" + REDIRECT_LOGIN_PAGE_NAME;
        }

        // 之前考虑过将userNo放在session中，然后一个JSESSIONID的cookie就可以管
        // 理了，但考虑到每次都要到redis服务器上去取一个来回，还不如直接从前台传过来，挺小一数据。
        Cookie userNoCookie = new Cookie("userNo", userNo + "");
        // 一个月
        userNoCookie.setMaxAge(2592000);
        userNoCookie.setDomain("localhost");
        userNoCookie.setPath("/");
        response.addCookie(userNoCookie);

        //不过当我们操作一些很有权限的操作时，使用的必须还是session，
        // 比如修改密码，从session只能获取到自己的userNo，就只能修改自己的密码！
        //这里不能有参数false，因为虽然前面创建了验证码session，但验证完删掉了，所以有false得到的session就唯为空！
        Session session = currentUser.getSession(false);
        session.setAttribute("userNo", userNo);

        return "redirect:" + HOME_PAGE_NAME;
    }



    @RequestMapping(value = "/check_code", method = RequestMethod.GET)
    public void checkCode(HttpServletRequest request, HttpServletResponse response) {

        try {
            checkCodeUtil.generateImg(request, response);
        } catch (IOException e) {
            System.out.println("生成验证码出现错误!");
        }
    }

    /**
     * 此属性包括默认的头像图片的url和前缀
     */
    private static final String AVATAR_URL = "";

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam(value = "email", required = false, defaultValue = "") String email,
                           @RequestParam(value = "tel", required = false, defaultValue = "") String tel,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "username") String username,
                           HttpServletResponse response) {
        // 因为是先插入才能生成useNo，所以先插入信息，然后再修改密码！
        User user = new User(username, email, tel, (new Random()).nextInt(10) + ".png", "");
        try {
            if (loginService.registerUser(user, password)) {

                List<String> list = loginService.whichInfoIsExisted(user);

                addCookieBackLoginRegPage(response, "registerStatus", generateReason(list));
                return "redirect:" + REDIRECT_LOGIN_PAGE_NAME + "#green";
            }
        } catch (PasswordModificationFailedException e) {
            addCookieBackLoginRegPage(response, "registerStatus", "密码修改失败导致注册失败！");
            return "redirect:" + REDIRECT_LOGIN_PAGE_NAME + "#green";
        }

        //注册成功，返回登录界面

        addCookieBackLoginRegPage(response, "registerStatus", "注册成功，请登录！");
        return "redirect:" + REDIRECT_LOGIN_PAGE_NAME;
    }

    private String generateReason(List<String> list) {
        StringBuilder reason = new StringBuilder();
        for (String s : list) {
            if ("unknown".equals(s)) {
                return "发生错误，原因不明！";
            }
            reason.append(s).append("、");
        }
        //去除最后一个顿号
        reason.deleteCharAt(reason.length() - 1);
        reason.append("已存在，其值必须唯一");
        return reason.toString();
    }

}