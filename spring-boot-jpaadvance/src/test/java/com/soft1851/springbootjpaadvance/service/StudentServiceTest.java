package com.soft1851.springbootjpaadvance.service;


import com.soft1851.springbootjpaadvance.model.cascade.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class StudentServiceTest {

    @Resource
    private StudentService studentService;

    @Test
    public void findAll() {
        log.info("findAll>>>>>>>>>>>>>>>{}", studentService.findAll());
    }

    @Test
    public void findById() {
        log.info("findById>>>>>>>>>>>>>>>{}", studentService.findById(1));
    }

    @Test
    public void save() {
        //这里即使给  id  也会被主键策略给冲掉
        //如果数据库本身有数据，且 id 不是主键策略生成的值，会报错  Duplicate entry '1' for key 'student.UK_90jf9j1kpcwnwqybnejoqiprf'
        Student student = Student.builder().studentName("陶然然")
                .hometown("江苏宜兴").clazzId(1).birthday(LocalDateTime.now()).build();
        log.info(student.toString());
        studentService.save(student);
        log.info("save>>>>>>>>>>>>>>>{}", studentService.findById(3));
    }

    @Test
    public void delete() {
        Student student = Student.builder().studentId(1).studentName("陶然然")
                .hometown("江苏宜兴").clazzId(1).birthday(LocalDateTime.now()).build();
        log.info("findAll>>>>>>>>>>>>>>>{}", studentService.findAll());
        studentService.delete(student);
        log.info("findAll>>>>>>>>>>>>>>>{}", studentService.findAll());
    }

    @Test
    public void count() {
        log.info("剩余数量count>>>>>>>>>>>>>>>{}", studentService.count());
    }


    @Test
    public void existsById() {
        log.info("existsById{}>>>>>>>>>>>>>>>>>>>>>", studentService.existsById(2));
    }

    @Test
    public void findOne() {
        Student student = Student.builder().studentId(2).build();
        log.info("findOne>>>>>>>>>>>>>>{}", studentService.findOne(student));
    }

    @Test
    public void findAll1(){
        Student student = Student.builder().clazzId(1).build();
//        Sort sort = new Sort();
        log.info("findAll>>>>>>>>>>>>>>{}",studentService.findAll(student,Sort.Direction.DESC,"student_id");
    }

    @Test
    public void findAll2(){
        Page<Student> pageInfo = studentService.findAll(PageRequest.of(1, 2, Sort.Direction.ASC, "student_id"));
        log.info("findAll>>>>>>>>>>>>>>{}", pageInfo);
    }

    @Test
    public void count2(){
        log.info("count2>>>>>>>>>>>>>>>>>>{}",studentService.count(Student.builder().clazzId(1).build()));
    }


    @Test
    public void exists(){
        log.info("exists>>>>>>>>>>>>>>>>>>{}",studentService.exists(Student.builder().clazzId(1).build()));
    }

    @Test
    public void saveAll(){
        Student student = Student.builder().studentName("陶然然").hometown("江苏宜兴").clazzId(1).birthday(LocalDateTime.now()).build();
        Student student2 = Student.builder().studentName("陶然然2").hometown("江苏宜兴").clazzId(1).birthday(LocalDateTime.now()).build();
        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);
        studentService.saveAll(students);
        log.info("saveAll>>>>>>>>>>>>>>>>>>{}",studentService.findAll());
    }
    @Test
    public void findByStudentNameOrderByStudentIdDesc(){
        log.info("findByStudentNameOrderByStudentIdDesc>>>>>>>>>>>>>>>>>>{}",studentService.findByStudentNameOrderByIdDesc("陶然然"));
    }

    @Test
    public void findByIdhhh() {
        log.info("findByIdhhh>>>>>>>>>>>>>>>>>>{}",studentService.findByIdhhh(2));
    }

    @Test
    public void updateStudentName() {
        log.info("updateStudentName>>>>>>>>>>>>>>>>>>{}",studentService.updateStudentName("陶然",2));
        log.info("findByIdhhh>>>>>>>>>>>>>>>>>>{}",studentService.findByIdhhh(2));

    }

    @Test
    public void insertStudent() {
        studentService.insertStudent(100, "哈哈哈哈", 1, "江苏徐州");
        log.info("findByIdhhh>>>>>>>>>>>>>>>>>>{}",studentService.findByIdhhh(100));
    }

    @Test
    public void selectById() {

        log.info("selectByName>>>>>>>>>>>>>>>>>>{}",studentService.selectById(2));
    }
}