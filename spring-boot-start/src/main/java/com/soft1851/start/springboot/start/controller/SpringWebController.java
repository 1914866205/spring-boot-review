package com.soft1851.start.springboot.start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 标准的Spring MVC控制器
 * @Author 涛涛
 * @Date 2020/5/11 19:59
 * @Version 1.0
 **/
@Controller
@RequestMapping("/springWeb")
@ResponseBody
public class SpringWebController {
    @RequestMapping("/hello")
    public String hello(){
        return "<h2>Hello, Spring Boot ! </h2>";
    }
}
