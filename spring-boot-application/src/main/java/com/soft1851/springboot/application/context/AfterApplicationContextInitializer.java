package com.soft1851.springboot.application.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/12 19:55
 * @Version 1.0
 **/
@Slf4j
public class AfterApplicationContextInitializer <C extends ConfigurableApplicationContext> implements ApplicationContextInitializer<C>, Ordered {

    @Override
    public void initialize(C applicationCotext) {
        log.info("After Application.id=" + applicationCotext.getId());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
