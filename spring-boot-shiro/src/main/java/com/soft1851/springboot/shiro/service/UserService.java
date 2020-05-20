package com.soft1851.springboot.shiro.service;

import com.soft1851.springboot.shiro.pojo.User;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/20 11:11
 * @Version 1.0
 **/
public interface UserService {
     User queryUserByName(String name);
}
