package com.soft1851.springboot.jpa.service.impl;

import com.soft1851.springboot.jpa.dao.BlogRepository;
import com.soft1851.springboot.jpa.model.Blog;
import com.soft1851.springboot.jpa.service.BlogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.Basic;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/13 8:27
 * @Version 1.0
 **/

@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogRepository blogRepository;
    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void batchSave(List<Blog> blogs) {
        blogRepository.saveAll(blogs);
    }

    @Override
    public void delete(Integer id) {
        blogRepository.deleteById(id);
    }

    @Override
    public void batchDelete(List<Blog> blogs) {
        //使用一条SQL
        blogRepository.deleteInBatch(blogs);
        //使用多条SQL
//        blogRepository.deleteAll(blogs);
        }

    @Override
    public void deleteAll() {
//        blogRepository.deleteAll();
        blogRepository.deleteAllInBatch();
    }

    @Override
    public void update(Blog blog) {
        blogRepository.saveAndFlush(blog);
    }

    /**
     * 获取持久化管理器
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * 批量更新方法，批量插入，删除做法类似
     * @param blogs
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void batchUpdate(List<Blog> blogs) {
        blogs.forEach(blog -> {
            em.merge(blog);
        });
        em.flush();
        em.clear();
    }

    @Override
    public long count() {
        return blogRepository.count();
    }

    @Override
    public Blog getBlog(Integer id) {
        //findOne的速度快于findById
        Optional<Blog> blog = blogRepository.findById(id);
//        Optional<Blog> blogs = blogRepository.findOne(Example.of(Blog.builder().blogId(id).build()));
        return blog.orElse(null);
    }

    @Override
    public List<Blog> getAll() {
        return blogRepository.findAll();
    }
}
