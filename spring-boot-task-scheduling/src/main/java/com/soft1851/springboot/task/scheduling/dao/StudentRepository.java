package com.soft1851.springboot.task.scheduling.dao;

import com.soft1851.springboot.task.scheduling.model.cascade.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/15 16:10
 * @Version 1.0
 **/
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
