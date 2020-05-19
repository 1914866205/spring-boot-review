package com.soft1851.springboot.quartz.job;

import com.soft1851.springboot.quartz.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description @DisallowConcurrentExecution 标记用在实现Job的类上面，意思是不允许并发执行
 * 注意 org.quartz.threadPool.threadCount线程池中线程的数量至少要多个，否则@DisallowConcurrentExecution不生效
 * 加入Job的设置时间间隔为3秒，但Job执行时间为5秒,设置@DisallowConcurrentExecution以后程序会等任务执行完毕后再去执行，否则会在3秒时再启用新的线程执行
 * @Author 涛涛
 * @Date 2020/5/19 10:22
 * @Version 1.0
 **/
@DisallowConcurrentExecution
@Component
@Slf4j
public class DynamicJob implements Job {
    /**
     * 核心方法，Quartz Job真正的执行逻辑
     *
     * @param jobExecutionContext executorContext JobExecutionContext中封装有Quartz运行所需的所有信息
     * @throws JobExecutionException execute()方法只允许抛出JobExecutionException异常
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // JobDetail中的JobDataMap是共同的，从getMergedJobDataMap获取的JobDataMap是全新的对象
        JobDataMap map = jobExecutionContext.getMergedJobDataMap();
        String jarPath = map.getString("jarPath");
        String parameter = map.getString("parameter");
        String vmParam = map.getString("vmParam");
        log.info("Running Job name:{}", map.getString("name"));
        log.info("Running Job description:{}", map.getString("jobDescription"));
        log.info("Running Job group:{}", map.getString("group"));
        log.info(String.format("Running Job cron:%s", map.getString("cronExpression")));
        log.info("Running Job jar path:{}", jarPath);
        log.info("Running Job parameter:{}", parameter);
        log.info("Running Job vmParam:{}", vmParam);
        long startTime = System.currentTimeMillis();
        if (!StringUtils.isEmpty(jarPath)) {
            File jar = new File(jarPath);
            if (jar.exists()) {
                ProcessBuilder processBuilder = new ProcessBuilder();
                processBuilder.directory(jar.getParentFile());
                List<String> commands = new ArrayList<>();
                commands.add("java");
                if (!StringUtils.isEmpty(vmParam)) {
                    commands.add(vmParam);
                }
                commands.add("-jar");
                commands.add(jarPath);
                if (!StringUtils.isEmpty(parameter)) {
                    commands.add(parameter);
                }
                processBuilder.command(commands);
                log.info("Running Job details as follows >>>>>>>>>>>>>>>>>>:");
                log.info("Running Job commands : {}", StringUtil.getListString(commands));
                try {
                    Process process = processBuilder.start();
                    logProcess(process.getInputStream(), process.getErrorStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                throw new JobExecutionException("Job Jar not found >> " + jarPath);
            }
        }
        long endTime = System.currentTimeMillis();
        log.info(">>>>>>>>>>>Running Job has bean completed,cost time:{}ms\n", (endTime - startTime));
    }

    /**
     * 记录Job执行内容
     *
     * @param inputStream
     * @param errorStream
     */
    private void logProcess(InputStream inputStream, InputStream errorStream) throws IOException {
        String inputLine;
        String errorLine;
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
        while (Objects.nonNull(inputLine = inputReader.readLine())) {
            log.info(inputLine);
        }
        while (Objects.nonNull(errorLine = errorReader.readLine())) {
            log.error(errorLine);
        }
    }
}
