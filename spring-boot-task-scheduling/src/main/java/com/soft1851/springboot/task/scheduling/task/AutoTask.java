package com.soft1851.springboot.task.scheduling.task;

import com.soft1851.springboot.task.scheduling.dao.CronRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/16 8:31
 * @Version 1.0
 **/
//注：如果这个类不加@EnableScheduling 注解，就需要在启动主类加上
@Slf4j
//@Component
public class AutoTask implements SchedulingConfigurer {
    @Resource
    protected CronRepository cronRepository;
    private String newCron;
    private Integer cronId = 1;
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> {
//                    log.info("执行定时任务: " + LocalDateTime.now().toLocalTime())
                },
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String cron = cronRepository.findCronByCronIdEquals(cronId).getCron();
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {
                        log.info("cron不能为空");
                    }
                    //2.3 返回执行周期
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }


//    @Override
//    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
//        scheduledTaskRegistrar.addTriggerTask(this::process,
//                triggerContext -> {
//                    //在这里先查询id为1的cron
//                    String cron = cronRepository.findCronByCronIdEquals(1).getCron();
//                    log.info(cron);
//                    if (cron.isEmpty()) {
//                        log.info("cron为空");
//                    }
//                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
//                });
//    }
//    private void process(){
//        log.info("从数据库读取cron..........");
//    }


}
