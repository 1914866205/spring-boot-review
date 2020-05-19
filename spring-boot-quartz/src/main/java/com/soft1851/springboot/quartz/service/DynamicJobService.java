package com.soft1851.springboot.quartz.service;

import com.soft1851.springboot.quartz.entity.JobEntity;
import com.soft1851.springboot.quartz.job.DynamicJob;
import com.soft1851.springboot.quartz.repository.JobEntityRepository;
import org.quartz.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 动态任务业务类
 * @Author 涛涛
 * @Date 2020/5/19 10:52
 * @Version 1.0
 **/
@Service
public class DynamicJobService {
    @Resource
    private JobEntityRepository jobEntityRepository;

    /**
     * 通过Id获取Job
     * @param id
     * @return
     */
    public JobEntity getJobEntityById(Integer id) {
        return jobEntityRepository.getById(id);
    }

    /**
     * 从数据库中加载 获取到所有Job
     * @return
     */
    public List<JobEntity> loadJobs() {
        return jobEntityRepository.findAll();
    }

    /**
     * 获取JobDataMap.(Job参数对象)
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
     * @param jobEntity
     * @return
     */
    public JobKey getJobKey(JobEntity jobEntity) {
        return JobKey.jobKey(jobEntity.getName(), jobEntity.getJobGroup());
    }


}
