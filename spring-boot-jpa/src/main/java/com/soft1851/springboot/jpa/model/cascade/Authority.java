package com.soft1851.springboot.jpa.model.cascade;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/13 7:21
 * @Version 1.0
 **/
@Data
@Entity
public class Authority {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    /**
     * 权限名
     */
    @Column(nullable = false)
    private String name;


    /**
     * 拥有权限的用户列表
     */
    @ManyToMany(mappedBy = "authorityList")
    private List<User> userList;
}
