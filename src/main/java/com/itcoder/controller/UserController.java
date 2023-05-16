package com.itcoder.controller;

import com.itcoder.pojo.User;
import com.itcoder.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/User")
public class UserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(String userEmail, String password, HttpServletRequest request){
        //获取前端传送过来的参数
        User loginUser = userService.login(userEmail, password);
        if (loginUser != null){
            request.setAttribute("meg","登录成功！");
            LOGGER.info("用户登录成功，保存到session");
            request.getSession().setAttribute("currentUser",loginUser);
            return "redirect:/pages/main.jsp";
        }
        LOGGER.info("用户登录失败，返回信息");
        request.setAttribute("meg","用户名或者密码错误！登陆失败");
        return "forward:/pages/login.jsp";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().setAttribute("currentUser",null);
        return "redirect:/pages/login.jsp";
    }
}
