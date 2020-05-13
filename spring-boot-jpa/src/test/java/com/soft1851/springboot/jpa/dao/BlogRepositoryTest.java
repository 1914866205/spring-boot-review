package com.soft1851.springboot.jpa.dao;

import com.soft1851.springboot.jpa.model.Blog;
import com.soft1851.springboot.jpa.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BlogRepositoryTest {

    @Autowired
    private BlogService blogService;


    @Autowired
    private BlogRepository blogRepository;

    @Test
    public void testSave(){
        Blog blog = Blog.builder().blogText("程序员必备十大插件").blogSummary("盘点那些神器插件").build();
        // 保存单个对象
        blogRepository.save(blog);
        List<Blog> blogList = new ArrayList<>(Arrays.asList(
                Blog.builder().blogText("SpringBoot启动报错").blogSummary("环境，jar包冲突，代码编写错误").build(),
                Blog.builder().blogText("一键UI设计").blogSummary("盘点那些神器插件").build(),
                Blog.builder().blogText("机器学习 ---- 朴素贝叶斯").blogSummary("生成模型,判别模型").build(),
                Blog.builder().blogText("IO流 ").blogSummary("InputStream输入流、OutputStream简单介绍").build(),
                Blog.builder().blogText("在中国程序员是青春饭吗").blogSummary("纯技术路线,混圈子、混人脉、靠内推, 靠猎头,靠自己").build(),
                Blog.builder().blogText("居然能查看微信访客记录，这款神器牛逼了").blogSummary("视频动态").build()
        ));
        //保存
        blogRepository.saveAll(blogList);
    }


    @Test
    public void testDelete(){
        Blog blog = Blog.builder().blogId(1)
                .blogText("IO流 ").blogSummary("InputStream输入流、OutputStream简单介绍").build();
        //删除单条记录
        //根据主键删除
        blogRepository.deleteById(1);
        //或者参数为对象，根据主键删除
        blogRepository.delete(blog);
        //删除集合
        blog = Blog.builder().blogId(6).blogText("哈哈哈哈哈哈哈").blogSummary("555555555").build();
        List<Blog> blogs = new ArrayList<>();
        blogs.add(blog);
        blog = Blog.builder().blogId(7).blogText("66666666666666").blogSummary("hhhhhhhh").build();
        blogs.add(blog);
        //删除集合，一条一条删除
        blogRepository.deleteAll(blogs);
        //删除集合：一条sql，拼接 or语句
        blogRepository.deleteInBatch(blogs);
        //删除全部
        //删除所有，先findAll，然后一条一条删除，最后提交事务
        blogRepository.deleteAll();
        //删除所有，使用一条sql
        blogRepository.deleteAllInBatch();
    }

    @Test
    void findDistinctByBlogIdLessThan() {
        //条件查询
        List<Blog> blogs = blogRepository.findDistinctByBlogIdLessThan(2);
        log.info("满足条件的记录有:");
        blogs.forEach(b->{
            log.info(b.toString());
        });
    }

    @Test
    void findById() {
        log.info("blogService.getBlog(){}",blogService.getBlog(1));
    }

    @Test
    void updateName() {
        Blog.builder().blogText("一键UI设计").blogSummary("盘点那些神器插件").build();
        blogRepository.updateName("UI设计", 1);
        log.info("blogService.getBlog(1){}", blogService.getBlog(1));
    }

    @Test
    public void testSelect(){
        //查询所有
        blogRepository.findAll().forEach(blog->{
            log.info(blog.toString());
        });
        //分页查询全部，返回封装了的分页信息，jpa页码从0开始
        Page<Blog> pageInfo = blogRepository.findAll(
                PageRequest.of(1, 3, Sort.Direction.ASC, "blogId")
        );
        log.info("总记录数：{}", pageInfo.getNumberOfElements());
        log.info("当前页记录数：{}", pageInfo.getNumberOfElements());
        log.info("每页记录数：{}", pageInfo.getSize());
        log.info("获取总页数：{}", pageInfo.getTotalPages());
        log.info("查询结果：{}", pageInfo.getContent());
        log.info("当前页（从0开始计）：{}", pageInfo.isFirst());
        log.info("是否为首页：{}", pageInfo.isFirst());
        log.info("是否为尾页：{}", pageInfo.isLast());
        log.info("是否为空页：{}", pageInfo.isEmpty());
    }

    @Test
    public void testCustomSQL(){
        Integer num = blogRepository.insertBlog("博客名", "博客概括");
        log.info("增加的数据条数：{}", num);
        Integer updateNum = blogRepository.updateName("博客名", 1);
        log.info("修改的数据条数,{}", updateNum);
    }

    @Test
    void findByName() {
        //单个查询
        Blog blog = Blog.builder().blogId(2).blogText("UI设计").build();
        Optional<Blog> optionalBlog = blogRepository.findOne(Example.of(blog));
        log.info("单个查询结果:{}", optionalBlog.orElse(null));
    }

    @Test
    void insertBlog() {
        Blog blog = Blog.builder().blogId(10).blogText("程序员").blogSummary("是什么").build();
        blogService.save(blog);
    }
    @Test
    void findByBlogSummary() {
        //条件查询
        List<Blog> blogs = blogRepository.findByBlogSummary("盘点那些神器插件");
        log.info("满足条件的记录有:");
        blogs.forEach(b->{
            log.info(b.toString());
        });
    }

    @Test
    void findByBlogTextOrBlogSummary() {
        //条件查询
        List<Blog> blogs = blogRepository.findByBlogTextOrBlogSummary("IO流","SpringBoot");
        log.info("满足条件的记录有:");
        blogs.forEach(b->{
            log.info(b.toString());
        });
    }

    @Test
    void findByBlogTextLike() {
        //条件查询
        List<Blog> blogs = blogRepository.findByBlogTextLike("报错");
        log.info("满足条件的记录有:");
        blogs.forEach(b->{
            log.info(b.toString());
        });
    }

}