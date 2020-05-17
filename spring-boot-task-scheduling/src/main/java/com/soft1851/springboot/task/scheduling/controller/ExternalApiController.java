package com.soft1851.springboot.task.scheduling.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/17 17:25
 * @Version 1.0
 **/
//外部接口的URL路径以 /external/作为前缀
@Controller
public class ExternalApiController {
    @GetMapping("/external/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello stranger");
    }
}
