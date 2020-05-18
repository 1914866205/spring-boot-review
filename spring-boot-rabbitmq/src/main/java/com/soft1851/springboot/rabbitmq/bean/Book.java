package com.soft1851.springboot.rabbitmq.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/18 14:24
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Book implements Serializable {
    private String bookName;
    private String author;
}
