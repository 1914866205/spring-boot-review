package com.soft1851.springbootjpaadvance.service.impl;

import com.soft1851.springbootjpaadvance.dao.TeacherRepository;
import com.soft1851.springbootjpaadvance.model.cascade.Teacher;
import com.soft1851.springbootjpaadvance.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/14 18:08
 * @Version 1.0
 **/
@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherRepository teacherRepository;
    @Override
    public Teacher findTeacherByTeacherIdEquals(Integer teacherId) {
        return teacherRepository.findTeacherByTeacherIdEquals(teacherId);
    }
}
