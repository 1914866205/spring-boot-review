package com.soft1851.springbootjpaadvance.service.impl;

import com.soft1851.springbootjpaadvance.dao.StudentRepository;
import com.soft1851.springbootjpaadvance.model.cascade.Student;
import com.soft1851.springbootjpaadvance.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/13 18:27
 * @Version 1.0
 **/
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Integer id) {
        return studentRepository.findById(id);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void delete(Student student) {
         studentRepository.delete(student);
    }

    @Override
    public long count() {
        return studentRepository.count();
    }

    @Override
    public boolean existsById(Integer id) {
        return studentRepository.existsById(id);
    }

    @Override
    public Optional<Student> findOne(Student student) {
        return studentRepository.findOne(Example.of(student));
    }

    @Override
    public List<Student> findAll(Student student) {
        return studentRepository.findAll(Example.of(student));
    }

    @Override
    public List<Student> findAll(Student student, Sort sort) {
        return studentRepository.findAll(Example.of(student), sort);
    }

    @Override
    public Page<Student> findAll(Student student, Pageable pageable) {
        return studentRepository.findAll(Example.of(student), pageable);
    }

    @Override
    public long count(Student student) {
        return studentRepository.count(Example.of(student));
    }

    @Override
    public boolean exists(Student student) {
        return studentRepository.exists(Example.of(student));
    }

    @Override
    public void saveAll(List<Student> students) {
        studentRepository.saveAll(students);
    }

    @Override
    public List<Student> findByStudentNameOrderByIdDesc(String name) {
        return studentRepository.findByStudentNameOrderByStudentIdDesc(name);
    }

    @Override
    public Student findByIdhhh(Integer id) {
        return studentRepository.findByIdhhh(id);
    }

    @Override
    public int updateStudentName(String name, Integer id) {
        return studentRepository.updateStudentName(name, id);
    }

    @Override
    public int insertStudent(Integer student_id, String student_name, Integer clazzId, String hometown) {
        return studentRepository.insertStudent(student_id, student_name, clazzId, hometown);
    }

    @Override
    public Student selectById(Integer id) {
        return studentRepository.selectById(id);
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Page<Student> findByName(String name, Pageable pageable) {
        return studentRepository.findByStudentName(name, pageable);
    }

    @Override
    public Slice<Student> findStudentByStudentName(String name, Pageable pageable) {
        return studentRepository.findStudentsByStudentName(name, pageable);
    }

    @Override
    public Page<Student> queryFirst3ByStudentName(String name, Pageable pageable) {
        log.info("name>>>>>>>>>>>>>>>>>>{}", name);
        return studentRepository.queryFirst3ByStudentName(name, pageable);
    }

    @Override
    public Slice<Student> findTop3ByStudentName(String name, Pageable pageable) {
        return studentRepository.findTop3ByStudentName(name, pageable);
    }

    @Override
    public Optional<Student> findOne(Specification<Student> spec) {
        return studentRepository.findOne(spec);
    }

    @Override
    public List<Student> findAll(Specification<Student> spec) {
        return studentRepository.findAll(spec);
    }

    @Override
    public Page<Student> findAll(Specification<Student> spec, Pageable pageable) {
        return studentRepository.findAll(spec, pageable);
    }

    @Override
    public List<Student> findAll(Specification<Student> spec, Sort sort) {
        return studentRepository.findAll(spec, sort);
    }

    @Override
    public long count(Specification<Student> spec) {
        return studentRepository.count(spec);
    }

//    @Override
//    public String findClassNameByStudentId(Integer studentId) {
//        return studentRepository.findClassNameByStudentId(studentId);
//    }

    @Override
    public List<Student> findStudentsByClazzId(Integer clazzId) {
        return studentRepository.findStudentsByClazzId(clazzId);
    }
}
