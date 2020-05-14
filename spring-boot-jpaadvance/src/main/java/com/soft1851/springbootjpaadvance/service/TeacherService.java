package com.soft1851.springbootjpaadvance.service;

import com.soft1851.springbootjpaadvance.model.cascade.Teacher;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/14 18:07
 * @Version 1.0
 **/
public interface TeacherService {
    /**
     * 根据id查询Teacher的所有信息
     * @param teacherId
     * @return
     */
    Teacher findTeacherByTeacherIdEquals(Integer teacherId);

}
