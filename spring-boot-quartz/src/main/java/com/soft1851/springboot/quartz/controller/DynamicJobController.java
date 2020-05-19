package com.soft1851.springboot.quartz.controller;

import com.soft1851.springboot.quartz.controller.dto.ModifyCronDto;
import com.soft1851.springboot.quartz.entity.JobEntity;
import com.soft1851.springboot.quartz.repository.JobEntityRepository;
import com.soft1851.springboot.quartz.service.DynamicJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Objects;
import java.util.Set;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/19 11:08
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/job")
@Slf4j
public class DynamicJobController {
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    @Resource
    private DynamicJobService dynamicJobService;
    @Resource
    private JobEntityRepository jobEntityRepository;

    /**
     * 初始化所有的Job
     */
    @PostConstruct
    public void initialize() {
        try {
            reStartAllJobs();
            log.info("init success");
        } catch (SchedulerException e) {
            log.info("printStackTrace", e);
        }
    }

    /**
     * 重新启动所有的job
     */
    private void reStartAllJobs() throws SchedulerException {
        //只允许一个线程进入操作
        synchronized (log) {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            Set<JobKey> set = scheduler.getJobKeys(GroupMatcher.anyGroup());
            //暂停所有Job
            scheduler.pauseJobs(GroupMatcher.anyGroup());
            //删除从数据库中注册的所有Job
            for (JobKey jobKey : set) {
                scheduler.unscheduleJob(TriggerKey.triggerKey(jobKey.getName(), jobKey.getGroup()));
                scheduler.deleteJob(jobKey);
            }
            //从数据库中注册的所有Job
            for (JobEntity jobEntity : dynamicJobService.loadJobs()) {
                log.info("Job register name:{},group:{},cron:{}", jobEntity.getName(), jobEntity.getJobGroup(), jobEntity.getCron());
                JobDataMap map = dynamicJobService.getJobDataMap(jobEntity);
                JobKey jobKey = dynamicJobService.getJobKey(jobEntity);
                JobDetail jobDetail = dynamicJobService.getJobDetail(jobKey, jobEntity.getDescription(), map);
                if ("OPEN".equals(jobEntity.getStatus())) {
                    scheduler.scheduleJob(jobDetail, dynamicJobService.getTrigger(jobEntity));
                } else {
                    log.info("Job jump name:{},Because {} status is{}", jobEntity.getName(), jobEntity.getName(), jobEntity.getStatus());
                }
            }
        }
    }

    /**
     * 根据id重启某个Job
     *
     * @param id
     * @return
     */
    @RequestMapping("/refresh/{id}")
    public String refresh(@PathVariable Integer id) throws SchedulerException {
        String result;
        JobEntity entity = dynamicJobService.getJobEntityById(id);
        if (Objects.isNull(entity)) {
            return "error:id is not exist";
        }
        synchronized (log) {
            JobKey jobKey = dynamicJobService.getJobKey(entity);
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.pauseJob(jobKey);
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobKey.getName(), jobKey.getGroup()));
            scheduler.deleteJob(jobKey);
            JobDataMap map = dynamicJobService.getJobDataMap(entity);
            JobDetail jobDetail = dynamicJobService.getJobDetail(jobKey, entity.getDescription(), map);

            if ("OPEN".equals(entity.getStatus())) {
                scheduler.scheduleJob(jobDetail, dynamicJobService.getTrigger(entity));
                result = "Refresh Job:" + entity.getName() + "\t jarPath:" + entity.getJarPath() + "success !";
            } else {
                result = "Refresh Job:" + entity.getName() + "\t jarPath:" + entity.getJarPath() + "failed !"
                        + "Because the Job status is" + entity.getStatus();
            }
        }
        return result;
    }

    /**
     * 重启数据库中所以的Job
     *
     * @return
     */
    @RequestMapping("/refresh/all")
    public String refreshAll() {
        String result;
        try {
            reStartAllJobs();
            result = "success";
        } catch (SchedulerException e) {
            result = "exception:" + e.getMessage();
        }
        return "refresh all jobs：" + result;
    }

    /**
     * 修改某个Job执行的Cron
     *
     * @param dto
     * @return
     */
    @PostMapping("/modify")
    public String modifyJob(@RequestBody @Validated ModifyCronDto dto) {
        if (!CronExpression.isValidExpression(dto.getCron())) {
            return "cron is invalid";
        }
        synchronized (log) {
            //获取作业对象
            JobEntity job = dynamicJobService.getJobEntityById(dto.getId());
            if ("OPEN".equals(job.getStatus())) {
                try {
                    //获取JobKey
                    JobKey jobKey = dynamicJobService.getJobKey(job);
                    System.out.println("jobkey"+jobKey);
                    TriggerKey triggerKey = new TriggerKey(jobKey.getName(),jobKey.getGroup());
                    System.out.println("triggerKey"+triggerKey);
                    Scheduler scheduler = schedulerFactoryBean.getScheduler();


                    CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
                    System.out.println("cronTrigger"+cronTrigger);
                    String oldCron = cronTrigger.getCronExpression();
                    if (!oldCron.equalsIgnoreCase(dto.getCron())) {
                        job.setCron(dto.getCron());
                        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(dto.getCron());
                        CronTrigger trigger = TriggerBuilder.newTrigger()
                                .withIdentity(jobKey.getName(), jobKey.getGroup())
                                .withSchedule(cronScheduleBuilder)
                                .usingJobData(dynamicJobService.getJobDataMap(job))
                                .build();
                        scheduler.rescheduleJob(triggerKey, trigger);
                        jobEntityRepository.save(job);
                    }
                } catch (SchedulerException e) {
                    log.error("printStackTrace", e);
                }
            } else {
                log.info("Job jump name : {} , Because {} status is {}", job.getName(), job.getName(), job.getStatus());
                return "modify failure ,because the job is closed";
            }
        }
        return "modify success";
    }
}
