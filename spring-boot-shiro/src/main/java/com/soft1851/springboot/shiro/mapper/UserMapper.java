package com.soft1851.springboot.shiro.mapper;

import com.soft1851.springboot.shiro.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/20 11:03
 * @Version 1.0
 **/
@Repository
@Mapper
public interface UserMapper {
     User queryUserByName(@Param("name") String name);
}
