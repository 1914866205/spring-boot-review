package com.soft1851.springbootjpaadvance.dao;

import com.soft1851.springbootjpaadvance.model.cascade.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/14 18:03
 * @Version 1.0
 **/
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    /**
     * 根据id查询Teacher的所有信息
     * @param teacherId
     * @return
     */
    Teacher findTeacherByTeacherIdEquals(Integer teacherId);

}
