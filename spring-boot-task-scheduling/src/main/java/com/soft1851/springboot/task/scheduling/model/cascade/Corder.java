package com.soft1851.springboot.task.scheduling.model.cascade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/17 19:04
 * @Version 1.0
 **/
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Corder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name",length = 32)
    private String name;
    @Column(name="avatar")
    private String avatar;
    @Column(name="url")
    private String url;
}
