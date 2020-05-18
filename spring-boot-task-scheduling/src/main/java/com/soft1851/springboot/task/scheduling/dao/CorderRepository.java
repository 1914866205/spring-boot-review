package com.soft1851.springboot.task.scheduling.dao;

import com.soft1851.springboot.task.scheduling.model.cascade.Corder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/17 19:04
 * @Version 1.0
 **/
public interface CorderRepository extends JpaRepository<Corder,Integer> {
}
