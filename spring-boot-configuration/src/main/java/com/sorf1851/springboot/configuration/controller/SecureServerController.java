package com.sorf1851.springboot.configuration.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @Description HTTPS测试
 * @Author 涛涛
 * @Date 2020/5/12 14:09
 * @Version 1.0
 **/
@RestController
@Slf4j
public class SecureServerController {

    @RequestMapping("/secured")
    public String secured() {
        log.info("Inside secured()");
        return "Hello user !!!:" + LocalDateTime.now();
    }

}
