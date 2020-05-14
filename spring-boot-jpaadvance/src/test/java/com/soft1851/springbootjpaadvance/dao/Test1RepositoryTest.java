//package com.soft1851.springbootjpaadvance.dao;
//
//import com.soft1851.springbootjpaadvance.model.cascade.Test1;
//import com.soft1851.springbootjpaadvance.model.cascade.Test2;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.annotation.Resource;
//
//@SpringBootTest
//@Slf4j
//class Test1RepositoryTest {
//    @Resource
//    private Test1Repository test1Repository;
//    @Resource
//    private Test2Repository test2Repository;
//
//    @Test
//    void save(){
//        test1Repository.save(Test1.builder().test1Id(1).test1Name("aaa").build());
//        test1Repository.save(Test1.builder().test1Id(2).test1Name("bbb").build());
//        test2Repository.save(Test2.builder().test2Id(1).test2Name("ccc").build());
//    }
//
//    @Test
//    void delete(){
//        test1Repository.deleteAll();
//        test2Repository.deleteAll();
//    }
//}