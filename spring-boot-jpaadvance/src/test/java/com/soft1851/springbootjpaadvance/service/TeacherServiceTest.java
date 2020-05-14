package com.soft1851.springbootjpaadvance.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class TeacherServiceTest {
    @Resource
    private TeacherService teacherService;

    @Test
    void findTeacherByTeacherIdEquals() {
        log.info("findTeacherByTeacherIdEquals>>>>>>>>>>>>>>>>>>>>>>>{}",teacherService.findTeacherByTeacherIdEquals(1).toString());
    }
}