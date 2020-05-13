package com.soft1851.springboot.jpa.service;

import com.soft1851.springboot.jpa.model.Blog;

import java.util.List;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/13 8:21
 * @Version 1.0
 **/
public interface BlogService {
    void save(Blog blog);

    void batchSave(List<Blog> blogs);

    void delete(Integer id);

    void batchDelete(List<Blog> blogs);

    void deleteAll();

    void update(Blog blog);

    void batchUpdate(List<Blog> blogs);

    long count();

    Blog getBlog(Integer id);

    List<Blog> getAll();

}
