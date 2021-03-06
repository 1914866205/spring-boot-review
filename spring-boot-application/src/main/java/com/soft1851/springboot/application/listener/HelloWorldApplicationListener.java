package com.soft1851.springboot.application.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @Description HelloWorld {@link ApplicationListener} 监听 {@link ContextRefreshedEvent}
 * @Author 涛涛
 * @Date 2020/5/12 20:08
 * @Version 1.0
 **/

@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class HelloWorldApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info(">>>>>>>>>>>>>>>>>>>> refresh applicationContext,Hello World:" + contextRefreshedEvent.getApplicationContext().getId() + ",timestamp:" + contextRefreshedEvent.getTimestamp());
    }
}
