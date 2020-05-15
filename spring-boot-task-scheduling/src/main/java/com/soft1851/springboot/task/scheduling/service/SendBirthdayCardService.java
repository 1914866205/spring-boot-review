package com.soft1851.springboot.task.scheduling.service;

import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.MessagingException;
import java.util.Map;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/15 16:14
 * @Version 1.0
 **/
public interface SendBirthdayCardService {
    void SendBirthdayCard() throws MessagingException;
}
