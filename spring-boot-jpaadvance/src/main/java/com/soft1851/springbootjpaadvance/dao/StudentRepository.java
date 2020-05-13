package com.soft1851.springbootjpaadvance.dao;

import com.soft1851.springbootjpaadvance.model.cascade.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.management.RuntimeErrorException;
import java.util.List;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/13 17:36
 * @Version 1.0
 **/
public interface StudentRepository extends JpaRepository<Student, Integer> {
    /**
     * 根据名字模糊查询，按照id降序
     *
     * @param name
     * @return
     */
    List<Student> findByStudentNameOrderByStudentIdDesc(String name);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    //?1应该是代表第一个参数
    @Query("select s from Student s where s.studentId = ?1")
    Student findByIdhhh(Integer id);

    /**
     * 修改
     *
     * @param name
     * @param id
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeErrorException.class)
    @Query(value = "update student set student_name=?1 where student_id=?2", nativeQuery = true)
    int updateStudentName(String name, Integer id);

    @Transactional(rollbackFor = RuntimeException.class)
    @Modifying
    @Query(value="insert into student (student_id,student_name,clazz_id,hometown) values(:student_id,:student_name,:clazz_id,:hometown)",nativeQuery = true)
    int insertStudent(@Param("student_id") Integer student_id, @Param("student_name") String student_name, @Param("clazz_id") Integer clazzId, @Param("hometown") String hometown);


    Student selectById(Integer id);
}
