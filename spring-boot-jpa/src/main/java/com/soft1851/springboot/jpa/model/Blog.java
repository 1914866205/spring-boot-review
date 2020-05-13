package com.soft1851.springboot.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/13 5:16
 * @Version 1.0
 **/
//@Entity 表明该类 (Blog) 为一个实体类,它默认对应数据库中的表名是blog
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blogId;

    @Column(nullable = false)  //@Column用来标识实体类中属性与数据表中字段的对应关系
    private String blogText;
    @Column(nullable = true, length = 32)
    private String blogSummary;//概况，概要
}
