package cn.jxufe.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

    /**
     * 登录方法，这里username可能是邮箱可能是电话，暂且用username代替。
     * @param username username
     * @param password password
     * @param rememberMe remember
     * @return string
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        @RequestParam(value = "rememberMe", required = false) Boolean rememberMe,
                        HttpSession session,
                        HttpServletResponse response) {
        System.out.println("\n----------------进入 login method in controller !----------------");

        Subject currentUser = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            if (currentUser.isAuthenticated() || currentUser.isRemembered()) {
                return HOME_PAGE_NAME;
            }
            // rememberMe is aways true
            if (rememberMe != null) {
                token.setRememberMe(rememberMe);
            }
            System.out.println("username : " + username + "\n password : " + password + "\nrememberMe? " + rememberMe);
            currentUser.login(token);

            Cookie usernameCookie = new Cookie("username", username);
            Cookie passwordCookie = new Cookie("password", password);
            response.addCookie(usernameCookie);
            response.addCookie(passwordCookie);

            session.setAttribute("username", username);
            session.setAttribute("test", "inHttpSession!");
        } catch (IncorrectCredentialsException e) {
            System.out.println("登录密码错误！");
            return "redirect:/login.html";
        } catch (UnknownAccountException e) {
            System.out.println("没有该用户！");
            return "redirect:/login.html";
        } catch (AuthenticationException e) {
            System.out.println("登录信息错误！");
            return "redirect:/login.html";
        }

        return HOME_PAGE_NAME;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register() {

        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/admin")
    public String testAdminPerms() {
        return "<h1>admin's page</h1>";
    }

    @ResponseBody
    @RequestMapping(value = "/user")
    public String testUserPerms() {
        return "<h1>user's page</h1>";
    }

}