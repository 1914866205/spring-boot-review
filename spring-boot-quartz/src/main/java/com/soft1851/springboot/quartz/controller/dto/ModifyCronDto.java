package com.soft1851.springboot.quartz.controller.dto;

import lombok.Data;

/**
 * @Description 修改cron表达式的dto类
 * @Author 涛涛
 * @Date 2020/5/19 11:06
 * @Version 1.0
 **/
@Data
public class ModifyCronDto {
    /**
     * 作业的id
     */
    private Integer id;
    /**
     * 作业的cron表达式
     */
    private String cron;
}
