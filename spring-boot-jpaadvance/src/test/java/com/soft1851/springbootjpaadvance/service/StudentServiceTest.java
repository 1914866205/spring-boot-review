package com.soft1851.springbootjpaadvance.service;


import com.soft1851.springbootjpaadvance.model.cascade.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                .hometown("江苏宜兴").birthday(LocalDateTime.now()).build();
        log.info(student.toString());
        studentService.save(student);
        log.info("save>>>>>>>>>>>>>>>{}", studentService.findById(3));
    }

    @Test
    public void delete() {
        Student student = Student.builder().studentId(1).studentName("陶然然")
                .hometown("江苏宜兴").birthday(LocalDateTime.now()).build();
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
    public void findAll1() {
        Student student = Student.builder().build();
        log.info("findAll>>>>>>>>>>>>>>{}", studentService.findAll(student, Sort.by(Sort.Direction.DESC, "studentId")));
    }

    @Test
    public void findAll2() {
        Page<Student> pageInfo = studentService.findAll(PageRequest.of(1, 2, Sort.by(Sort.Direction.ASC, "studentId")));
        log.info("findAll>>>>>>>>>>>>>>{}", pageInfo);
    }

    @Test
    public void count2() {
        log.info("count2>>>>>>>>>>>>>>>>>>{}", studentService.count(Student.builder().studentName("倪涛涛").build()));
    }


    @Test
    public void exists() {
        log.info("exists>>>>>>>>>>>>>>>>>>{}", studentService.exists(Student.builder().studentName("倪涛涛").build()));
    }

    @Test
    public void saveAll() {
        Student student = Student.builder().studentName("陶然然").hometown("江苏宜兴").birthday(LocalDateTime.now()).build();
        Student student2 = Student.builder().studentName("陶然然2").hometown("江苏宜兴").birthday(LocalDateTime.now()).build();
        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);
        studentService.saveAll(students);
        log.info("saveAll>>>>>>>>>>>>>>>>>>{}", studentService.findAll());
    }

    @Test
    public void findByStudentNameOrderByStudentIdDesc() {
        log.info("findByStudentNameOrderByStudentIdDesc>>>>>>>>>>>>>>>>>>{}", studentService.findByStudentNameOrderByIdDesc("陶然然"));
    }

    @Test
    public void findByIdhhh() {
        log.info("findByIdhhh>>>>>>>>>>>>>>>>>>{}", studentService.findByIdhhh(2));
    }

    @Test
    public void updateStudentName() {
        log.info("updateStudentName>>>>>>>>>>>>>>>>>>{}", studentService.updateStudentName("陶然", 2));
        log.info("findByIdhhh>>>>>>>>>>>>>>>>>>{}", studentService.findByIdhhh(2));

    }

    @Test
    public void insertStudent() {
        studentService.insertStudent(100, "哈哈哈哈", 1, "江苏徐州");
        log.info("findByIdhhh>>>>>>>>>>>>>>>>>>{}", studentService.findByIdhhh(100));
    }

    @Test
    public void selectById() {
        log.info("selectById>>>>>>>>>>>>>>>>>>{}", studentService.selectById(2));
    }

    @Test
    public void findAll3() {
        int page = 1;
        int size = 2;
        Sort sort = Sort.by(Sort.Direction.DESC, "studentId");
        Pageable pageable = PageRequest.of(page, size, sort);
        log.info("findAll>>>>>>>>>>>>>>>>>>{}", studentService.findAll(pageable));
    }

    @Test
    public void findStudentByStudentName() {
        int page = 1;
        int size = 2;
        Sort sort = Sort.by(Sort.Direction.DESC, "studentId");
        Pageable pageable = PageRequest.of(page, size, sort);
        Slice<Student> students = studentService.findStudentByStudentName("陶然然", pageable);
            log.info("findStudentByStudentName>>>>>>>>>>>>>>>>>>{}",students);
        students.forEach(student -> {
            log.info("findStudentByStudentName>>>>>>>>>>>>>>>>>>{}", student);
        });
            log.info("findStudentByStudentName>>>>>>>>>>>>>>>>>>{}");
    }


    @Test
    public void queryFirst3ByStudentName() {
        int page = 1;
        int size = 2;
        Sort sort = Sort.by(Sort.Direction.DESC, "studentId");
        Pageable pageable = PageRequest.of(page, size, sort);
        Slice<Student> students = studentService.queryFirst3ByStudentName("陶然然", pageable);
        students.forEach(student -> {
            log.info("queryFirst3ByStudentName>>>>>>>>>>>>>>>>>>{}", student);
        });
    }

    @Test
    public void findTop3ByStudentName() {
        int page = 1;
        int size = 2;
        Sort sort = Sort.by(Sort.Direction.DESC, "studentId");
        Pageable pageable = PageRequest.of(page, size, sort);
        Slice<Student> students = studentService.findTop3ByStudentName("陶然然", pageable);
        students.forEach(student -> {
            log.info("findTop3ByStudentName>>>>>>>>>>>>>>>>>>{}", student);
        });
    }

    /**
     * Root<T> root，代表了可以查询和操作的实体对象的根，开一个通过 get("属性名") 来获取对应的值。
     * CriteriaQuery query，代表一个 specific 的顶层查询对象，它包含着查询的各个部分，比如 select 、from、where、group by、order by 等。
     * CriteriaBuilder cb，来构建 CritiaQuery 的构建器对象，其实就相当于条件或者是条件组合，并以 Predicate 的形式返回。
     */
    @Test
    public void findOneSpecification() {
        //根据 Specification 条件查询单个对象，注意的是，如果条件能查出来多个会报错
        Specification<Student> specification = new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //root.get("name")表示获取name这个字段的名称，like表示执行like查询，%zt%表示值
//                Predicate p1 = criteriaBuilder.like(root.get("studentName"), "%陶然%");
                Predicate p2 = criteriaBuilder.equal(root.get("studentId"), 2);
                //将两个查询联合起来之后返回Predicate对象
                return criteriaBuilder.and(p2);
            }
        };
        Optional<Student> student = studentService.findOne(specification);
        log.info("findOneSpecification>>>>>>>>>>>>>>>>>>{}", student);

    }

    @Test
    public void findAllSpecificationPageable() {
        //根据 Specification 条件查询单个对象，注意的是，如果条件能查出来多个会报错
        Specification<Student> s1 = new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //root.get("name")表示获取name这个字段的名称，like表示执行like查询，%zt%表示值
                Predicate p = criteriaBuilder.like(root.get("studentName"), "%然%");
                //返回Predicate对象
                return criteriaBuilder.and(p);
            }
        };
        Specification<Student> s2 = new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //root.get("name")表示获取name这个字段的名称，like表示执行like查询，%zt%表示值
                Predicate p = criteriaBuilder.between(root.get("studentId"), 1, 10);
                //返回Predicate对象
                return criteriaBuilder.and(p);
            }
        };
        Sort sort = Sort.by(Sort.Direction.DESC, "studentId");
        Pageable pageable = PageRequest.of(1, 2, sort);
        Page<Student> students = studentService.findAll(Specification.where(s1).and(s2), pageable);
        log.info("findAllSpecification>>>>>>>>>>>>>>>>>>{}", students);
    }

    @Test
    public void findAllSpecificationSort() {
        //根据 Specification 条件查询多个对象
        Specification<Student> s1 = new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //root.get("name")表示获取name这个字段的名称，like表示执行like查询，%zt%表示值
                Predicate p = criteriaBuilder.like(root.get("studentName"), "%然%");
                //返回Predicate对象
                return criteriaBuilder.and(p);
            }
        };
        Specification<Student> s2 = new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //root.get("name")表示获取name这个字段的名称，like表示执行like查询，%zt%表示值
                Predicate p = criteriaBuilder.between(root.get("studentId"), 1, 10);
                //返回Predicate对象
                return criteriaBuilder.and(p);
            }
        };
        Sort sort = Sort.by(Sort.Direction.DESC, "studentId");
        List<Student> students = studentService.findAll(Specification.where(s1).and(s2), sort);
        students.forEach(student -> {
            log.info("findAllSpecificationPageable>>>>>>>>>>>>>>>>>>{}", student);
        });
    }

    @Test
    public void countSpecification() {
        //根据 Specification 条件查询
        Specification<Student> s1 = new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //root.get("name")表示获取name这个字段的名称，like表示执行like查询，%zt%表示值
                Predicate p = criteriaBuilder.like(root.get("studentName"), "%然%");
                //返回Predicate对象
                return criteriaBuilder.and(p);
            }
        };
        Specification<Student> s2 = new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //root.get("name")表示获取name这个字段的名称，like表示执行like查询，%zt%表示值
                Predicate p = criteriaBuilder.between(root.get("studentId"), 1, 10);
                //返回Predicate对象
                return criteriaBuilder.and(p);
            }
        };
        log.info("countSpecification>>>>>>>>>>>>>>>>>>{}", studentService.count(Specification.where(s1).and(s2)));
    }

//    @Test

    @Test
    void name() {

    }
//    public void findClassNameByStudentId(){
//        log.info("findClassNameByStudentId>>>>>>>>>>>>>>>>>>{}", studentService.findClassNameByStudentId(2));
//    }
}