package com.soft1851.springboot.task.scheduling.Config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/15 14:14
 * @Version 1.0
 **/
@Configuration
@Slf4j
public class MailConfig {
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;


    @Bean
    public JavaMailSenderImpl mailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+host);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+port);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+username);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+password);
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        return javaMailSender;
    }
}

