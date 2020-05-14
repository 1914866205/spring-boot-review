package com.soft1851.springbootjpaadvance.service.impl;

import com.soft1851.springbootjpaadvance.dao.ClazzRepository;
import com.soft1851.springbootjpaadvance.model.cascade.Clazz;
import com.soft1851.springbootjpaadvance.service.ClazzService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/14 18:09
 * @Version 1.0
 **/
@Service
public class ClazzServiceImpl implements ClazzService {
    @Resource
    private ClazzRepository clazzRepository;
    @Override
    public Clazz findClazzByClazzIdEquals(Integer clazzId) {
        return clazzRepository.findClazzByClazzIdEquals(clazzId);
    }
}
