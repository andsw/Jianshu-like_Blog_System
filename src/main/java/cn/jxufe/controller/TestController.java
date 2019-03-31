package cn.jxufe.controller;

import cn.jxufe.bean.User;
import cn.jxufe.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: TestController
 * @author: hsw
 * @date: 2019/3/30 19:26
 * @Description: TODO
 */
@Controller
@RequestMapping("/test_controller")
public class TestController {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @ResponseBody
    @RequestMapping("/test_spring_mvc")
    public String  testSpringMVC(){
        return "hello world!";
    }

    @ResponseBody
    @RequestMapping("/test_mybatis")
    public String testMybatis(){
        System.out.println("finding all");
        System.out.println(userDao.getAllUsers().get(1));
        return "mm";
    }
}
