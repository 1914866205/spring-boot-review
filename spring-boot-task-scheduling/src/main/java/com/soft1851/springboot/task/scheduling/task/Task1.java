package com.soft1851.springboot.task.scheduling.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/14 19:34
 * @Version 1.0
 **/
//@Component
public class Task1 {
    private int count = 0;

    /**
     * 设置 process()每隔5秒钟执行一次，并统计执行的次数
     */
    @Scheduled(cron = "*/5 * * * * ?")
    private void process() {
        System.out.println("定时任务正在执行" + (count++));
    }
}
