package com.soft1851.springbootjpaadvance.dao;

import com.soft1851.springbootjpaadvance.model.cascade.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/14 18:05
 * @Version 1.0
 **/
public interface ClazzRepository extends JpaRepository<Clazz, Integer> {

    /**
     * 根据clazz_id查询Clazz的所有信息
     * @param clazzId
     * @return
     */
    Clazz findClazzByClazzIdEquals(Integer clazzId);

}
