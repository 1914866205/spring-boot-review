package com.soft1851.springboot.task.scheduling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling   //开启定时
@EnableAsync  //作用于启动类，放置在启动类上开启异步任务注解
public class SpringBootTaskSchedulingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTaskSchedulingApplication.class, args);
    }

}
