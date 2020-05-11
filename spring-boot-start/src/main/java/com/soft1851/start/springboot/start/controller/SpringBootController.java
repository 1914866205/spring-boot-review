package com.soft1851.start.springboot.start.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 标准的Spring Boot RESTController
 * @Author 涛涛
 * @Date 2020/5/11 19:59
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api")
public class SpringBootController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello,Spring Boot!";
    }

}
