package com.soft1851.springboot.task.scheduling.task;

import com.soft1851.springboot.task.scheduling.service.SendBirthdayCardService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/14 19:38
 * @Version 1.0
 **/

//注：如果这个类不加@EnableScheduling 注解，就需要在启动主类加上
//@Component
public class Scheduler2Task {
    @Resource
    private SendBirthdayCardService sendBirthdayCardService;

//    private DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
//
//    /**
//     * fixedRare:固定频率执行
//     */
//    @Scheduled(fixedRate=3000)
//    public void reportCurrentTime(){
//        System.out.println("现在时间1：" + dft.format(LocalDateTime.now()));
//    }
//
//    /**
//     * fixedDelay:固定延时执行
//     */
//    @Scheduled(fixedDelay = 2000)
//    public void reportCurrentTime2(){
//        System.out.println("现在时间2：" + dft.format(LocalDateTime.now()));
//    }
//
//    /**
//     * 第一次延迟3秒后执行，之后按fixedDelay的规则每2秒执行一次
//     */
//    @Scheduled(initialDelay = 3000,fixedDelay = 2000)
//    public void reportCurrentTime3(){
//        System.out.println("现在时间3："+dft.format(LocalDateTime.now()));
//    }
//
//    /**
//     * 通过cron表达式定义规则
//     */
//    @Scheduled(cron = "0/5 * * * * *")
//    public void reportCurrentTime4(){
//        System.out.println("现在时间4：" + dft.format(LocalDateTime.now()));
//    }
//    @Scheduled(cron = "0 48 10 * * ?")
//    public void reportCurrentTime5() throws MessagingException {
//        sendBirthdayCardService.SendBirthdayCard();
//    }
}
