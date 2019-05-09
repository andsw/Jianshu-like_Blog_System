package cn.jxufe.controller;

import cn.jxufe.bean.User;
import cn.jxufe.service.impl.LoginServiceImpl;
import cn.jxufe.util.CheckCodeUtil;
import cn.jxufe.util.PasswordEncoderUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
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

    private static final String HOME_PAGE_NAME = "http://localhost:8080/front_end/index.html";
    private static final String REDIRECT_LOGIN_PAGE_NAME = "http://localhost:8080/front_end/loginAndRegister.html";


    private final LoginServiceImpl loginService;
    private final CheckCodeUtil checkCodeUtil;
    private final PasswordEncoderUtil passwordEncoderUtil;

    @Autowired
    public LoginOrRegisterController(CheckCodeUtil checkCodeUtil, PasswordEncoderUtil passwordEncoderUtil, LoginServiceImpl loginService) {
        this.checkCodeUtil = checkCodeUtil;
        this.passwordEncoderUtil = passwordEncoderUtil;
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
    public String login(@RequestParam(value = "username", required = false) String username,
                        @RequestParam(value = "password", required = false) String password,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        System.out.println("username : " + username + "\n password : " + password);

        //如果已经登录了，就不需要跳转了！
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated() || currentUser.isRemembered()) {
            return "redirect:" + HOME_PAGE_NAME;
        }

        //先不判断验证码！
//        if (!checkCodeUtil.codeChecking(request)) {
//            System.out.println("验证码错误！");
//            addCookieBackLoginRegPage(response, "loginStatus", "验证码错误");
//            return "redirect:" + REDIRECT_LOGIN_PAGE_NAME;
//        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
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

        int userNo = loginService.getUserNoWhenLoginSuccessfully(username);
        Cookie userNoCookie = new Cookie("user_no", userNo + "");
        userNoCookie.setDomain("localhost");
        userNoCookie.setPath("/front_end/index.html");
        response.addCookie(userNoCookie);
        return "redirect:" + HOME_PAGE_NAME;
    }

    @RequestMapping("/check_code")
    public void checkCode(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("++++++++check code is generating!++++++++++");

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
        String passwordTel = "", passwordEmail = "";
        if ("".equals(email)) {
            passwordTel = passwordEncoderUtil.encode(tel, password);
        } else {
            passwordEmail = passwordEncoderUtil.encode(email, password);
        }
        User user = new User(username, email, tel, (new Random()).nextInt(10) + ".png",
                passwordEmail, passwordTel);
        if (loginService.insertUser(user) == 0) {

            List<String> list = loginService.whichInfoIsExisted(user);

            addCookieBackLoginRegPage(response, "registerStatus", generateReason(list));
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