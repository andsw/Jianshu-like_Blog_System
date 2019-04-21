package cn.jxufe.controller;

import cn.jxufe.bean.User;
import cn.jxufe.util.CheckCodeUtil;
import cn.jxufe.util.PasswordEncoderUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

/**
 * @ClassName: LoginController
 * @author: hsw
 * @date: 2019/4/10 22:51
 * @Description: TODO
 */
@Controller
@RequestMapping(value = "/user")
public class LoginOrRegisterController {

    public static final String HOME_PAGE_NAME = "home";

    private final CheckCodeUtil checkCodeUtil;
    private final PasswordEncoderUtil passwordEncoderUtil;

    @Autowired
    public LoginOrRegisterController(CheckCodeUtil checkCodeUtil, PasswordEncoderUtil passwordEncoderUtil) {
        this.checkCodeUtil = checkCodeUtil;
        this.passwordEncoderUtil = passwordEncoderUtil;
    }

    /**
     * 登录方法，这里username可能是邮箱可能是电话，暂且用username代替。
     * 错误信息的cookie在前台显示一遍后便会自动删除！
     * @param username username
     * @param password password
     * @return string
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "username", required = false) String username,
                        @RequestParam(value = "password", required = false) String password,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        System.out.println("\n----------------进入 login method in controller !----------------");
        System.out.println("username : " + username + "\n password : " + password);

        //如果已经登录了，就不需要二维码跳转了！
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated() || currentUser.isRemembered()) {
            return HOME_PAGE_NAME;
        }

        if (!checkCodeUtil.codeChecking(request)) {
            System.out.println("验证码错误！");
            Cookie cookie = new Cookie("loginStatus", "验证码错误！");
            cookie.setPath("/pin/copy.html");
            response.addCookie(cookie);
            return "redirect:http://localhost:63342/pin/copy.html?_ijt=4di1pnl2l6tpun3dot2kba5pfb";
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // rememberMe is aways true
        token.setRememberMe(true);
        try {
            currentUser.login(token);
        } catch (IncorrectCredentialsException e) {
            System.out.println("登录密码错误！");
            Cookie cookie = new Cookie("loginStatus", "登录密码错误！");
            cookie.setPath("/pin/copy.html");
            response.addCookie(cookie);
            return "redirect:http://localhost:63342/pin/copy.html?_ijt=4di1pnl2l6tpun3dot2kba5pfb";
        } catch (AuthenticationException e) {
            System.out.println("登录信息有误！");
            Cookie cookie = new Cookie("loginStatus", "登录信息有误！");
            cookie.setPath("/pin/copy.html");
            response.addCookie(cookie);
            return "redirect:http://localhost:63342/pin/copy.html?_ijt=4di1pnl2l6tpun3dot2kba5pfb";
        }

        return HOME_PAGE_NAME;
    }

    @RequestMapping("/checkCode")
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
    public String register(@RequestParam(value = "email", required = false) String email,
                           @RequestParam(value = "tel", required = false) String tel,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "username") String username) {
        String passwordTel = "", passwordEmail = "";
        if (email == null) {
            passwordTel = passwordEncoderUtil.encode(tel, password);
        } else {
            passwordEmail = passwordEncoderUtil.encode(email, password);
        }
        User user = new User(username, email, tel, (new Random()).nextInt(10) + ".png",
                passwordEmail, passwordTel);
        return null;
    }

}