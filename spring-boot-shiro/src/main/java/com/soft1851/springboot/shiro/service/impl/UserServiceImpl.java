package com.soft1851.springboot.shiro.service.impl;

import com.soft1851.springboot.shiro.mapper.UserMapper;
import com.soft1851.springboot.shiro.pojo.User;
import com.soft1851.springboot.shiro.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/20 11:12
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }
}
