package com.soft1851.springboot.application.bootstrap;

import com.soft1851.springboot.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/12 17:59
 * @Version 1.0
 **/
@Slf4j
@SpringBootApplication
public class SpringApplicationEventBootstrap {
    public static void main(String[] args) {
        //创建上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册应用实践监听器
        context.addApplicationListener(applicationEvent -> {
            log.info("监听到事件：" + applicationEvent);
        });
        //启动上下文
        context.refresh();
        //发送事件
        context.publishEvent("HelloWorld");
        context.publishEvent("2020");
        context.publishEvent(new ApplicationEvent("史上最长假期") {

        });
        //关闭上下文
        context.close();
    }
}
