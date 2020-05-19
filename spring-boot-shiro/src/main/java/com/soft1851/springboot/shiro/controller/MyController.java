package com.soft1851.springboot.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/19 21:30
 * @Version 1.0
 **/
@Controller
public class MyController {
    @RequestMapping({"/", "/index"})
    public String toIndex(Model model) {
        model.addAttribute("msg", "hello,Shiro");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add() {
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String update() {
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("login")
    public String login(String username, String password, Model model) {
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //执行登录的方法，如果没有异常，就说明OK了
        try {
            subject.login(token);
            //登录成功，返回首页
            return "index";
        } catch (UnknownAccountException e) {
            //用户名不存在
            model.addAttribute("msg", "用户名错误");
            return "login";
        } catch (IncorrectCredentialsException e) {
            //密码不存在
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }
}
