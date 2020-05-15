package com.soft1851.springboot.task.scheduling.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MailServiceTest {

    @Resource
    private MailService mailService;
    @Test
    void sendSimpleTextMail() {
        String to = "1914866205@qq.com";
        String subject = "Springboot 发送简单文本邮件";
        String content = "<h3>第一封Springboot 简单文本邮件</h3>";
        mailService.sendSimpleTextMail(to, subject, content);
    }

    @Test
    void sendHtmlMail() throws MessagingException {
        String to = "1914866205@qq.com";
        String subject = "Springboot 发送HTML邮件";
        String content = "<h1>第二封Springboot HTML邮件</h3>";
        mailService.sendHtmlMail(to, subject, content);
    }

    @Test
    void sendAttachmentMail() throws MessagingException {
        String to = "1914866205@qq.com";
        String subject = "Springboot 发送附件邮件";
        String content = "<h1>第三封Springboot 附件邮件</h3>";
        String filePath = "pom.xml";
        mailService.sendAttachmentMail(to, subject, content, filePath);
    }

    @Test
    void sendImgMail() throws MessagingException {
        String to = "1914866205@qq.com";
        String subject = "Springboot 发送图片邮件";
        String content = "<h2>第四封Springboot 图片邮件</h2><br/><img src=\"cid:img01\"/>";
        String imgPath = "D:\\360MoveData\\Users\\lenovo\\Desktop\\java\\笔记\\五月\\5月15日\\1.png";
        Map<String, String> imgMap = new HashMap<>();
        //以键值对的形式存入
        imgMap.put("img01", imgPath);
        mailService.sendImgMail(to, subject, content, imgMap);
    }
}