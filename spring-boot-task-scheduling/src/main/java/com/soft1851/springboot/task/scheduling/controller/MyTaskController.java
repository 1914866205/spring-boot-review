package com.soft1851.springboot.task.scheduling.controller;

import com.soft1851.springboot.task.scheduling.dao.CronRepository;
import com.soft1851.springboot.task.scheduling.model.cascade.Cron;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.concurrent.ScheduledFuture;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/16 9:28
 * @Version 1.0
 * @Controller可以接受jsp和html，被视图解析器InternalResourceViewResolver解析
 * @RestController=@Controller+ResponseBody，但不能接受jsp和html，被视图解析器InternalResourceViewResolver解析
 * @ResponseBody加上后可返回JSON，XML或自定义mediaType内容到页面
 **/
@RestController
@RequestMapping(value = "/cron")
@Slf4j
public class MyTaskController {
    @Resource
    protected CronRepository cronRepository;

    /**
     * 线程池任务调度，能够开启线程池进行任务调度
     */
    @Resource
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;


    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    /**
     * ThreadPoolTaskScheduler.schedule()方法会创建一个定时计划ScheduledFuture,
     * 在这个方法需要添加两个参数，Runnable(线程接口)和CronTrigger(定时任务触发器)
     */
    ScheduledFuture<?> future;

    @GetMapping("/start")
    public String start(@Param("cronId") int cronId) {
        log.info("》》》》》》》》》" + cronId + "<<<<<<<<<<");
        String cron = cronRepository.findCronByCronIdEquals(cronId).getCron();
        log.info(">>>>>>>>>>>>>>>" + cron);
        future = threadPoolTaskScheduler.schedule(new MyRunnable(), new CronTrigger(cron));
        log.info("定时任务启动");
        return "定时任务启动";
    }

    @GetMapping("/stop")
    public String stopCron() {
        if (future != null) {
            future.cancel(true);
        }
        log.info("定时任务停止");
        return "定时任务停止";
    }

    @PostMapping("/change")
    public String updateCron(@RequestBody Cron cron) {
        stopCron();
        System.out.println(cron);
        future = threadPoolTaskScheduler.schedule(new MyRunnable(), new CronTrigger(cron.getCron()));
        cronRepository.updateCron(cron.getCron(), cron.getCronId());
        return "修改定时任务设置";
    }
    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            log.info("我的定时任务：" + LocalDateTime.now());
        }
    }
}
