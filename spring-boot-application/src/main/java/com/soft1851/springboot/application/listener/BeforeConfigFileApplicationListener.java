package com.soft1851.springboot.application.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.core.env.Environment;

/**
 * @Description Before {@link ConfigFileApplicationListener} 实现
 * @Author 涛涛
 * @Date 2020/5/12 20:03
 * @Version 1.0
 **/
@Slf4j
public class BeforeConfigFileApplicationListener implements SmartApplicationListener {

    @Override
    public int getOrder(){
        //比ConfigFileApplicationListener 优先级更高
        return ConfigFileApplicationListener.DEFAULT_ORDER - 1;
    }

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        return false;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof ApplicationEnvironmentPreparedEvent) {
            ApplicationEnvironmentPreparedEvent preparedEvent = (ApplicationEnvironmentPreparedEvent) applicationEvent;
            Environment environment = preparedEvent.getEnvironment();
            log.info(">>>>>>>>>>>>>> environment.getProperty(\"name\"):" + environment.getProperty("name"));
        }
    }
}
