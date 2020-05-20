package com.soft1851.springboot.shiro.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
    @Resource
    private UserService userService;
    @Test
    void queryUserByName() {
        System.out.println(userService.queryUserByName("ntt"));
    }
}