package com.soft1851.springbootjpaadvance.service;

import com.soft1851.springbootjpaadvance.dao.ClazzRepository;
import com.soft1851.springbootjpaadvance.dao.StudentRepository;
import com.soft1851.springbootjpaadvance.model.cascade.Clazz;
import com.soft1851.springbootjpaadvance.model.cascade.Course;
import com.soft1851.springbootjpaadvance.model.cascade.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ClazzServiceTest {
    @Resource
    private StudentRepository studentRepository;
    @Resource
    private ClazzRepository clazzRepository;
    @Resource
    private ClazzService clazzService;
    @Test
    void findClazzByClazzIdEquals() {
        log.info("findClazzByClazzIdEquals>>>>>>>>>>>>>>>>>>>>>>>"+clazzService.findClazzByClazzIdEquals(1));
    }
    @Test
    void OneToMany(){
        List<Student> studentList = studentRepository.findStudentsByClazzId(1);
        Clazz clazz = clazzRepository.findClazzByClazzIdEquals(1);
        clazz.setStudents(studentList);
        System.out.println(clazz);

    }

    @Test
    void manyToMany(){
        for (int i = 1; i <3 ; i++) {
        System.out.println("*****************");
        List<Student> students = studentRepository.findStudentsByClazzId(i);
        students.forEach(student -> {
            System.out.println(student.getStudentName() + "的课程：");
            List<Course> courses = student.getCourseList();
            courses.forEach(course -> {
                System.out.println("课程id:" + course.getCourseId() + "课程名称：" + course.getCourseName());
            });
        });
        }
    }
}