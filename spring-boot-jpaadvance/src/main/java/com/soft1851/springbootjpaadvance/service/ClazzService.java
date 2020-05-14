package com.soft1851.springbootjpaadvance.service;

import com.soft1851.springbootjpaadvance.model.cascade.Clazz;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/14 18:08
 * @Version 1.0
 **/
public interface ClazzService {
    /**
     * 根据clazz_id查询Clazz的所有信息
     * @param clazzId
     * @return
     */
    Clazz findClazzByClazzIdEquals(Integer clazzId);

}
