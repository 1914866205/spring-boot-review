package com.soft1851.springboot.shiro.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/20 11:02
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private int id;
    private String name;
    private String pwd;
    private String perms;
}
