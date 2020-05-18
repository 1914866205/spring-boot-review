package com.soft1851.springboot.task.scheduling.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import cn.hutool.http.HttpUtil;
import com.soft1851.springboot.task.scheduling.dao.CorderRepository;
import com.soft1851.springboot.task.scheduling.model.cascade.Corder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/17 19:00
 * @Version 1.0
 **/
//相对于@Repository和@Service,
// @Component用于那些比较中立的类的注解
//@Component
@EnableScheduling //开启定时任务
@EnableAsync //开启异步（多线程）
@Slf4j
public class MultithreadScheduleTask {
    @Resource
    private CorderRepository corderRepository;

    @Async
    @Scheduled(fixedRate = 1000)
    public void first() {
        Console.log("第一个异步定时任务,{},当前线程:{}", DateUtil.now(), Thread.currentThread().getName());
    }

    @Async
    @Scheduled(fixedDelay = 2000)
    public void getCoder(){
        int index = RandomUtil.randomInt(1, 4);
        System.out.println(index);
        Corder corder = corderRepository.findById(index).get();
        download(corder);
    }

    @Async
    public void download(Corder corder) {
        String template = "D:/360MoveData/Users/lenovo/Desktop/{}.jpg";
        String path = StrUtil.format(template, IdUtil.simpleUUID());
        HttpUtil.downloadFile(corder.getAvatar(), FileUtil.file(path));
        getQrCode(corder.getUrl(), path);
    }

    @Async
    public void getQrCode(String content, String logo) {
        String template = "D:/360MoveData/Users/lenovo/Desktop/{}.jpg";
        String file = StrUtil.format(template, IdUtil.simpleUUID());
        QrCodeUtil.generate(content, QrConfig.create().setImg(logo), FileUtil.file(file));
    }
}
