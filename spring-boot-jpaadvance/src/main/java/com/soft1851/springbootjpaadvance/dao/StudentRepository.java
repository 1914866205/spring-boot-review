package com.soft1851.springbootjpaadvance.dao;

import com.soft1851.springbootjpaadvance.model.cascade.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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
public interface StudentRepository extends JpaRepository<Student, Integer> , JpaSpecificationExecutor<Student> {
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

    /**
     * 插入数据
     * @param student_id
     * @param student_name
     * @param clazzId
     * @param hometown
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Modifying
    @Query(value="insert into student (student_id,student_name,clazz_id,hometown) values(:student_id,:student_name,:clazz_id,:hometown)",nativeQuery = true)
    int insertStudent(@Param("student_id") Integer student_id, @Param("student_name") String student_name, @Param("clazz_id") Integer clazzId, @Param("hometown") String hometown);


    Student selectById(Integer id);

    /**
     * 根据name查询并分页
     * @param name
     * @param pageable
     * @return
     */
    Page<Student> findByStudentName(String name, Pageable pageable);


    /**
     * Page 和 Slice 的区别如下:
     *
     * Page 接口继承自 Slice 接口，而 Slice 继承自 Iterable 接口。
     * Page 接口扩展了 Slice 接口，添加了获取总页数和元素总数量的方法，因此，返回 Page 接口时，必须执行两条 SQL，一条复杂查询分页数据，另一条负责统计数据数量。
     * 返回 Slice 结果时，查询的 SQL 只会有查询分页数据这一条，不统计数据数量。
     * 用途不一样：Slice 不需要知道总页数、总数据量，只需要知道是否有下一页、上一页，是否是首页、尾页等，比如前端滑动加载一页可用；而 Page 知道总页数、总数据量，可以用于展示具体的页数信息，比如后台分页查询。
     * @param name
     * @param pageable
     * @return
     */
    Slice<Student> findStudentsByStudentName(String name, Pageable pageable);
    /**
     * 查询id最大的用户信息
     * @return
     */
    Student findFirstByOrderByStudentIdDesc();

    /**
     * 查询前三个元素
     * @param name
     * @param pageable
     * @return
     */
    Page<Student> queryFirst3ByStudentName(String name, Pageable pageable);
    Slice<Student> findTop3ByStudentName(String name, Pageable pageable);
    //根据Specification 条件查询单个对象，注意的是，如果条件能查出来多个会报错
//    Optional<Student> findOne(@Nullable Specification<Student> spec);
//
//    //根据Specification 条件查询List结果
//    List<Student> findAll(@Nullable Specification<Student> spec);
//
//    //根据 Specification 条件，分页查询
//    Page<Student> findAll(@Nullable Specification<Student> spec, Pageable pageable);
//
//    //根据 Specification 条件，带排序的查询结果
//    List<Student> findAll(@Nullable Specification<Student> spec, Sort sort);
//
//    //根据 Specification 条件，查询数量
//    long count(@Nullable Specification<Student> spec);

//    @Query("SELECT c.clazzName\n" +
//            "FROM Clazz c,Student s\n" +
//            "WHERE c.clazzId=s.clazzId\n" +
//            "AND s.studentId=?1")
//    String findClassNameByStudentId(Integer studentId);
}
