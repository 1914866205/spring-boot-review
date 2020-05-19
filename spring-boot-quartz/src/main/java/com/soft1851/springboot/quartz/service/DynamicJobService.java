package com.soft1851.springboot.quartz.service;

import com.soft1851.springboot.quartz.controller.dto.ModifyCronDto;
import com.soft1851.springboot.quartz.entity.JobEntity;
import com.soft1851.springboot.quartz.job.DynamicJob;
import com.soft1851.springboot.quartz.repository.JobEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.print.Book;
import java.util.List;

/**
 * @Description 动态任务业务类
 * @Author 涛涛
 * @Date 2020/5/19 10:52
 * @Version 1.0
 **/
@Service
@Slf4j
public class DynamicJobService {
    @Resource
    private JobEntityRepository jobEntityRepository;
    @Resource
    private DynamicJobService dynamicJobService;
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 通过Id获取Job
     *
     * @param id
     * @return
     */
    public JobEntity getJobEntityById(Integer id) {
        return jobEntityRepository.getById(id);
    }

    /**
     * 从数据库中加载 获取到所有Job
     *
     * @return
     */
    public List<JobEntity> loadJobs() {
        return jobEntityRepository.findAll();
    }

    /**
     * 获取JobDataMap.(Job参数对象)
     *
     * @param jobEntity
     * @return
     */
    public JobDataMap getJobDataMap(JobEntity jobEntity) {
        JobDataMap map = new JobDataMap();
        map.put("name", jobEntity.getName());
        map.put("jobGroup", jobEntity.getJobGroup());
        map.put("cronExpression", jobEntity.getCron());
        map.put("parameter", jobEntity.getParameter());
        map.put("jobDescription", jobEntity.getDescription());
        map.put("vmParam", jobEntity.getVmParam());
        map.put("jarPath", jobEntity.getJarPath());
        map.put("status", jobEntity.getStatus());
        return map;
    }

    /**
     * 获取JobDetail,JobDetail是任务的定义，而Job是任务的执行逻辑,JobDetail里会引用一个Job Class定义
     *
     * @param jobKey
     * @param description
     * @param map
     * @return
     */
    public JobDetail getJobDetail(JobKey jobKey, String description, JobDataMap map) {
        return JobBuilder.newJob(DynamicJob.class)
                .withIdentity(jobKey)
                .withDescription(description)
                .setJobData(map)
                .storeDurably()
                .build();
    }

    /**
     * 获取Trigger (Job的触发器，执行规则)
     *
     * @param jobEntity
     * @return
     */
    public Trigger getTrigger(JobEntity jobEntity) {
        return TriggerBuilder.newTrigger()
                .withIdentity(jobEntity.getName(), jobEntity.getJobGroup())
                .withSchedule(CronScheduleBuilder.cronSchedule(jobEntity.getCron()))
                .build();
    }

    /**
     * 获取JobKey,包含Name和Group
     *
     * @param jobEntity
     * @return
     */
    public JobKey getJobKey(JobEntity jobEntity) {
        return JobKey.jobKey(jobEntity.getName(), jobEntity.getJobGroup());
    }

    //监听消息队列
    @RabbitListener(queues = "soft1851.news")
    public void receive(ModifyCronDto dto) {
        System.out.println("************************");
        System.out.println("收到消息");
        System.out.println(dto);
        System.out.println("************************");

        if (!CronExpression.isValidExpression(dto.getCron())) {
            log.info("cron is invalid");
        }
        synchronized (log) {
            //获取作业对象
            JobEntity job = dynamicJobService.getJobEntityById(dto.getId());
            if ("OPEN".equals(job.getStatus())) {
                try {
                    //获取JobKey
                    JobKey jobKey = dynamicJobService.getJobKey(job);
                    System.out.println("jobkey" + jobKey);
                    TriggerKey triggerKey = new TriggerKey(jobKey.getName(), jobKey.getGroup());
                    System.out.println("triggerKey" + triggerKey);
                    Scheduler scheduler = schedulerFactoryBean.getScheduler();


                    CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
                    System.out.println("cronTrigger" + cronTrigger);
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
                log.info("modify failure ,because the job is closed");
            }
        }
        log.info("modify success");
    }

}
