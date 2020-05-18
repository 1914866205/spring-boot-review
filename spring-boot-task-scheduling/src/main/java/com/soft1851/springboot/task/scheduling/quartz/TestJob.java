package com.soft1851.springboot.task.scheduling.quartz;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/18 18:13
 * @Version 1.0
 **/
public class TestJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Console.log("当前时间：{}", DateUtil.now());
    }
}
