package com.soft1851.springboot.task.scheduling.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/16 15:57
 * @Version 1.0
 **/
@Service
@Slf4j
public class AsyncTaskService {
    /**
     * @Async  ： 作用于方法上，有次注解的方法将会异步执行，
     * 即此方法未执行完，但是调用的方法可以返回结果
     */
    @Async
    public void asyncTask() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("处理数据中");
    }
}
