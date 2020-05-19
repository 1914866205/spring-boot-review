package com.soft1851.springboot.quartz.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/19 10:05
 * @Version 1.0
 **/
@Entity
@Data
@Accessors(chain = true)
public class JobEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * job名称
     */
    private String name;
    /**
     * job组名
     */
    private String jobGroup;
    /**
     * 执行的cron
     */
    private String cron;
    /**
     * job的参数
     */
    private String parameter;
    /**
     * job描述信息
     */
    private String description;
    /**
     * vm参数
     */
    private String vmParam;
    /**
     * job的jar路径
     */
    private String jarPath;
    /**
     * job的执行状态,这里我设置为OPEN/CLOSE且只有该值为OPEN才会执行该Job
     */
    private String status;
}