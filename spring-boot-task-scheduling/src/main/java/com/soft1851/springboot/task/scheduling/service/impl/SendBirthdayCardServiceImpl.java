package com.soft1851.springboot.task.scheduling.service.impl;

import com.soft1851.springboot.task.scheduling.dao.StudentRepository;
import com.soft1851.springboot.task.scheduling.model.cascade.Student;
import com.soft1851.springboot.task.scheduling.service.MailService;
import com.soft1851.springboot.task.scheduling.service.SendBirthdayCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/15 16:18
 * @Version 1.0
 **/
@Slf4j
@Service
public class SendBirthdayCardServiceImpl implements SendBirthdayCardService {
    @Value("${spring.mail.username}")
    private String from;
    @Resource
    private MailService mimeMessage;
    @Resource
    private StudentRepository studentRepository;
    /**
     * fixedRare:固定频率执行
     */
    @Override
    public void SendBirthdayCard() throws MessagingException {
        //获取当前时间
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = localDateTime.toString().substring(5, 10);
        log.info("当前日期》》》》》》》》》》》》》{}", date);
        //获取所有学生
        List<Student> studentList = studentRepository.findAll();
        for (Student student : studentList) {
            log.info("当前学生生日》》》》》》》》》》》》》{}", student.getBirthday().toString().substring(5, 10));
            if (date.equals(student.getBirthday().toString().substring(5, 10))) {
                //发送邮件
                String to = student.getEmail();
                String subject = "祝你生日快乐！";
                String content = "<h2>祝你生日快乐！</h2><br/><img src=\"cid:img01\"/>";
                String imgPath = "src/main/resources/static/img/birthday.jpg";
                Map<String, String> imgMap = new HashMap<>();
                //以键值对的形式存入
                imgMap.put("img01", imgPath);
                mimeMessage.sendImgMail(to, subject, content, imgMap);
            }
        }

    }
}
