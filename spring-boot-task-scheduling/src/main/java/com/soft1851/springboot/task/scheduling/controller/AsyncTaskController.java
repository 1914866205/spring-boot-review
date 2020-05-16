package com.soft1851.springboot.task.scheduling.controller;

import com.soft1851.springboot.task.scheduling.service.impl.AsyncTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/16 16:01
 * @Version 1.0
 **/
@RestController
@Slf4j
public class AsyncTaskController {
    @Resource
    private AsyncTaskService asyncTaskService;

    @RequestMapping("/asyncTask")
    public String asyncTask() {
        asyncTaskService.asyncTask();
        return "》》》》》》测试异步任务《《《《《《";
    }

}
