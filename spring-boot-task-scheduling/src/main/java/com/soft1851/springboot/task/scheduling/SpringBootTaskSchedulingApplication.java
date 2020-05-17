package com.soft1851.springboot.task.scheduling;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableEncryptableProperties//加入注解，使得加密生效
@SpringBootApplication
@EnableScheduling   //开启定时
@EnableAsync  //作用于启动类，放置在启动类上开启异步任务注解
public class SpringBootTaskSchedulingApplication {
    public static void main(String[] args) {
//        System.setProperty("jasypt.encryptor.password", "salt");

        SpringApplication.run(SpringBootTaskSchedulingApplication.class, args);
    }

}
