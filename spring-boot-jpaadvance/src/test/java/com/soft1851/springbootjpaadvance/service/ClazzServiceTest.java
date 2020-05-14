package com.soft1851.springbootjpaadvance.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ClazzServiceTest {

    @Resource
    private ClazzService clazzService;
    @Test
    void findClazzByClazzIdEquals() {
        log.info("findClazzByClazzIdEquals>>>>>>>>>>>>>>>>>>>>>>>"+clazzService.findClazzByClazzIdEquals(1));
    }
}