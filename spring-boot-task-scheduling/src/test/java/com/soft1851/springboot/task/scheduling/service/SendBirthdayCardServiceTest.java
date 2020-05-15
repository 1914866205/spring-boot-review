package com.soft1851.springboot.task.scheduling.service;

import com.soft1851.springboot.task.scheduling.dao.StudentRepository;
import com.soft1851.springboot.task.scheduling.model.cascade.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@EnableScheduling
class SendBirthdayCardServiceTest {

    @Resource
    private SendBirthdayCardService sendBirthdayCardService;
    @Resource
    private StudentRepository studentRepository;

    @Test
    void insertStudent() {
        //插入一条学生数据
        Student student = Student.builder().studentName("涛涛").email("1914866205@qq.com").hometown("江苏徐州").birthday(LocalDateTime.of(1999, 5, 27, 7, 20)).build();
        studentRepository.save(student);
    }

    @Test
    void sendBirthdayCard() throws MessagingException {
        sendBirthdayCardService.SendBirthdayCard();
    }
}