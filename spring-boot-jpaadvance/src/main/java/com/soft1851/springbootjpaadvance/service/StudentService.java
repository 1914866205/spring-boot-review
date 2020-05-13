package com.soft1851.springbootjpaadvance.service;

import com.soft1851.springbootjpaadvance.model.cascade.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.management.RuntimeErrorException;
import java.util.List;
import java.util.Optional;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/13 18:29
 * @Version 1.0
 **/
public interface StudentService {

    /**
     * 查找所有学生信息
     * @return
     */
    List<Student> findAll();

    /**
     * 根据id查找学生
     *
     * @return
     */
    Optional<Student> findById(Integer id);

    /**
     * 添加学生
     * @param student
     */
    void save(Student student);


    /**
     * 删除学生
     * @param student
     * @return
     */
    void delete(Student student);


    /**
     * 查询学生总数
     * @return
     */
    long count();


    /**
     * 判断该id是否存在
     * @param id
     * @return
     */
    boolean existsById(Integer id);


    /**
     * 根据实例，查找一个对象
     *
     * @param student
     * @return
     */
    Optional<Student> findOne(Student student);
    /**
     * 根据实例 查找一批对象
     * @param student
     * @return
     */
    List<Student> findAll(Student student);

    /**
     * 根据实例查找一批对象，且排序
     * @param student
     * @param sort
     * @return
     */
    List<Student> findAll(Student student, Sort sort);

    /**
     * 根据实例查找一批对象，且排序分页
     * @param student
     * @param pageable
     * @return
     */
    Page<Student> findAll(Student student, Pageable pageable);


    /**
     * 根据实例符合条件的数量
     * @param student
     * @return
     */
    long count(Student student);

    /**
     * 根据实例判断是否存在
     * @param student
     * @return
     */
    boolean exists(Student student);

    /**
     * 批量保存
     * @param student
     * @return
     */
    void saveAll(List<Student> student);


    /**
     * 根据名字模糊查询，按照id降序
     * @param name
     * @return
     */
    List<Student> findByStudentNameOrderByIdDesc(String name);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    Student findByIdhhh(Integer id);
    /**
     * 修改
     * @param name
     * @param id
     * @return
     */
    int updateStudentName(String name, Integer id);

    int insertStudent(@Param("student_id") Integer student_id, @Param("student_name") String student_name, @Param("clazz_id") Integer clazzId, @Param("hometown") String hometown);


    Student selectById(Integer id);


}
