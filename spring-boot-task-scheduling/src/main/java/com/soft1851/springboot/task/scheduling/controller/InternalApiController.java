package com.soft1851.springboot.task.scheduling.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/17 17:26
 * @Version 1.0
 **/
//颞部接口的URL路径以 /internal/作为前置
@Controller
public class InternalApiController {
    @GetMapping("/internal/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello friend");
    }
}
